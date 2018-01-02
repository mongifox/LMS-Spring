<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>
<%
List<Book> books = new ArrayList<>();
books = (List<Book>) request.getAttribute("books");
%>
<div class="container">
	<form style="text-align: center" action="saveAuthor" method="post">
		<div class="form-group">
			<label for="exampleInputEmail1">Author Name</label> <input
				type="text" class="mx-auto  w-25 form-control"
				form-control" name="authorName" placeholder="Enter name">
		</div>
		<br />
		<div class="row">
		<div class="col">
			<div class="form-group">
				<label for="exampleSelect2">Select Books</label> 
				<select
					multiple="multiple" class="form-control  w-50 mx-auto " size="5" name="bookIds">
					<%
						for (Book b : books) {
					%>
					<option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
					<%
						}
					%>
				</select>

			</div>
		</div>
		</div>
		<button type="submit" class="btn btn-primary btn-lg">SAVE</button>
	</form>

</div>