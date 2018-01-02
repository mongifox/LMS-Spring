<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<%
	/* AdminService adminService = new AdminService();
	List<Author> authors = new ArrayList<>();
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = adminService.readAuthorsByPage(1, null);
	}
 */
 	List<Author> authors = new ArrayList<>();
 	authors = (List<Author>) request.getAttribute("authors");
	Integer totalAuthors = (Integer)request.getAttribute("totalAuthors"); // this will be constant from all calling locations
	out.println(totalAuthors);
	Integer totalPages = 1;
	if (totalAuthors % 8 > 0) {
		totalPages = (totalAuthors / 8) + 1;
	} else {
		totalPages = totalAuthors / 8;
	}
	out.println(totalPages);
%>

<script>
	function searchAuthors() {
		$.ajax({
			url : "searchAuthors",
			data : {
				searchStringAuthor : $('#searchStringAuthor').val()
			}
		}).done(function(data) {
			
			$('#authorsTable').html(data);
			var n=$('#authorsTable tr').length-1;
			console.log("total rows "+n);
			
			if(n%8>0)
			{
				n=(n/8)+1;
			}else
			{
				n= n/8;
			}
						
			$("#pages").empty();
		//	$("#pages").append('<li class="page-item"><a class="page-link" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span></a></li>');
			
			for(var i=1;i<=n;i++)
				{
					//console.log(" I "+i);
					$("#pages").append('<li class="page-item"><a class="page-link" href="pageAuthors?pageNo='+i+'s">'+i+'</a></li>');
				}
		//	$("#pages").append('<li class="page-item"><a class="page-link" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a></li>');
			
			
			$('#authorsTable tr:gt(8)').remove();
		});
	}
</script>


<div class="container">

 	<h3>List of Authors in LMS.</h3>

	${statusMessage} 
	
	<a href="http://localhost:8080/lms/addauthor1" class="pull-right btn btn-primary">New Author</a><br />
	<input class="form-control mr-sm-2 w-50" type="text"
		placeholder="Search by Author Name" aria-label="Search"
		id="searchStringAuthor" oninput="searchAuthors()">
	</form>
	<nav aria-label="Page navigation example">
		<ul class="pagination" id="pages">
			<!-- <li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li> -->
			<%
				for (int i = 1; i <= totalPages; i++) {
			%>
			<li class="page-item"><a class="page-link"
				href="http://localhost:8080/lms/pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<!-- <li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li> -->
		</ul>
	</nav>

	<table class="table table-bordered table-hover" id="authorsTable">
		<tr>
			<th>#</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th></th>
		</tr>
		<%
			for (Author a : authors) {
		%>
		<tr>
			<td>
				<%
					out.println(authors.indexOf(a) + 1);
				%>
			</td>
			<td><a href="http://localhost:8080/lms/editauthor1?authorId=<%=a.getAuthorId()%>"><%=a.getAuthorName()%></a></td>
			<td>
				<%
					for (Book b : a.getBooks()) {
							out.println(b.getTitle() + "|| ");
						}
				%>
			</td>
			<td><button type="button" class="btn btn-danger"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>