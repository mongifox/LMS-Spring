<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<div class="container">
	<form style="text-align: center" action="cardNoValidation" method="get">
		<div class="form-group">
		<label for="exampleInputEmail1">Card Number:</label> <input
				type="text" class="mx-auto  w-25 form-control"
				form-control" name="cardNo" placeholder="Enter Card No">
		</div>
		<button type="submit" class="btn btn-primary btn-lg">LogIn</button>
		<br/>
		<br/>
		${statusMessage}
	</form>
	
</div>