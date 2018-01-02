<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%
Borrower borrower = (Borrower) request.getAttribute("borrower");
%>
<div class="container">
	<div class="form-group">
	<form style="text-align: center" action="editBorrower" method="post">
		<label for="exampleInputEmail1">Borrower Name</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="name" value="<%=borrower.getName()%>"
		placeholder="Enter Name"> <br />
		
		<label for="exampleInputEmail1">Borrower Address</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="address" value="<%=borrower.getAddress()%>"
		placeholder="Enter Address"> <br />
		
		<label for="exampleInputEmail1">Borrower Phone Number</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="phone" value="<%=borrower.getPhone()%>"
		placeholder="Enter Phone Number"> <br />
		
		<input type="hidden" name="cardNo" value="<%=borrower.getCardNo()%>"> <br />
		<button type="submit" class="btn btn-success float-md-center">SAVE</button>
	</form>
	</div>
</div>


		