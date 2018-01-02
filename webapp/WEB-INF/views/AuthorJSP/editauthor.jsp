<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>
<%
Author author = (Author) request.getAttribute("author");
%>
<div class="container">
	<form action="editAuthor" method="post">
		<h3>Enter Author Name to Edit</h3>
		<input type="text" name="authorName" value="<%=author.getAuthorName()%>"> <br />
		<input type="hidden" name="authorId" value="<%=author.getAuthorId()%>"> <br />
		<button type="submit" class="btn btn-success float-md-center">Save</button>
	</form>
</div>