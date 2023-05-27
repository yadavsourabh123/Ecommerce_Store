<%@page import="com.dao.OrderDao"%>

<%
String st = request.getParameter("st");
if (st != null) {
	int id = Integer.parseInt(request.getParameter("id"));
	OrderDao dao2 = new OrderDao();
	boolean f = dao2.updateStatus(st, id);
	if (f) {
		session.setAttribute("succMsg", "order status updated");
		response.sendRedirect("orders.jsp");
	}
}
%>