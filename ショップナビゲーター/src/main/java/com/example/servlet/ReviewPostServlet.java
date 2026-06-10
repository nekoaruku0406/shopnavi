package com.example.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.model.Review;
import com.example.service.ReviewService;

@WebServlet("/review/post")
public class ReviewPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // 日本語文字化け対策
        request.setCharacterEncoding("UTF-8");

        // フォームから値取得
        int shopId =
                Integer.parseInt(request.getParameter("shopId"));

        String rating =
                request.getParameter("rating");

        String comment =
                request.getParameter("comment");

        // Reviewオブジェクト作成
        Review review = new Review();

        review.setShopId(shopId);
        review.setRating(rating);
        review.setComment(comment);

        // DB保存
        ReviewService service = new ReviewService();
        service.addReview(review);

        // 投稿後、元の店舗詳細ページへ戻る
        response.sendRedirect(
                request.getContextPath()
                + "/shopdetail?id="
                + shopId
        );
    }
}