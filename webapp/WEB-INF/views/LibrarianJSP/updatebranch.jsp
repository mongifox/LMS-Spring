<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@include file="../header.html"%>


<%
	//LibrarianService libService  = new LibrarianService(); 
	Integer branchId = (Integer) (request.getAttribute("branchId"));
	Branch b = (Branch)request.getAttribute("branch");
%>
<div class="container">

	<h1>Updating Branch for <%=b.getBranchName()%></h1>
	<form action="branchUpdate" method="post">
		${statusMessage} <br />
		<input type="hidden" name="branchId"  value="<%=branchId%>"/>
		
		 <div class="form-group">
			  <label for="usr">Branch Name: </label>
			  <input type="text" class="form-control" id="usr" name="branchName" value="<%=b.getBranchName()%>" >
			</div>
			<div class="form-group">
			  <label for="pwd">Branch Address: </label>
			  <input type="text" class="form-control" id="pwd" name="branchAddress" value="<%=b.getBranchAddress()%>" >
			</div> 
		<button type="submit"class="btn btn-success" >Update Branch</button>
		
	</form>
	
	
</div>