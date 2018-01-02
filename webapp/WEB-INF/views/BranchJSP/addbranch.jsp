<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="../header.html"%>
<%
List<Book> books = new ArrayList<>();
books = (List<Book>) request.getAttribute("books");
%>
<div class="container">
	<form style="text-align: center" action="saveBranch" method="post">
		<div class="form-group">
				<label for="exampleInputEmail1">Branch Name</label> <input
				type="text" class="mx-auto  w-25 form-control"
				form-control" name="branchName" placeholder="Enter Branch name">
		</div>
		<div class="form-group">
				<label for="exampleInputEmail1">Branch Address</label> <input
				type="text" class="mx-auto  w-25 form-control"
				form-control" name="branchAddress" placeholder="Enter Branch address">
		</div>
		<br />
		<div class="row">
		<div class="col">
			<div class="form-group">
				<label for="exampleSelect2">Select the books to Add to the Branch</label> <select
					multiple="multiple" class="form-control" size="5" name="bookIds">
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
		<button type="submit" class="btn btn-success float-md-right">SAVE</button>
	</form>
</div>

		

<%-- <!--  <%for(Book b: books){ %>
			
			<%if(b.getPublisher().getPublisherId()!=null){}else{%>
				<option value="<%=b.getBookId()%>"> <%=b.getTitle() %></option>
			<%}%>
			<% } %>--> --%>