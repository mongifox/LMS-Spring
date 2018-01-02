<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@include file="header.html"%>

<%
	//LibrarianService libService = new LibrarianService();
	//LibrarianService libService = new LibrarianService();
	List<Book> books = new ArrayList<>();
	HashMap<Integer, Integer> mapBooks = new HashMap<>();
	Integer branchId = (Integer) request.getAttribute("branchId");
	Branch branch = new Branch();
%>

<%
		books = (List<Book>) request.getAttribute("books");
		mapBooks = (HashMap<Integer, Integer>)request.getAttribute("mapBooks");
		branch = (Branch) request.getAttribute("branch");
%>
<div class="container">
	<h3>Book Copies for branch <%=branch.getBranchName()%></h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>No of Copies</th>
			
		</tr>
		<%for (Book book : books) {%>
		<tr>
			<td><%out.println(books.indexOf(book) + 1);%></td>
			<td><%=book.getTitle()%></td>
			<td>
				<form action="saveCopies" method="post" >			
					<input style="width:19%;float:left;" type="number" name="noOfCopies" class="form-control" value="<%=mapBooks.get(book.getBookId())%>" min="0" >
					<input type="hidden" name = "bookId" value="<%=book.getBookId()%>">
					<input type="hidden" name = "branchId" value="<%=branchId%>">
					<button style="float:left;margin-left:1em" type="submit" class="btn btn-primary btn-md">Save Book</button>
				</form> 
			</td>
		</tr>
		<%}%>
	</table>
</div>


<script>
function update_book()
{
	$('#txt_name').val();
}

</script>
