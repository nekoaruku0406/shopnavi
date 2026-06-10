<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.example.model.Shop" %>
<%@ page import="com.example.model.Review" %>
<%@ page import="com.example.service.ReviewService" %>

<html>
<head><title>店舗詳細</title></head>
<body>

<h2>店舗詳細画面（仮）</h2>

<%
    // セッションから自分の投稿IDリストを取得
    List<Integer> myPostIds = (List<Integer>) session.getAttribute("MY_POST_IDS");
    if (myPostIds == null) {
        myPostIds = new ArrayList<Integer>();
    }
    Shop shop = (Shop) request.getAttribute("shop");
    // ★ReviewServiceを呼び出して、さっき追加された最新の全リストを取得する
    ReviewService service = new ReviewService();
    List<Review> allReviews = service.getReviewsByShopId(shop.getId());
%>

<!-- 店舗情報 -->
<h3>店舗情報</h3>

<div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;">

    <p>
        <strong>店舗名：<%= shop.getName() %></strong>
    </p>

    <p>
        ジャンル：<%= shop.getGenre() %>
    </p>

    <p>
        住所：<%= shop.getAddress() %>
    </p>

</div>

<h3>💬 みんなの口コミ</h3>

<%
    // リストの中身を一つずつ取り出して画面に描画する
    if (allReviews != null && !allReviews.isEmpty()) {
        for (Review rev : allReviews) {
%>
        <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
            <p><strong>評価：<%= rev.getRating() %></strong></p>
            <p>コメント：<%= rev.getComment() %></p>
            <p style="color: gray; font-size: 11px;">（口コミID: <%= rev.getId() %>）</p>

            <% 
                // この口コミが自分の投稿したものなら、削除ボタンを出す
                if (myPostIds.contains(rev.getId())) { 
            %>
                <form action="${pageContext.request.contextPath}/review/delete" method="POST">
                    <input type="hidden" name="reviewId" value="<%= rev.getId() %>">
                    <button type="submit" style="color: red;">❌ この投稿を削除する</button>
                </form>
            <% } else { %>
                <p style="color: gray; font-size: 12px;">（自分が書いた投稿ではない、または30分経過したため削除できません）</p>
            <% } %>
        </div>
<% 
        }
    } else {
%>
    <p>まだ口コミはありません。最初の口コミを投稿しよう！</p>
<% 
    }
%>

<hr>

<h3>✍ 口コミを投稿する</h3>
<form action="${pageContext.request.contextPath}/review/post" method="POST">
    <input type="hidden" name="shopId" value="<%= shop.getId() %>">
    
    評価：
    <select name="rating">
        <option value="A">A（すごくいい）</option>
        <option value="B">B（いい）</option>
        <option value="C">C（普通）</option>
        <option value="D">D（ちょっと…）</option>
    </select><br><br>
    
    コメント：<br>
    <textarea name="comment" rows="4" cols="40"></textarea><br><br>
    
    <button type="submit">投稿する</button>
</form>


<h2><a href="shop">戻る</h2>
</body>
</html>