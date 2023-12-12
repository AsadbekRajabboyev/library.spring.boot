package uz.asadbek.library.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.library.spring.boot.models.Book;
import uz.asadbek.library.spring.boot.models.Person;
import uz.asadbek.library.spring.boot.service.BookService;
import uz.asadbek.library.spring.boot.service.PeopleService;

import java.util.Optional;


@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("kitob", bookService.index());
        return "book/index";
    }
    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("kitob", new Book());
        return "book/new";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person){
        model.addAttribute("kitob", bookService.show(id));

        Optional<Person> ownerBook = bookService.getOwnerBook(id);
        if (ownerBook.isPresent()){
            model.addAttribute("owner",ownerBook.get());
        }else{
            model.addAttribute("people", peopleService.index());
        }
        return "book/show";
    }
    @PostMapping
    public String createBook(@ModelAttribute("book") Book book){
        bookService.newBookAdd(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("kitob", bookService.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id")int id){
        bookService.updateBook(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("{id}/delete")
    public String deletebook( @PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/giveBook")
    public String giveBook(@ModelAttribute("person")Person person,@PathVariable("id")int id){
        bookService.giveBook(id,person);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/setNull")
    public String setNull(@PathVariable("id") int id){
        bookService.setNull(id);
        return "redirect:/book/{id}";
    }
}
