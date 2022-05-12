package com.saquib.demo.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saquib.demo.model.Book;
import com.saquib.demo.repository.BookRepository;

@RestController
// @Controller - jab aap MVC use krte ho tab, agar apke pass View hai to iska use krege, view nahi hai to 
// RestController ka use krege.
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	//apka data json ki format ke andar a rha hai, json ka data lene ke liye hme RequestBody ka use krna hoga,
	// RestAPI ke andar generally aap XML ka use bi kr skte ho, but aaj ke time sbse jyda json use hota hai, 
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody Book book) {
		
		bookRepository.save(book);
		return "Book Added Done...";
	}
	
	// meri ye API ban kr ready hai, jab me is API ko client ko dna chahta hu to usse pahle check krna chahta 
	// hu ki kya ye API proper kaam kr rhi hai ya nhi? testing ke liye postman ka use krege.
	
	@GetMapping("/findAllBook")
	public List<Book> findAllBook() {
		
		
		return bookRepository.findAll();
	}
	
	@GetMapping("/findBook/{id}")
	public Book findBook(@PathVariable Integer id) {
		
		Book book =new Book();
		Optional<Book> o=bookRepository.findById(id);
		if(!o.isEmpty())
		{
			book=o.get();
		}
		return book;
	}
	
	@DeleteMapping("/deleteBook")
	public String deleteBook(@RequestParam Integer id)
	{
		String msg="Book Not Found...";
		Book book=findBook(id);
		if(book.getId()!=null)
		{
			bookRepository.delete(book);
			msg="Book Deleted...";
		}
		return msg;
	}
	
	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book)
	{
		Book myBook = findBook(book.getId());
		
		if(myBook.getId()==null)
		{
			return myBook;
		}
		
		return bookRepository.save(book);
	}
}
