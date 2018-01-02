<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>

<%@include file="header.html" %>

<%
	//LibrarianService libService = new LibrarianService();
	List<Branch> branches = new ArrayList<>();
	branches = (List<Branch>) request.getAttribute("branches");
	//branches = libService.readBranches();
%>
<div class="container">  
  <!-- Nav pills -->
    
    <h3>Select a branch to Update /  Edit</h3>
      	<form style="text-align:center;" action="selectBranch" id="selectbrach" method="post">
		 
		<hr/>
		<br/>
		<br/>
		<br/>
		${statusMessage}
		<br/>
		<select  style="height:2.5em;" name="branchId" id="dropdown_menu" >
			<option value="" disabled selected >Select the Branch</option>
			<%
				for (Branch br : branches) {
			%>
			<option value="<%=br.getBranchId()%>"><%=br.getBranchName()%></option>
			<% 
				}
			%>
		</select>
		 <input type="submit" name="update" class="btn btn-primary" value="Update Branch"/>
		 <input type="submit" name="edit" class="btn btn-success" value="Edit Book Copies"/>
	</form> <br/>
	  <br/>   
	 
	  
  
</div>