package com.gcit.lms;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	Author author = new Author();
	
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public String authors(Locale locale, Model model) {
		List<Author> authors = new ArrayList<>();
		authors = adminService.readAuthorsByPage(1, null);
		model.addAttribute("authors", authors);
		Integer totalAuthors = adminService.getAuthorsCount();
		model.addAttribute("totalAuthors", totalAuthors);
		return "AuthorJSP/viewauthors";
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(Locale locale, Model model) {
		List<Book> books = new ArrayList<>();
		books = adminService.readBooksByPage(1, null);
		model.addAttribute("books", books);
		Integer totalBooks = adminService.getBooksCount();
		model.addAttribute("totalBooks", totalBooks);
		return "BookJSP/viewbooks";
	}
	
	@RequestMapping(value = "/addauthor1", method = RequestMethod.GET)
	public String addAuthors(Locale locale, Model model) {
		List<Book> books = new ArrayList<>();
		books = adminService.readBooks();
		model.addAttribute("books", books);
		return "AuthorJSP/addauthor";
		
	}
	
	@RequestMapping(value = "/saveAuthor", method = RequestMethod.POST)
	public String viewAuthors(Locale locale, Model model, @RequestParam("authorName") String authorName ,@RequestParam("bookIds") String[] bookIdForAuthor) {	
		author.setAuthorName(authorName);
		// adding the associated books to the author
		List<Book> books = new ArrayList<>();
		if (bookIdForAuthor != null) {
			for (String id : bookIdForAuthor) {
				Book b = new Book();
				b.setBookId(Integer.parseInt(id));
				books.add(b);
			}
		}
		author.setBooks(books);
		adminService.saveAuthor(author);
		List<Author> authors = new ArrayList<>();
		authors = adminService.readAuthorsByPage(1, null);
		model.addAttribute("authors", authors);
		Integer totalAuthors = adminService.getAuthorsCount();
		model.addAttribute("totalAuthors", totalAuthors);
		return "AuthorJSP/viewauthors";
	}
	
	@RequestMapping(value = "/addbook1", method = RequestMethod.GET)
	public String addBookss(Locale locale, Model model) {
		List<Publisher> publishers = adminService.readPublishers();
		List<Author> authors = adminService.readAllAuthors();
		List<Genre> genres = adminService.readGenres();
		model.addAttribute("publishers", publishers);
		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);
		return "BookJSP/addbook";
		
	}
	
	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public String viewBooks(Locale locale, Model model, @RequestParam("title") String title ,@RequestParam("authorIds") String[] authorIdForBook, @RequestParam("genreIds") String[] genreIdForBook, @RequestParam("pubId") String[] pubIdForBook) {	
		Book book = new Book();
		book.setTitle(title);
			List<Author> authors = new ArrayList<>();
			if (authorIdForBook != null) {
				for (String authorId : authorIdForBook) {
					Author au = new Author();
					au.setAuthorId((Integer.parseInt(authorId)));
					authors.add(au);
				}
			}
			book.setAuthors(authors);

			List<Genre> genres = new ArrayList<>();
			if (genreIdForBook != null) {
				for (String genreId : genreIdForBook) {
					Genre gen = new Genre();
					gen.setGenreId((Integer.parseInt(genreId)));
					genres.add(gen);
				}
			}
			book.setGenres(genres);

			Publisher publisher = new Publisher();
			if (pubIdForBook != null) {
				for (String pubId : pubIdForBook) {
					if (pubId != null)
						publisher.setPublisherId(Integer.parseInt(pubId));
				}
			}
			book.setPublisher(publisher);
			adminService.saveBook(book);
			List<Book> books = new ArrayList<>();
			books = adminService.readBooksByPage(1, null);
			model.addAttribute("books", books);
			Integer totalBooks = adminService.getBooksCount();
			model.addAttribute("totalBooks", totalBooks);
			return "BookJSP/viewbooks";
	}
	
	@RequestMapping(value = "/editbook1", method = RequestMethod.GET)
	public String editBookInitial(Locale locale, Model model, @RequestParam("bookId") Integer bookId) {
		Book book = adminService.readBookByPk(bookId);
		model.addAttribute("book", book);
		return "BookJSP/editbook";
	}
	
	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public String editBook(Locale locale, Model model, @RequestParam("title") String title ,@RequestParam("bookId") Integer bookId) {	
		Book book = new Book();
		book.setTitle(title);
		book.setBookId(bookId);
		adminService.saveBook(book);
		List<Book> books = new ArrayList<>();
		books = adminService.readBooksByPage(1, null);
		model.addAttribute("books", books);
		Integer totalBooks = adminService.getBooksCount();
		model.addAttribute("totalBooks", totalBooks);
		return "BookJSP/viewbooks";
	}
	
	
	@RequestMapping(value = "/editauthor1", method = RequestMethod.GET)
	public String editAuthorInitial(Locale locale, Model model, @RequestParam("authorId") Integer authorId) {
		Author author = adminService.readAuthorByPk(authorId);
		model.addAttribute("author", author);
		return "AuthorJSP/editauthor";
		
	}
	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String editAuthor(Locale locale, Model model, @RequestParam("authorName") String authorName ,@RequestParam("authorId") Integer authorId) {	
			author.setAuthorName(authorName);
			author.setAuthorId(authorId);
			adminService.saveAuthor(author);
			List<Author> authors = new ArrayList<>();
			authors = adminService.readAuthorsByPage(1, null);
			model.addAttribute("authors", authors);
			Integer totalAuthors = adminService.getAuthorsCount();
			model.addAttribute("totalAuthors", totalAuthors);
		return "AuthorJSP/viewauthors";
	}
	
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String deleteAuthor(Locale locale, Model model, @RequestParam("authorId") Integer authorId) {	
		if (authorId != null) {
				Author author = new Author();
				author.setAuthorId(authorId);
				adminService.deleteAuthor(author);		
		}
		List<Author> authors = new ArrayList<>();
		authors = adminService.readAuthorsByPage(1, null);
		model.addAttribute("authors", authors);
		Integer totalAuthors = adminService.getAuthorsCount();
		model.addAttribute("totalAuthors", totalAuthors);
		return "AuthorJSP/viewauthors";
	}
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(Locale locale, Model model, @RequestParam("bookId") Integer bookId) {		
		if (bookId != null) {
			Book book = new Book();
			book.setBookId(bookId);
			adminService.deleteBook(book);		
		}
		List<Book> books = new ArrayList<>();
		books = adminService.readBooksByPage(1, null);
		model.addAttribute("books", books);
		Integer totalBooks = adminService.getBooksCount();
		model.addAttribute("totalBooks", totalBooks);
		return "BookJSP/viewbooks";
	}
	
	@RequestMapping(value = "/pageAuthors", method = RequestMethod.GET)
	public String pageAuthors(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo ) {	
	
		model.addAttribute("authors", adminService.readAuthorsByPage(pageNo, null) );
		//request.setAttribute("authors", adminService.readAuthorsByPage(pageNo, null));
		System.out.println("Here 1");
		Integer totalAuthors = adminService.getAuthorsCount();
		System.out.println("Total Authors: " + totalAuthors);
		model.addAttribute("totalAuthors", totalAuthors);
		
		return "Author/viewauthors";
	}
	
	@RequestMapping(value = "/pageBooks", method = RequestMethod.GET)
	public String pageBooks(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo ) {	
		//request.setAttribute("books", adminService.readBooksByPage(pageNo, null));
		model.addAttribute("books", adminService.readBooksByPage(pageNo, null) );
		List<Book> books = new ArrayList<>();
		books = adminService.readBooksByPage(1, null);
		model.addAttribute("books", books);
		Integer totalBooks = adminService.getBooksCount();
		model.addAttribute("totalBooks", totalBooks);
		
		return "BookJSP/viewbooks";
	}
	
	/*@RequestMapping(value = "/searchAuthors", method = RequestMethod.GET)
	public String pageAuthors(Locale locale, Model model, @RequestParam("searchStringAuthor") String searchString ) {	
		try {
			List<Author> authors = adminService.readAuthorsByPage(1, searchString);
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("<tr><th>#</th><th>Author Name</th><th>Books Written</th><th>Delete</th></tr>");
			for (Author a : authors) {
				strBuf.append("<tr><td>"+authors.indexOf(a)+1+"</td><td><a href='editauthor.jsp?authorId=" + a.getAuthorId()+"'>" + a.getAuthorName()+"</a></td><td>");
				for (Book b : a.getBooks()) {
					strBuf.append(b.getTitle()+ " || ");
				}
				
				strBuf.append("<td><button type='button' class='btn btn-danger' onclick='javascript:location.href=\"deleteAuthor?authorId="+a.getAuthorId()+"\"'>Delete</button></td></tr>");
			}
			response.getWriter().append(strBuf.toString());
			isAjax = Boolean.TRUE;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "Author/viewauthors";
	}*/
	
	//routing to publishers view (main page)
	@RequestMapping(value = "/publishers", method = RequestMethod.GET)
	public String publishers(Locale locale, Model model) {
		List<Publisher> publishers = new ArrayList<>();
		publishers = adminService.readPublishers();
		model.addAttribute("publishers", publishers);
		return "PublisherJSP/viewpublisher";
		
	}
	
	@RequestMapping(value = "/addpublisher1", method = RequestMethod.GET)
	public String addPublishers(Locale locale, Model model) {
		List<Book> books = new ArrayList<>();
		books = adminService.readBooks();
		model.addAttribute("books", books);
		return "PublisherJSP/addpublisher";
		
	}
	
	//add publisher
	@RequestMapping(value = "/savePublisher", method = RequestMethod.POST)
	public String viewPublishers(Locale locale, Model model, @RequestParam("publisherName") String publisherName ,@RequestParam("publisherAddress") String publisherAddress ,@RequestParam("publisherPhone") String publisherPhone ,@RequestParam("bookIds") String[] bookIdsForPub) {	
			Publisher pub = new Publisher();
			pub.setPublisherName(publisherName);
			pub.setPublisherAddress(publisherAddress);
			pub.setPublisherPhone(publisherPhone);
			for (String id : bookIdsForPub) {
				System.out.println("" + Integer.parseInt(id));
			}
			List<Book> books = new ArrayList<>();
			if (bookIdsForPub != null) {
				for (String id : bookIdsForPub) {
					System.out.println("Entering here in Controller");
					Book b = new Book();
					b.setBookId(Integer.parseInt(id));
					//b.setPublisher(pub);
					books.add(b);
				}
			}	
			pub.setBooks(books);
			adminService.savePublisher(pub);
			List<Publisher> publishers = new ArrayList<>();
			publishers = adminService.readPublishers();
			model.addAttribute("publishers", publishers);

		return "PublisherJSP/viewpublisher";
	}
	
	//edit publisher
	@RequestMapping(value = "/editPublisher", method = RequestMethod.POST)
	public String editPublisher(Locale locale, Model model, @RequestParam("publisherId") Integer publisherId, @RequestParam("publisherName") String publisherName ,@RequestParam("publisherAddress") String publisherAddress ,@RequestParam("publisherPhone") String publisherPhone) {	
		Publisher pub = new Publisher();
		pub.setPublisherName(publisherName);
		pub.setPublisherAddress(publisherAddress);
		pub.setPublisherPhone(publisherPhone);
		pub.setPublisherId(publisherId);
		
			adminService.savePublisher(pub);
			List<Publisher> publishers = new ArrayList<>();
			publishers = adminService.readPublishers();
			model.addAttribute("publishers", publishers);
		
		return "PublisherJSP/viewpublisher";
	}
	
	//delete publisher
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String deletePublisher(Locale locale, Model model, @RequestParam("publisherId") Integer publisherId) {	
		if (publisherId != null) {
				Publisher publisher = new Publisher();
				publisher.setPublisherId(publisherId);
				adminService.deletePublisher(publisher);
				List<Publisher> publishers = new ArrayList<>();
				publishers = adminService.readPublishers();
				model.addAttribute("publishers", publishers);
			}
		
		return "PublisherJSP/viewpublisher";
	}
	
	//routing to branches view (main page)
	@RequestMapping(value = "/branches", method = RequestMethod.GET)
	public String branches(Locale locale, Model model) {
		List<Branch> branches = new ArrayList<>();
		branches = adminService.readBranches();
		model.addAttribute("branches", branches);
		return "BranchJSP/viewbranch";
	}
	
	@RequestMapping(value = "/addbranch1", method = RequestMethod.GET)
	public String addBranches(Locale locale, Model model) {
		List<Book> books = new ArrayList<>();
		books = adminService.readBooks();
		model.addAttribute("books", books);
		return "BranchJSP/addbranch";
		
	}
	
	//add branch
	@RequestMapping(value = "/saveBranch", method = RequestMethod.POST)
	public String viewBranches(Locale locale, Model model, @RequestParam("branchName") String branchName ,@RequestParam("branchAddress") String branchAddress,@RequestParam("bookIds") String[] bookIdsForBranch) {	
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
			for(String s : bookIdsForBranch) {
				System.out.println(s);
			}
			List<Book> books = new ArrayList<>();
			
			if (bookIdsForBranch != null) {
				for (String id : bookIdsForBranch) {
					Book b = new Book();
					b.setBookId(Integer.parseInt(id));
					books.add(b);
				}
			}	
			branch.setBooks(books);	
			adminService.saveBranch(branch);
			List<Branch> branches = new ArrayList<>();
			branches = adminService.readBranches();
			model.addAttribute("branches", branches);
			return "BranchJSP/viewbranch";
		}
	
	//edit Branch
	@RequestMapping(value = "/editBranch", method = RequestMethod.POST)
	public String editBranch(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName ,@RequestParam("branchAddress") String branchAddress) {	
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		branch.setBranchId(branchId);
		//adminService.savePublisher(pub);
		adminService.saveBranch(branch);
		List<Branch> branches = new ArrayList<>();
		branches = adminService.readBranches();
		model.addAttribute("branches", branches);
		return "BranchJSP/viewbranch";
	}
	
	//delete branch
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
	public String deleteBranch(Locale locale, Model model, @RequestParam("branchId") Integer branchId) {	
		if (branchId != null) {
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			adminService.deleteBranch(branch);
			List<Branch> branches = new ArrayList<>();
			branches = adminService.readBranches();
			model.addAttribute("branches", branches);
		}
			
		return "BranchJSP/viewbranch";
	}
	
	//routing to borrowers view (main page)
	@RequestMapping(value = "/borrowers", method = RequestMethod.GET)
	public String borrowers(Locale locale, Model model) {
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = adminService.readAllBorrowers();
		model.addAttribute("borrowers", borrowers);
		return "BorrowerJSP/viewborrower";
	}
	
	@RequestMapping(value = "/addborrower1", method = RequestMethod.GET)
	public String addBorrower(Locale locale, Model model) {
		return "BorrowerJSP/addborrower";
		
	}
	
	//add borrower
	@RequestMapping(value = "/saveBorrower", method = RequestMethod.POST)
	public String viewBorrowers(Locale locale, Model model, @RequestParam("name") String name ,@RequestParam("address") String address, @RequestParam("phone") String phone) {	
		Borrower borrower = new Borrower();
		borrower.setName(name);
		borrower.setAddress(address);
		borrower.setPhone(phone);
		adminService.saveBorrower(borrower);
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = adminService.readAllBorrowers();
		model.addAttribute("borrowers", borrowers);
		return "BorrowerJSP/viewborrower";
	}
	
	@RequestMapping(value = "/editBorrower1", method = RequestMethod.GET)
	public String editBorrowerInitial(Locale locale, Model model, @RequestParam("cardNo") Integer cardNo) {
		Borrower borrower = adminService.readBorrowerByPk(cardNo);
		model.addAttribute("borrower", borrower);
		return "BorrowerJSP/editborrower";
		
	}
	
	//edit borrower
	@RequestMapping(value = "/editBorrower", method = RequestMethod.POST)
	public String editBorrower(Locale locale, Model model, @RequestParam("cardNo") Integer cardNo, @RequestParam("name") String name ,@RequestParam("address") String address, @RequestParam("phone") String phone) {	
		Borrower borrower = new Borrower();
		borrower.setName(name);
		borrower.setAddress(address);
		borrower.setPhone(phone);
		borrower.setCardNo(cardNo);
		adminService.saveBorrower(borrower);
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = adminService.readAllBorrowers();
		model.addAttribute("borrowers", borrowers);
		return "BorrowerJSP/viewborrower";
	}
	
	//delete branch
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.GET)
	public String deleteBorrower(Locale locale, Model model, @RequestParam("cardNo") Integer cardNo) {	
		if (cardNo != null) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(cardNo);
			adminService.deleteBorrower(borrower);	
		}
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = adminService.readAllBorrowers();
		model.addAttribute("borrowers", borrowers);
			
		return "BorrowerJSP/viewborrower";
	}
	
	//routing to override module view (main page)
	@RequestMapping(value = "/override", method = RequestMethod.GET)
	public String overrideMainModule(Locale locale, Model model) {
		List<BookLoans> bookLoans = new ArrayList<>();
		bookLoans = adminService.readLoans();
		HashMap<Integer, Book> mapsBooks = new HashMap<>();
		HashMap<Integer, Branch> mapsBranches = new HashMap<>();
		HashMap<Integer, Borrower> mapsBorrowers = new HashMap<>();
		for(BookLoans b : bookLoans) {
			mapsBooks.put(b.getBookId(), adminService.readBookByPk(b.getBookId()));
			mapsBranches.put(b.getBranchId(), adminService.readBranchByPk(b.getBranchId()));
			mapsBorrowers.put(b.getCardNo(), adminService.readBorrowerByPk(b.getCardNo()));
		}
		
		model.addAttribute("bookLoans", bookLoans);
		model.addAttribute("mapsBooks", mapsBooks);
		model.addAttribute("mapsBranches", mapsBranches);
		model.addAttribute("mapsBorrowers", mapsBorrowers);
		return "OverrideJSP/duedate";
	}

	//routing details to orverride final stage
	@RequestMapping(value = "/overrideDate", method = RequestMethod.GET)
	public String overrideDueDate(Locale locale, Model model, @RequestParam("bookId") Integer bookId, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) {	
		model.addAttribute("bookId", bookId);
		model.addAttribute("branchId", branchId);
		model.addAttribute("cardNo", cardNo);
		return "OverrideJSP/overrideDate";
	}

	//changing the duedate final stage
	@RequestMapping(value = "/saveDate", method = RequestMethod.POST)
	public String overrideFinalStage(Locale locale, Model model,@RequestParam("duedate") String duedate1, @RequestParam("bookId") Integer bookId, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date1;
		System.out.println(bookId);
		System.out.println(branchId);
		System.out.println( cardNo);
	 	try {
				date1 = sdf.parse(duedate1);
				Timestamp duedate = new java.sql.Timestamp(date1.getTime());
				adminService.overRideDueDate(bookId, branchId, cardNo, duedate);			
			} catch (ParseException e2) {
				e2.printStackTrace();
		}
	 	List<BookLoans> bookLoans = new ArrayList<>();
		bookLoans = adminService.readLoans();
		HashMap<Integer, Book> mapsBooks = new HashMap<>();
		HashMap<Integer, Branch> mapsBranches = new HashMap<>();
		HashMap<Integer, Borrower> mapsBorrowers = new HashMap<>();
		for(BookLoans b : bookLoans) {
			mapsBooks.put(b.getBookId(), adminService.readBookByPk(b.getBookId()));
			mapsBranches.put(b.getBranchId(), adminService.readBranchByPk(b.getBranchId()));
			mapsBorrowers.put(b.getCardNo(), adminService.readBorrowerByPk(b.getCardNo()));
		}
		
		model.addAttribute("bookLoans", bookLoans);
		model.addAttribute("mapsBooks", mapsBooks);
		model.addAttribute("mapsBranches", mapsBranches);
		model.addAttribute("mapsBorrowers", mapsBorrowers);
			
		return "OverrideJSP/duedate";
	}

}
