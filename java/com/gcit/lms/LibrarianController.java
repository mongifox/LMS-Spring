package com.gcit.lms;

import java.sql.SQLException;
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
import com.gcit.lms.service.LibrarianService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class LibrarianController {
	
	@Autowired
	LibrarianService libService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String gotoLibrarian(Locale locale, Model model) {
		List<Branch> branches = 	libService.readBranches();
		model.addAttribute("branches", branches);
		return "LibrarianJSP/librarianbranchview";
	}
	
	@RequestMapping(value = "/selectBranch", method = RequestMethod.POST)
	public String selectBranch(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam(value="update", required=false) String decision1, @RequestParam(value = "edit", required=false) String decision2) {
		model.addAttribute("branchId", branchId );
		Branch branch = 	libService.readBranchByPk(branchId);
		model.addAttribute("branch", branch);
		
		System.out.println("Decision 1 value: "+ decision1);
		System.out.println("Decision 2 value: "+ decision2);
		
		//for editing no Of Copies
		if((decision1 == null) && (decision2!=null)) {
			List<Book> books = libService.readBooksByBranch(branch);
			HashMap<Integer, Integer> mapBooks = new HashMap<>();
			for(Book b : books) {
				mapBooks.put(b.getBookId(), libService.readBookCountInBranch(branchId, b.getBookId()));
			}
			System.out.println("Map Books" + mapBooks);
			model.addAttribute("books", books );
			model.addAttribute("mapBooks", mapBooks);
			return "LibrarianJSP/viewbooksperbranch";
		}
		
		//for updating
		if((decision2==null) && (decision1!=null)) {
			return "LibrarianJSP/updatebranch";
		}
		return "LibrarianJSP/librarianbranchview";
	}
	
	@RequestMapping(value = "/saveCopies", method = RequestMethod.POST)
	public String saveCopies(Locale locale, Model model, @RequestParam("branchId") Integer branchId, @RequestParam("bookId") Integer bookId, @RequestParam("noOfCopies") Integer noOfCopies) {
//		model.addAttribute("branchId", branchId );
//		Branch branch = 	libService.readBranchByPk(branchId);
//		model.addAttribute("branch", branch);
		System.out.println("Entering save copies in librarian controller");
		System.out.println("No of Copies : " + noOfCopies);
			libService.saveBookCopies(noOfCopies, bookId, branchId);
			System.out.println("leaving save copies in librarian service");
			List<Branch> branches = 	libService.readBranches();
			model.addAttribute("branches", branches);
			/*List<Book> books = libService.readBooksByBranch(branch);
			HashMap<Integer, Integer> mapBooks = new HashMap<>();
			for(Book b : books) {
				mapBooks.put(b.getBookId(), libService.readBookCountInBranch(branchId, b.getBookId()));
			}
			System.out.println("Map Books" + mapBooks);
			model.addAttribute("books", books );
			model.addAttribute("mapBooks", mapBooks);*/
		
		return "LibrarianJSP/librarianbranchview";
	}
	
	@RequestMapping(value = "/branchUpdate", method = RequestMethod.POST)
	public String branchUpdate(Model model, @RequestParam("branchName") String branchName, @RequestParam("branchAddress") String branchAddress, @RequestParam("branchId") Integer branchId) {
		System.out.println("Inside /branchUpdate");
		libService.updateBranch(branchName, branchAddress, branchId);
		List<Branch> branches = 	libService.readBranches();
		model.addAttribute("branches", branches);
		return "LibrarianJSP/librarianbranchview";
		
	}
}
