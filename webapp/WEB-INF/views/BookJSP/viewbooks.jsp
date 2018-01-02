<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%
	/* AdminService adminService = new AdminService();
	List<Book> books = new ArrayList<>();

	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = adminService.readBooksByPage(1, null);
	} */
	
	List<Book> books = new ArrayList<>();
	books = (List<Book>) request.getAttribute("books");
	Integer totalBooks = (Integer)request.getAttribute("totalBooks");
	
	Integer totalPages = 1;
	if (totalBooks % 8 > 0) {
		totalPages = (totalBooks / 8) + 1;
	} else {
		totalPages = totalBooks / 8;
	}
	
	
%>

<script>
	function searchBooks() {
		$.ajax({
			url : "searchBooks",
			data : {
				searchStringBook : $('#searchStringBook').val()
			}
		}).done(function(data) {
			$('#booksTable').html(data);
			
			var n=$('#booksTable tr').length-1;
			console.log("total rows "+n);
			
			if(n%8>0)
			{
				n=(n/8)+1;
			}else
			{
				n= n/8;
			}
			$("#pages").empty();
			//$("#pages").append('<li class="page-item"><a class="page-link" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span></a></li>');
			for(var i=1;i<=n;i++)
			{
				//console.log(" I "+i);
				$("#pages").append('<li class="page-item"><a class="page-link" href="pageBooks?pageNo='+i+'s">'+i+'</a></li>');
			}
		//	$("#pages").append('<li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a></li>');
			
			
			$('#booksTable tr:gt(8)').remove();
		});
	}
</script>



<div class="container">
	
	<h3>List of Books in LMS</h3>
	${statusMessage}
	<a href="http://localhost:8080/lms/addbook1" class="pull-right btn btn-primary">New Book</a><br />

	<input class="form-control mr-sm-2 w-50" type="text"
		placeholder="Search by Book Title" aria-label="Search"
		id="searchStringBook" oninput="searchBooks()">
	</form>
	<nav aria-label="Page navigation example">
		<ul class="pagination" id="pages">
		<!-- 	<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li> -->
			<%
				for (int i = 1; i <= totalPages; i++) {
			%>
			<li class="page-item"><a class="page-link"
				href="pageBooks?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<!-- <!-- <li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li> -->
		</ul>
	</nav>
	
	<table class="table table-bordered table-hover" id="booksTable">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>Authors</th>
			<th>Genres</th>
			<!-- <th>Publishers</th> -->
			<th>Delete</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr><td><%out.println(books.indexOf(b) + 1);%>
		</td><td><a href="http://localhost:8080/lms/editbook1?bookId=<%=b.getBookId()%>"><%=b.getTitle()%></a></td>
		<td><%for (Author a : b.getAuthors()) {out.println(a.getAuthorName() + "|| ");}%></td>
<td>
				<%
					for (Genre g : b.getGenres()) {
							out.println(g.getGenreName() + "|| ");
						}
				%>
			</td>
			<%-- <td>
				<%
					Publisher temp = b.getPublisher();
						if (temp != null) {
							out.println(temp.getPublisherName());
						}
				%>
			</td> --%>
			<%-- <td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editbook.jsp?bookId=<%=b.getBookId()%>'">Edit</button></td>
				 --%>
				 <!--  -->
			<td><button type="button" class="btn btn-danger"
					onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>