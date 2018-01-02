<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@include file="../header.html"%>

<%
List<Book> books = new ArrayList<>();
books = (List<Book>)request.getAttribute("booksPerBranch");
Integer cardNo = (Integer)request.getAttribute("cardNo");
Integer branchId = (Integer)request.getAttribute("branchId");
Branch branch = new Branch();
branch.setBranchId(branchId);

boolean flagRepeat = false;
int numOfPages = 0;
%>

<div class="container">
	<h3>List of Books available for Check Out.</h3>
	${statusMessage}
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>No Of Copies</th>
			<th></th>
		</tr>
		<%for(Book book: books){ %>
			<tr>
				<td><%out.println(books.indexOf(book)+1); %></td>
				<td><%=book.getTitle() %></td>
				<td>
				<%for(BookCopies bc: book.getBookCopies()){
					if(bc.getBranchId()==branch.getBranchId()){	
						out.println(bc.getNoOfCopies());
				}
				}%>
			</td>
				<td><button type="button" class="btn btn-primary btn-lg" onclick="javascript:location.href='checkOutFinal?cardNo=<%=cardNo%>&bookId=<%=book.getBookId()%>&branchId=<%=branchId%>'">CheckOut</button></td>
			</tr>
		<%} %>
	</table>
	<a id="checkout" role="button" href="http://localhost:8080/LMSWeb/borrowerSelec?branchId=<%=branchId%>&cardNo=<%=cardNo%>" class="btn btn-warning">Back</button>
</div>