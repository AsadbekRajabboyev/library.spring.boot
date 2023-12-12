package uz.asadbek.library.spring.boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.library.spring.boot.models.Person;
import uz.asadbek.library.spring.boot.service.PeopleService;
import uz.asadbek.library.spring.boot.util.PersonValidator;

import java.util.Date;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,Model model){
        model.addAttribute("person", peopleService.show(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        person.setCreatedAt(new Date());
        peopleService.saveUser(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person")@Valid Person person,BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        peopleService.edit(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("{id}/delete")
    public String deletePerson( @PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
