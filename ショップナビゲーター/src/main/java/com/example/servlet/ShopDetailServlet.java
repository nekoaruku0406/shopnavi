package com.example.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.model.Shop;
import com.example.service.ShopService;

@WebServlet("/shopdetail")
public class ShopDetailServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字化け防止
        request.setCharacterEncoding("UTF-8");
        
        Shop shop = new ShopService().findById(request.getParameter("id"));
        request.setAttribute("shop",shop);
        
       
        request.getRequestDispatcher("shop_detail.jsp").forward(request, response);;
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
    
}