package com.example.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.service.ReviewService;

@WebServlet("/review/delete")
public class ReviewDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));

        HttpSession session = request.getSession();
        List<Integer> myPostIds = (List<Integer>) session.getAttribute("MY_POST_IDS");

        if (myPostIds != null && myPostIds.contains(reviewId)) {
           new ReviewService().deleteReviewById(reviewId);
        } else {
            System.out.println("削除権限がないか、セッションが切れている。");
        }

        response.sendRedirect(request.getContextPath() + "/shop_detail.jsp?id=5");
    }
}