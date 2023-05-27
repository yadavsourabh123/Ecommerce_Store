package com.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDAO;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.entity.Cart;
import com.entity.Product;
import com.entity.ProductOrder;
import com.entity.User;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("userObj");
			String paymentType = req.getParameter("payment");
			String totalPrice = req.getParameter("totalPrice");
			String amt = req.getParameter("amt");

			/* check out stock */

			CartDAO dao = new CartDAO();
			ProductDao pdao = new ProductDao();
			OrderDao odao = new OrderDao();

			List<Cart> plist = dao.getCart(user.getId());

			ProductOrder o = null;

			ArrayList<ProductOrder> orderList = new ArrayList<ProductOrder>();

			ArrayList<Product> outOfStockProduct = new ArrayList<Product>();

			Random r = new Random();

			for (Cart c : plist) {
				o = new ProductOrder();
				Product p = pdao.getProductByid(c.getPid());
				if (p.getStock() < c.getQuantity()) {
					outOfStockProduct.add(p);
				} else {
					o.setUserId(user.getId());
					o.setProductid(p.getId());
					o.setOrderId("PROD-ORD-00" + r.nextInt(1000));
					o.setQuantity(c.getQuantity());
					o.setPrice(p.getPrice());
					o.setPaymentType("COD");
					o.setOrderStatus("Order Processing");
					orderList.add(o);
				}
			}
			/* end out of stock */

			if (outOfStockProduct.size() > 0) {
				session.setAttribute("failedMsg", "Please Remove out of stock product");
				resp.sendRedirect("cart.jsp");
			} else {
				if ("COD".equals(paymentType)) {

					boolean f = odao.saveOrder(orderList);

					if (f) {

						for (ProductOrder po : orderList) {
							Product p = pdao.getProductByid(po.getProductid());
							pdao.updateProductStock(p.getId(), p.getStock() - po.getQuantity());
						}

						resp.sendRedirect("order_success.jsp");
					} else {
						session.setAttribute("failedMsg", "Your Order Failed");
						resp.sendRedirect("cart.jsp");
					}

				} else if ("noselect".equals(paymentType)) {
					session.setAttribute("failedMsg", "Choose Payment Method");
					resp.sendRedirect("cart.jsp");
				} else {
					resp.sendRedirect("card_payment.jsp?amt=" + amt);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
