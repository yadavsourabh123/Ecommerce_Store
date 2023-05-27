
<%@page import="com.util.DBConnect"%>
<%@page import="com.dao.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@include file="component/css.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
}

.clink {
	text-decoration: none;
}
</style>
</head>
<body>
	<%@include file="component/navbar.jsp"%>

	<div class="container p-5" style="margin-top: 60px">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card paint-card">
					<div class="card-header">
						<p class="fs-4 text-center">User Forgot Password</p>
						<c:if test="${not empty errorMsg}">
							<p class="fs-4 text-center text-danger">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty succMsg}">
							<p class=" fs-4 text-center text-success">${succMsg}</p>
							<c:remove var="succMsg" scope="session" />
						</c:if>

					</div>
					<div class="card-body">

						<form action="forgot_password.jsp" method="post">

							<div class="mb-3">
								<label class="form-label">Enter Email</label> <input
									name="email" type="text" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Enter Mob No</label> <input
									name="mobno" type="text" class="form-control">
							</div>

							<button type="submit" class="btn bg-primary text-white col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
	String em = request.getParameter("email");
	String mobno = request.getParameter("mobno");

	if (em != null && mobno != null) {

		UserDAO dao = new UserDAO(DBConnect.getConnection());
		boolean f = dao.checkEmailAndMobForForgot(em, mobno);
		if (f) {
			response.sendRedirect("reset_password.jsp?em=" + em);
		} else {
			session.setAttribute("errorMsg", "invalid email mobno");
			response.sendRedirect("forgot_password.jsp");
		}
	}
	%>

</body>
</html>