<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%AdminService adminService = new AdminService();
Publisher publisher = adminService.readPublisherByPk(Integer.parseInt(request.getParameter("publisherId")));
%>
<div class="container">
	<div class="form-group">
	<form style="text-align: center" action="editPublisher" method="post">
		<label for="exampleInputEmail1">Publisher Name</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="publisherName" value="<%=publisher.getPublisherName()%>"
		placeholder="Enter Name"> <br />
		
		<label for="exampleInputEmail1">Publisher Address</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"
		placeholder="Enter Address"> <br />
		
		<label for="exampleInputEmail1">Publisher Phone Number</label> <input type="text"
		class="mx-auto  w-25 form-control" form-control" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"
		placeholder="Enter Phone Number"> <br />
		
		<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>"> <br />
		<button type="submit" class="btn btn-success float-md-center">SAVE</button>
	</form>
	</div>
</div>


		