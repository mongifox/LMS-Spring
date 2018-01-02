<%@include file="header.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>

<%
Integer cardNo = (Integer)request.getAttribute("cardNo");
Integer branchId = (Integer)request.getAttribute("branchId");
List<Book> books = new ArrayList<>();
books = (List<Book>) request.getAttribute("booksPerBranch");
%>
<div class="container">

<div class="row form-group">
 <!--        <div class="col-xs-12">
            <ul class="nav nav-pills nav-justified thumbnail setup-panel">
                <li class="disabled"><a href="#step-1">
                    <h4 class="list-group-item-heading">Step 1</h4>
                    <p class="list-group-item-text">Selecting Branch</p>
                </a></li>
                <li class="active"><a href="#step-2">
                    <h4 class="list-group-item-heading">Step 2</h4>
                    <p class="list-group-item-text">Borrower Selection</p>
                </a></li>
                <li class="disabled"><a href="#step-3">
                    <h4 class="list-group-item-heading">Step 3</h4>
                    <p class="list-group-item-text">Third step description</p>
                </a></li>
            </ul>
        </div> -->
	</div>
	<form style="text-align:center;">
	<br/> 
	  <a    id="checkout" role="button" class="btn btn-lg btn-primary  w-25" href="checkOut?branchId=<%=branchId%>&cardNo=<%=cardNo%>&books=<%=books%>">Checkout Book</a>
	  <br/>
	  <br/>
	  <a role="button" class="btn btn-lg btn-success w-25" href="returnBook?branchId=<%=branchId%>&cardNo=<%=cardNo%>&books=<%=books%>">Return Book</a>
	
	
	<%-- <div class="col-md-6 text-center">
		<a class="btn btn-lg btn-primary" href="checkOut?branchId=<%=branchId%>&cardNo=<%=cardNo%>&books=<%=books%>">Check Out Book</a><br /> 
	</div>
	<div class="col-md-6 text-center">
		<a class="btn btn-lg btn-success" href="returnBook?branchId=<%=branchId%>&cardNo=<%=cardNo%>&books=<%=books%>">Return Book</a><br />
	</div> --%>
	</form>
	
	<a id="checkout" role="button" href="http://localhost:8080/LMSWeb/cardNoValidation?cardNo=<%=cardNo%>" class="btn btn-warning">Back</button></a>
	
</div>
