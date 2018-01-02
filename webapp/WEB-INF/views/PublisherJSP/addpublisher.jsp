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
<form action="savePublisher" class="w-50" style="margin:auto" method="post">
<div class="form-group">
    <label for="publisherName">Publisher Name</label>
    <input type="text" class="form-control" name="publisherName" aria-describedby="emailHelp" placeholder="Enter Name">
  </div>
  <div class="form-group">
    <label for="publisherAddress">Publisher Address</label>
    <input type="text" class="form-control" name="publisherAddress" placeholder="Enter Address">
  </div>
  <div class="form-group">
    <label for="publisherPhone">Publisher Phone Number</label>
    <input type="text" class="form-control" name="publisherPhone" placeholder="Enter Phone Number">
  </div>
  
   <div class="form-group">
    <label for="exampleSelect2">Example multiple select</label>
    <select multiple class="form-control"  name="bookIds">
     <%for(Book b: books){ %>
		<option value="<%=b.getBookId()%>"> <%=b.getTitle() %></option>
	 <% } %>
    </select>
    <br/>
    <button type="submit" class="btn btn-primary float-md-right">Submit</button>
  </div>
  
</form>
</div>