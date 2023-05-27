package com.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDAO;
import com.util.DBConnect;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int uid = Integer.parseInt(req.getParameter("uid"));
			int pid = Integer.parseInt(req.getParameter("pid"));

			CartDAO dao = new CartDAO();
			HttpSession session = req.getSession();

			if (dao.addCart(pid, uid)) {
				session.setAttribute("succMsg", "Product Added to Cart");
				resp.sendRedirect("view_product.jsp?id=" + pid);
			} else {
				session.setAttribute("errorMsg", "something wrong on server");
				resp.sendRedirect("view_product.jsp?id=" + pid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
