<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="../header.html"%>
<%
	Integer bookId = (Integer)request.getAttribute("bookId");
	Integer branchId = (Integer)request.getAttribute("branchId");
	Integer cardNo = (Integer)request.getAttribute("cardNo");
%>
<div class="container">
	
	<form style="text-align: center" action="saveDate" method="post">
		<input type="hidden" name="bookId"  value="<%=bookId%>"/>
		<input type="hidden" name="branchId"  value="<%=branchId%>"/>
		<input type="hidden" name="cardNo"  value="<%=cardNo%>"/>
		<div class="form-group">
			<label for="exampleInputEmail1">Enter Due Date</label> <input
				type="date" class="mx-auto  w-25 form-control"
				form-control" name="duedate" placeholder="yyyy-mm-dd">
		</div>
	<button type="submit" class="btn btn-success float-md-right">SAVE</button>
	</form>
</div>