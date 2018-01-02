<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>
<%
Book book = (Book) request.getAttribute("book");
%>
<div class="container">
	<%-- <form action="editBook" method="post">
		<h3>Enter the New Book Title</h3>
		<input type="text" name="title" value="<%=book.getTitle()%>"> <br />
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>"> <br />
		<button type="submit">Save</button>
	</form>
	 --%>
	<div class="form-group">
	<form style="text-align: center" action="editBook" method="post">
			<label for="exampleInputEmail1">Book Title</label> <input type="text"
				class="mx-auto  w-25 form-control" form-control" name="title" value="<%=book.getTitle()%>"
				placeholder="Enter title"> <br />
				<input type="hidden" name="bookId" value="<%=book.getBookId()%>"> 
				<button type="submit" class="btn btn-success float-md-center">SAVE</button>
				</form>
		</div>
	
</div>