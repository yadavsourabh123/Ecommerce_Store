package com.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProductDao;
import com.dao.UserDAO;
import com.util.DBConnect;

@WebServlet("/admin/deleteUser")
public class DeleteUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		UserDAO dao = new UserDAO(DBConnect.getConnection());

		HttpSession session = req.getSession();

		if (dao.deleteUser(id)) {

			session.setAttribute("succMsg", "Delete sucesfully");
			resp.sendRedirect("user.jsp");

		} else {
			session.setAttribute("errorMsg", "something wrong on server");
			resp.sendRedirect("user.jsp");
		}

	}
}
