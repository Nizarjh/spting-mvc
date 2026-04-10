package az.niarjh.springcource.controllers;

import az.niarjh.springcource.dao.BookDAO;
import az.niarjh.springcource.dao.PersonDAO;
import az.niarjh.springcource.models.Book;
import az.niarjh.springcource.models.Person;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private PersonDAO personDAO;
    private BookDAO bookDAO;

    private static final String REDIRECT = "redirect:/books";

    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookDAO.showAllBooks());
        return "books/showAllBooks";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") UUID id,
            Model model,
            @ModelAttribute("person") Person person) {
        Book book = bookDAO.showBook(id);
        model.addAttribute("book", book);
        if (book.getOwner() != null) {
            model.addAttribute("owner", book.getOwner());
        } else {
            model.addAttribute("people", personDAO.showAllPeople());
        }
        return "books/showBook";
    }

    @GetMapping("/new")
    public String newbook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.saveBook(book);
        return REDIRECT;
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("book", bookDAO.showBook(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.updateBook(book, id);
        return REDIRECT;
    }

    @PatchMapping("/{id}/person")
    public String addBookToPerson(@ModelAttribute("person") Person person,
            @PathVariable("id") UUID id) {
        Book book = bookDAO.showBook(id);
        book.setOwner(person);
        bookDAO.updateBook(book, id);
        return REDIRECT;
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") UUID id) {
        bookDAO.freeBook(id);
        return REDIRECT + "/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") UUID id) {
        bookDAO.deleteBook(id);
        return REDIRECT;
    }

}
