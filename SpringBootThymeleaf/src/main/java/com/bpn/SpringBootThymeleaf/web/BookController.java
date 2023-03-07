package com.bpn.SpringBootThymeleaf.web;

import com.bpn.SpringBootThymeleaf.lib.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books.html")
  public String all(Model model) {

    model.addAttribute("books", bookService.findAll());

    return "books/list";
  }

  @GetMapping(value = "/books.html", params = "isbn")
  public String get(@RequestParam("isbn") String isbn, Model model) {

    bookService.find(isbn)
            .ifPresent(book -> model.addAttribute("book", book));

    return "books/details";
  }

  @GetMapping("/books/500")
  public void error() {
    throw new NullPointerException("Dummy NullPointerException");
  }

}
