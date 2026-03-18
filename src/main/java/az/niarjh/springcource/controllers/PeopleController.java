package az.niarjh.springcource.controllers;

import az.niarjh.springcource.dao.BookDAO;
import az.niarjh.springcource.dao.PersonDAO;
import az.niarjh.springcource.models.Book;
import az.niarjh.springcource.models.Person;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
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

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    private static final String REDIRECT = "redirect:/people/";

    @GetMapping("/")
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.showAllPeople());
        return "people/showAllPeople";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("person", personDAO.showPerson(id));
        List<Book> books = bookDAO.showBooksByPerson(id);
        if (!books.isEmpty()) {
             model.addAttribute("books", books);
        }
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.save(person);
        return REDIRECT;
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult, @PathVariable("id") UUID id) {

        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(person, id);
        return REDIRECT;
    }

    @GetMapping("/{id}/delete")
    public String getDeletePerson(Model model, @PathVariable("id") UUID id) {
        model.addAttribute("person");
        return "people/delete";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") UUID id) {
        personDAO.delete(id);
        return REDIRECT;
    }

}
