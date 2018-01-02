<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="../header.html"%>
<%AdminService adminService = new AdminService();
Branch branch = adminService.readBranchByPk(Integer.parseInt(request.getParameter("branchId")));
%>
<div class="container">
	<form action="editBranch" method="post">
		<h3>Enter the New Branch Name</h3>
		<input type="text" name="branchName" value="<%=branch.getBranchName()%>"> <br />
		<input type="text" name="branchAddress" value="<%=branch.getBranchAddress()%>"> <br />
		<input type="hidden" name="branchId" value="<%=branch.getBranchId()%>"> <br />
		<button type="submit" class="btn btn-success float-md-center">Save</button>
	</form>
</div>