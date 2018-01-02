<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>



<%
	List<Publisher> publishers = new ArrayList<>();
	publishers = (List<Publisher>) request.getAttribute("publishers");
%>

<div class="container">
<br/>
<a href="http://localhost:8080/lms/addpublisher1" class="pull-right btn btn-primary">New Publisher</a><br /> 
<div class="clear"></div>
	<h3>Find the List of Publishers in LMS.</h3>
	${statusMessage}
	
	
	<table class="table table-bordered table-hover">
		<tr>
			<th>#</th>
			<th>Publisher Name</th>
			<th>Books Published</th>
			
		</tr>
		<%
			for (Publisher pb : publishers) {
		%>
		<tr>
			<td>
				<%
					out.println(publishers.indexOf(pb) + 1);
				%>
			</td>
			<td><a href="editpublisher.jsp?publisherId=<%=pb.getPublisherId()%>"><%=pb.getPublisherName()%></a></td>
			<td>
				<%
					for (Book b : pb.getBooks()) {
							out.println(b.getTitle() + "|| ");
						}
				%>
			</td>
			<%-- <td><button type="button" class="btn btn-primary"
				onclick="javascript:location.href='editpublisher.jsp?publisherId=<%=pb.getPublisherId()%>'">Edit</button></td> --%>
		<td><button type="button" class="btn btn-danger"
				onclick="javascript:location.href='deletePublisher?publisherId=<%=pb.getPublisherId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
		
	</table>
</div>