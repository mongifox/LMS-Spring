package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;
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

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.BorrowerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BorrowerController {
	
	@Autowired
	BorrowerService brwService;
	
	private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrowerModule(Locale locale, Model model) {
		return "BorrowerJSP/borrowerValidation";
	}
	
	@RequestMapping(value = "/cardNoValidation", method = RequestMethod.GET)
	public String cardNoValidation(Locale locale, Model model, @RequestParam("cardNo") Integer cardNo) {
		boolean validationFlag = false;
		System.out.println("Controller CardNo " + cardNo);
		validationFlag = brwService.checkValidation(cardNo);
		System.out.println("Flag: " + validationFlag);	
		if(validationFlag) {
			List<Branch> branches = new ArrayList<>();
			branches = brwService.readAllBranches();
			model.addAttribute("branches", branches);
			model.addAttribute("cardNo", cardNo );
			return "BorrowerJSP/borrowerBranchSelection";
		}
		else {
			return "BorrowerJSP/borrowerValidation";
		}
	}
	
	@RequestMapping(value = "/borrowerSelec", method = RequestMethod.GET)
	public String borrowerbranchSelection(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) {
			List<Book> bookInBranch = new ArrayList<>();
			bookInBranch = brwService.readAllBooksInBranch(branchId);
			model.addAttribute("booksPerBranch", bookInBranch);
			System.out.println("Entering here");
			model.addAttribute("branchId", branchId );
			model.addAttribute("cardNo", cardNo );
			
		return "BorrowerJSP/borrowerSelection";	
	}
	
	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String checkOutFirst(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) {
			List<Book> bookInBranch = new ArrayList<>();
			bookInBranch = brwService.readAllBooksInBranch(branchId);
			System.out.println("Books In Branch Size " + bookInBranch.size());
			//System.out.println("Copies List " + bookInBranch.get(0).getBookCopies());
			model.addAttribute("booksPerBranch", bookInBranch);
			model.addAttribute("branchId", branchId );
			model.addAttribute("cardNo", cardNo );
		return "BorrowerJSP/checkOut";	
	}
	
	@RequestMapping(value = "/checkOutFinal", method = RequestMethod.GET)
	public String checkOutFinal(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo, @RequestParam("bookId") Integer bookId) {
			List<Book> bookInBranch = new ArrayList<>();
			brwService.checkOutFucntion( cardNo,  bookId,  branchId);
			bookInBranch = brwService.readAllBooksInBranch(branchId);
			System.out.println("Books In Branch Size " + bookInBranch.size());
			//System.out.println("Copies List " + bookInBranch.get(0).getBookCopies());
			model.addAttribute("booksPerBranch", bookInBranch);
			model.addAttribute("branchId", branchId );
			model.addAttribute("cardNo", cardNo );
			
			
		return "BorrowerJSP/checkOut";	
	}

	@RequestMapping(value = "/returnBook", method = RequestMethod.GET)
	public String returnBookFirst(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo) {
			List<Book> bookInBranch = new ArrayList<>();
			bookInBranch = brwService.readBooksFromLoans(branchId, cardNo);
			model.addAttribute("booksPerBranch", bookInBranch);
			model.addAttribute("branchId", branchId );
			model.addAttribute("cardNo", cardNo );
			HashMap<Integer, Integer> mapBookBorrowed = new HashMap<>();
			for(Book book : bookInBranch) {
				mapBookBorrowed.put(book.getBookId(), brwService.countOfLoanedBooksByBorrower(book.getBookId(),cardNo,branchId));
			}
			model.addAttribute("mapBookBorrowed", mapBookBorrowed);
		return "BorrowerJSP/resultOut";	
	}
	 
	@RequestMapping(value = "/returnBookFinal", method = RequestMethod.GET)
	public String returnBookFinal(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("cardNo") Integer cardNo, @RequestParam("bookId") Integer bookId) {
			List<Book> bookInBranch = new ArrayList<>();
			brwService.updateLoanTablesEnrty( cardNo,  bookId,  branchId);
			bookInBranch = brwService.readBooksFromLoans(branchId, cardNo);
			model.addAttribute("booksPerBranch", bookInBranch);
			model.addAttribute("branchId", branchId );
			model.addAttribute("cardNo", cardNo );
			HashMap<Integer, Integer> mapBookBorrowed = new HashMap<>();
			for(Book book : bookInBranch) {
				mapBookBorrowed.put(book.getBookId(), brwService.countOfLoanedBooksByBorrower(book.getBookId(),cardNo,branchId));
			}
			model.addAttribute("mapBookBorrowed", mapBookBorrowed);
			
		return "BorrowerJSP/resultOut";	
	}

}
