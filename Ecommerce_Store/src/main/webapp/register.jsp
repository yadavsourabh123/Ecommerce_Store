
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ecom : Register</title>
<%@include file="component/css.jsp"%>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	<%@include file="component/navbar.jsp"%>

	<div class="container mt-5 p-5">
		<div class="row">
			<div class="col-md-5 p-5">
				<img alt="" src="data/img/ecom.png" width="100%" height="400px">
			</div>
			<div class="col-md-6 offset-md-1 mt-1">
				<div class="card card-sh">
					<div class="card-header">
						<p class="fs-5 text-center">Register</p>
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


						<form action="registerUser" method="post" novalidate enctype="multipart/form-data"
							id="userRegister">
							<div class="row">
								<div class="col">
									<label class="form-label">Full Name</label> <input required
										name="fullname" type="text"
										class="form-control form-control-sm">
								</div>

								<div class="col">
									<label class="form-label">Mobile Number</label> <input required
										name="mobno" type="number"
										class="form-control form-control-sm">
								</div>
							</div>


							<div class="mt-2">
								<label class="form-label">Email</label> <input required
									name="email" type="email" class="form-control form-control-sm">
							</div>


							<div class="row mt-2">

								<div class="col">
									<label class="form-label">Address</label> <input required
										name="address" type="text"
										class="form-control form-control-sm">
								</div>

								<div class="col">
									<label class="form-label">City</label> <input required
										name="city" type="text" class="form-control form-control-sm">
								</div>

							</div>

							<div class="row mt-2">

								<div class="col">
									<label class="form-label">State</label> <input required
										name="state" type="text" class="form-control form-control-sm">
								</div>

								<div class="col">
									<label class="form-label">Pincode</label> <input required
										name="pincode" type="number"
										class="form-control form-control-sm">
								</div>

							</div>


							<div class="row mt-3">
								<div class="col">
									<label class="form-label">Password</label> <input required
										name="password" type="password" id="pass"
										class="form-control form-control-sm">
								</div>


								<div class="col">
									<label class="form-label">Confirm Password</label> <input
										required name="confirmpassword" type="password"
										class="form-control form-control-sm">
								</div>
								<div class="mb-3">
									<label class="form-label">Profile Image</label> <input required
										name="img" type="file" class="form-control">
								</div>
							</div>
							<button type="submit"
								class="btn bg-primary text-white col-md-12 mt-3">Register</button>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
	<script type="text/javascript" src="data/js/user_script.js"></script>
</body>
</html>