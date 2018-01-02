<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%
	
	List<Branch> branches = new ArrayList<>();
	branches = (List<Branch>)request.getAttribute("branches");
%>

<div class="container">
<a href="http://localhost:8080/lms/addbranch1" class="pull-right btn btn-primary">Add Branch</a><br />
	<h3>Find the List of Branches in LMS.</h3>
		${statusMessage}
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Branch Name</th>
			<th>Books Present</th>
			<!-- <th>Book Copies</th> -->
			
			<th></th>
		</tr>
			<%for(Branch br: branches){ %>
			<tr>
				<td><%out.println(branches.indexOf(br)+1); %></td>
				<td><a href=editbranch.jsp?branchId=<%=br.getBranchId()%>"><%=br.getBranchName() %></a></td>
				<td>
					<%for(Book b: br.getBooks()){ 
						out.println(b.getTitle()+"|| ");
					}%>
				</td>
				
				<%-- <td>  
				<%
				for(BookCopies bc: br.getBookCopies()){ 
					if(bc.getNoOfCopies()!=null){
						out.println(bc.getNoOfCopies()+"|| ");
					}	
					}%>   
				 </td> --%> 
			<%-- <td><button type="button" class="btn btn-primary"
				onclick="javascript:location.href='editbranch.jsp?branchId=<%=br.getBranchId()%>'">Edit</button></td> --%>
		<td><button type="button" class="btn btn-danger"
				onclick="javascript:location.href='deleteBranch?branchId=<%=br.getBranchId()%>'">Delete</button></td>
		</tr>
		<%} %>
	</table>
</div>