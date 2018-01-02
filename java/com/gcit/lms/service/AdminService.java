package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

@Configurable
public class AdminService {
	
	@Autowired 
	AuthorDAO aDao;
	
	@Autowired
	BookDAO bDao;
	
	@Autowired
	BranchDAO brDao;
	
	@Autowired
	PublisherDAO pDao;
	
	@Autowired
	BorrowerDAO brwDao;
	
	@Autowired
	BookLoansDAO blDao;
	
	@Autowired
	BookCopiesDAO bcDao;
	
	@Autowired
	GenreDAO gDao;
	
	@Transactional
	public void saveAuthor(Author author) {
		try {
			if (author.getAuthorId() != null) {
				aDao.updateAuthor(author);
			} else {
				Integer authorId = aDao.addAuthorWithID(author);
				author.setAuthorId(authorId);
				aDao.addAuthorBooks(author);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void savePublisher(Publisher publisher) {
		try {
			if (publisher.getPublisherId() != null) {
				System.out.println("Entering");
				pDao.updatePublisher(publisher);
			} else {
				System.out.println("Entering without id");
				Integer publisherId = pDao.addPublisherWithID(publisher);
				System.out.println("PUBID : " + publisherId);
				publisher.setPublisherId(publisherId);
				for(Book b : publisher.getBooks()) {
					//b.setPublisher(publisher);
					System.out.println("Entering inside books for publisher addition");
					//bDao.addBookWithPublisherByID(b);
					bDao.addBookPublisher(b, publisherId);
					System.out.println("exiting inside books for publisher addition");
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void saveBranch(Branch branch)  {
		try {
			if (branch.getBranchId() != null) {
				System.out.println("Entering Branch with ID ...");
				brDao.updateBranch(branch);
			} else {
				System.out.println("Entering Branch without ID...");
				Integer branchId = brDao.addBranchWithID(branch);
				System.out.println("ID gen is : " + branchId);
				branch.setBranchId(branchId);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void saveBorrower(Borrower borrower) {
		try {
			if (borrower.getCardNo() != null) {
				System.out.println("Entering with ID");
				brwDao.updateBorrowerAllFields(borrower);
			} else {
				System.out.println("Entering without id");
				Integer cardNo = brwDao.addBorrowerWithID(borrower);
				System.out.println("PUBID" + cardNo);
				borrower.setCardNo(cardNo);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Author> readAuthorsByPage(Integer pageNo, String searchString) {
		List<Author> authors = null;
		try {
			if(searchString!=null){
				return aDao.readAuthorsByName(searchString);
			}
			System.out.println("--- " + aDao);
			System.out.println("Entering read authors service");
			System.out.println("Outside");
			authors = aDao.readAllAuthorsByPg(pageNo);
			for (Author author : authors) {
				author.setBooks(bDao.readBooksByAuthor(author));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}
	
	public Integer getAuthorsCount() {
		Integer authorCount = 0;
		try {
			authorCount = aDao.getAuthorsCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return authorCount;
	}
	
	public List<Book> readBooksByPage(Integer pageNo, String searchString) {
		List<Book> books = null;
		try {
			if(searchString!=null){
				return bDao.readBooksByName(searchString);
			}
			System.out.println("Entering read book service");
			books = bDao.readAllBooksByPg(pageNo);
			for(Book book : books) {
				book.setAuthors(aDao.readAuthorByBooks(book));
				book.setGenres(gDao.readGenresByBook(book));
				System.out.println("Publisher _____");
				//book.setPublisher(pDao.readPublisherByPK(book);
				System.out.println("Publisher ****");
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public Integer getBooksCount() {
		Integer bookCount = 0;
		try {
			bookCount = bDao.getBooksCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return bookCount;
	}
	
	public List<Publisher> readPublishers() {
		List<Publisher> publishers = null;
		try {
			publishers = pDao.readAllPublisher();
			for(Publisher publisher : publishers) {
				publisher.setBooks(bDao.readBooksByPublisher(publisher));
			}
			//p.setBooks(bdao.readFirstLevel("SELECT * from tbl_book WHERE pubId = ? ",
		//	new Object[] { p.getPublisherId() }));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return publishers;
	}
	
	public List<Borrower> readAllBorrowers() {
		List<Borrower> borrowers = null;
		try {
			borrowers = brwDao.readAllBorrower();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return borrowers;
		
	}
	
	public List<BookLoans> readLoans() {
		List<BookLoans> bookLoans = null;
		try {
			bookLoans = blDao.readBookLoansByDateIn();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return bookLoans;
	}
	
	public Author readAuthorByPk(Integer authorId)  {
		Author author = null;
		try {
			author = aDao.readAuthorByPK(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return author;
	}
	
	public Book readBookByPk(Integer bookId)  {
		Book book = null;
		try {
			book =  bDao.readBookByPK(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public Publisher readPublisherByPk(Integer publisherId)  {
		Publisher publisher = null;
		try {
			publisher =  pDao.readPublisherByPK(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return publisher;
	}
	
	public Branch readBranchByPk(Integer branchId)  {
	Branch branch = null;
		try {
			branch = brDao.readBranchByPK(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}
	
	public Borrower readBorrowerByPk(Integer cardNo) {
		Borrower borrower = null;
		try {
			borrower = brwDao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return borrower;
	}
	
	public List<Branch> readBranches()  {
		List<Branch> branches = null;
		try {
			branches = brDao.readAllBranches();
			for(Branch br : branches) {
				try {
					System.out.println();
					br.setBooks(bDao.readBooksByBranches(br));
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branches;
	}
	
	public List<Book> readBooks() {
		System.out.println("Entering inside read books service module");
		List<Book> books = null;
		try {
			books = bDao.readAllBooks();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public void deleteAuthor(Author author)  {
		// delete author in Author Table
		try {
			aDao.deleteAuthor(author);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}//No need to delete in book author table	
	}
	public void deleteBook(Book book) {
			// delete book in Book Table
			try {
				bDao.deleteBook(book);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	public void deletePublisher(Publisher publisher) {
		// delete Publisher in Publisher Table
		try {
			pDao.deletePublisher(publisher);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBranch(Branch branch) {
		// delete Branch in Branch Table
		try {
			brDao.deleteBranch(branch);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBorrower(Borrower borrower) {
		try {
			brwDao.deleteBorrower(borrower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void overRideDueDate(Integer bookId, Integer branchId, Integer cardNo, Date duedate) {
		System.out.println("Inside admin service: " + bookId + "--" + branchId + "--" + cardNo + "--" +duedate);
		try {
			blDao.updateBookLoanDueDate(bookId, branchId, cardNo, duedate);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveBook(Book book) {
		try {
			if (book.getBookId() != null) {
				bDao.updateBook(book);
			} else {
				Integer bookId = bDao.addBookWithPublisherByID(book);
				book.setBookId(bookId);
				bDao.addBookAuthors(book);
				bDao.addBookWithGenre(book);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Author> readAllAuthors() {
		List<Author> authors = null;
		System.out.println("Entering read authors service");
		try {
			authors = aDao.readAllAuthors();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}
	

	public List<Genre> readGenres() {
		List<Genre> genres = null;
		try {
			genres = gDao.readAllGenre();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

//	public List<Publisher> readPublishers()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		Connection conn = connUtil.getConnection();
//		PublisherDAO pDao = new PublisherDAO(conn);
//		return pDao.readAllPublisher();
//	}
//
//	public List<Branch> readBranches()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		Connection conn = connUtil.getConnection();
//		BranchDAO brDao = new BranchDAO(conn);
//		System.out.println("Entering here inside admin br");
//		return brDao.readAllBranches();
//	}
//	
//
//	public List<Book> readBooks()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		Connection conn = connUtil.getConnection();
//		BookDAO bdao = new BookDAO(conn);
//		return bdao.readAllBooks();
//	}
//
//	


}
