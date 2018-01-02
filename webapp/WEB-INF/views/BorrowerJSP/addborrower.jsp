<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>

<div class="container">
<form action="saveBorrower" class="w-50" style="margin:auto" method="post">
<div class="form-group">
    <label for="borrowerName">Borrower Name</label>
    <input type="text" class="form-control" name="name" aria-describedby="emailHelp" placeholder="Enter Name">
  </div>
  <div class="form-group">
    <label for="borrowerAddress">Borrower Address</label>
    <input type="text" class="form-control" name="address" placeholder="Enter Address">
  </div>
  <div class="form-group">
    <label for="borrowerPhone">Borrower Phone Number</label>
    <input type="text" class="form-control" name="phone" placeholder="Enter Phone Number">
  </div>
    <br/>
    <button type="submit" class="btn btn-primary float-md-right">Submit</button>
</form>
 </div>