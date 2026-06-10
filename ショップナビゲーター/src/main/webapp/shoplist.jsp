<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.example.model.Shop" %>
<%@ page import="com.example.service.ReviewService" %>

<html>
<head>
    <title>店舗一覧</title>
</head>
<body>

<h3>店舗一覧</h3>

<%
    List<Shop> allShops = (List<Shop>) session.getAttribute("shopList");

    if (allShops == null) {
        allShops = new ArrayList<Shop>();
    }
%>

<%
    if (allShops != null && !allShops.isEmpty()) {

        for (Shop shop : allShops) {
        	//shop.setId(5);
%>

<div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">

    <!-- 店舗名をクリック可能にする -->
    <p>
        <strong>
            <a href="shopdetail?id=<%= shop.getId() %>">
    <%= shop.getName() %>
</a>
        </strong>
    </p>

    <p>ジャンル：<%= shop.getGenre() %></p>
    <p>住所：<%= shop.getAddress() %></p>

</div>

<%
        }

    } else {
%>

<p>該当する店舗は見つかりませんでした。</p>

<%
    }
%>

<hr>

</body>
</html>