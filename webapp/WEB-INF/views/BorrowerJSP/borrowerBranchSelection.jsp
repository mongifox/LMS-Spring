<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>

<%@include file="header.html"%>

<%
//BorrowerService brwService = new BorrowerService();
List<Branch> branches = new ArrayList<>();
branches = (List<Branch>) request.getAttribute("branches");
Integer cardNo = (Integer)request.getAttribute("cardNo");
%>
<div class="container">
	<h3>Find the List of Branches in LMS.</h3>
	${statusMessage}
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Branches Name</th>
			<th>Branches Address</th>
			<th>Book Titles</th>
		
			<th></th>
		</tr>
		<%for(Branch branch: branches){ %>
			<tr>
				<td><%out.println(branches.indexOf(branch)+1); %></td>
				<td><%=branch.getBranchName() %></td>
				<td><%=branch.getBranchAddress() %></td>
				<td>
					<%for(Book b: branch.getBooks()){ 
						out.println(b.getTitle()+"|| ");
					}%>
				</td>
				<%-- <td>
					<%for(BookCopies bc: branch.getBookCopies()){ 
						out.println(bc.getNoOfCopies()+"|| ");
					}%> --%>
				</td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='borrowerSelec?branchId=<%=branch.getBranchId()%>&cardNo=<%=cardNo%>'">Select</button></td>
			</tr>
		<%} %>
	</table>
</div>