<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@include file="../header.html"%>

<%
List<Book> books = new ArrayList<>();
books = (List<Book>)request.getAttribute("booksPerBranch");
Integer cardNo = (Integer)request.getAttribute("cardNo");
Integer branchId = (Integer)request.getAttribute("branchId");
HashMap<Integer, Integer> mapBookBorrowed = new HashMap<>();
mapBookBorrowed = (HashMap<Integer, Integer>) request.getAttribute("mapBookBorrowed");
//BorrowerService brwService = new BorrowerService();
boolean flagRepeat = false;
%>

<div class="container">
	<h3>List of Books Borrower has taken.</h3>
	${statusMessage}
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>No Of Copies</th>
			<th>Return</th>
		</tr>
		<%for(Book book: books){ %>
			<tr>
				<td><%out.println(books.indexOf(book)+1); %></td>
				<td><%=book.getTitle() %></td>
				 <td>
				<%
					out.println(mapBookBorrowed.get(book.getBookId()));
				 %></td>  
				<td><button type="button" class="btn btn-success" onclick="javascript:location.href='returnBookFinal?cardNo=<%=cardNo%>&bookId=<%=book.getBookId()%>&branchId=<%=branchId%>'">Return</button></td>
			</tr>
		<%} %>
	</table>
	
	
	<a id="checkout" role="button" href="http://localhost:8080/lms/borrowerSelec?branchId=<%=branchId%>&cardNo=<%=cardNo%>" class="btn btn-warning">Back</button>
</div>