<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="../header.html"%>

<%
	
	List<BookLoans> bookLoans = new ArrayList<>();
	Book book = null;	
	Branch branch = null;
	Borrower borrower = null;
	bookLoans = (List<BookLoans>) request.getAttribute("bookLoans");
	HashMap<Integer, Book> mapsBooks = new HashMap<>();
	mapsBooks = (HashMap<Integer, Book>) request.getAttribute("mapsBooks");
	HashMap<Integer, Branch> mapsBranches = new HashMap<>();
	mapsBranches = (HashMap<Integer, Branch>) request.getAttribute("mapsBranches");
	HashMap<Integer, Borrower> mapsBorrowers = new HashMap<>();
	mapsBorrowers = (HashMap<Integer, Borrower>) request.getAttribute("mapsBorrowers");
%>
<div class="container">
	<h3>List of Book Loans in LMS.</h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>Branch Name</th>
			<th>Borrower Name</th>
			<th>Date out</th>
			<th>Due date</th>
			<th>Override</th>
		</tr>
			<%
			for(BookLoans b: bookLoans){
				if(b.getDateIn()==null){
			%>
				<tr>
			<td><%=bookLoans.indexOf(b) + 1%></td>
			<td><%
				book = mapsBooks.get(b.getBookId());
				//book = adminService.readBookByPk(b.getBookId());
				if(book!=null){
					out.println(book.getTitle());
				}
				%>
			</td>
			<td><%
				branch = mapsBranches.get(b.getBranchId());
				if(branch!=null){
					out.println(branch.getBranchName());
				}
				%>
			</td>
			<td><%
				 borrower = mapsBorrowers.get(b.getCardNo());
				if(borrower!=null){
					out.println(borrower.getName());
				}
				%>
			</td>
			<td><%=b.getDateOut()%></td>
			<td><%=b.getDueDate()%></td>
			<td><button type="button"
					onclick="javascript:location.href='overrideDate?bookId=<%=b.getBookId()%>&branchId=<%=b.getBranchId()%>&cardNo=<%=b.getCardNo()%>'"
					class="btn btn-primary btn-sm">Override</button>
			</td>
		</tr>
		<%
			}
		}
		%>
	</table>
</div>