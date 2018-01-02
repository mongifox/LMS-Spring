<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>



<%
List<Borrower> borrowers = new ArrayList<>();
borrowers = (List<Borrower>) request.getAttribute("borrowers");
%>

<div class="container">
<br/>
<a href="http://localhost:8080/lms/addborrower1" class="pull-right btn btn-primary">New Borrower</a><br /> 
<div class="clear"></div>
	<h3>Find the List of Borrowers in LMS.</h3>
	${statusMessage}
	
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone Number</th>
			<th>Delete</th>
		</tr>
		<%for (Borrower b : borrowers) {%>
		<tr>
			<td><%out.println(borrowers.indexOf(b) + 1);%></td>
			<td><a href="http://localhost:8080/lms/editBorrower1?cardNo=<%=b.getCardNo()%>"><%=b.getName()%></a></td>
			<td><%=b.getAddress()%></td>
			<td><%=b.getPhone()%></td>
			<td><button type="button" class="btn btn-danger"
				onclick="javascript:location.href='deleteBorrower?cardNo=<%=b.getCardNo()%>'">Delete</button></td>
		</tr>
		<%}%>
		
	</table>
</div>