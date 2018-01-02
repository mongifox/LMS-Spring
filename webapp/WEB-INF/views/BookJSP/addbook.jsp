<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="header.html"%>
<%	
List<Publisher> publishers = new ArrayList<>();
publishers = (List<Publisher>) request.getAttribute("publishers");
List<Author> authors = new ArrayList<>();
authors = (List<Author>) request.getAttribute("authors");
List<Genre> genres = new ArrayList<>();
genres = (List<Genre>) request.getAttribute("genres");
%>
<div class="container">
	<form style="text-align: center" action="saveBook" method="post">

		<div class="form-group">
			<label for="exampleInputEmail1">Book Title</label> <input type="text"
				class="mx-auto  w-25 form-control" form-control" name="title"
				placeholder="Enter name">
		</div>
		<br />

		<div class="row">
			<div class="col">
				<div class="form-group">
					<label for="exampleSelect2">Select the Authors</label>
					<select multiple="multiple" class="form-control" size="5"
						name="authorIds">
						<%
							for (Author a : authors) {
						%>
						<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="exampleSelect2">Select Genres</label> <select
						multiple="multiple" class="form-control" size="5" name="genreIds">
						<%
							for (Genre g : genres) {
						%>
						<option value="<%=g.getGenreId()%>"><%=g.getGenreName()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="col">
				<div class="form-group">
					<label for="exampleSelect2">Select a Publisher</label> <select
						size="5" class="form-control" name="pubId">
						<%
							for (Publisher p : publishers) {
						%>
						<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
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