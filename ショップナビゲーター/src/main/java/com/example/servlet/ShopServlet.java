package com.example.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.service.ShopService;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字化け防止
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        session.setAttribute("shopList", new ShopService().findAll());
        
       
        request.getRequestDispatcher("shoplist.jsp").forward(request, response);;
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
    
}