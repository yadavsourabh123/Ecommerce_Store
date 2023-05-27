package com.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CategoryDao;

@WebServlet("/admin/deleteCategory")
public class DeleteCategory extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		CategoryDao dao = new CategoryDao();

		HttpSession session = req.getSession();

		if (dao.deleteCategory(id)) {

			session.setAttribute("succMsg", "Delete sucesfully");
			resp.sendRedirect("category.jsp");

		} else {
			session.setAttribute("errorMsg", "something wrong on server");
			resp.sendRedirect("category.jsp");
		}

	}

}
