package uz.asadbek.library.spring.boot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.library.spring.boot.models.Book;
import uz.asadbek.library.spring.boot.models.Person;
import uz.asadbek.library.spring.boot.repository.BookRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> index() {
        return bookRepository.findAll();
    }
    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public void newBookAdd(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void updateBook(int id, Book newBook) {
        newBook.setBookId(id);
        bookRepository.save(newBook);
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
    public Optional<Person> getOwnerBook(int id) {
        return bookRepository.findByPersonByBookId(id);
    }
    @Transactional
    public void giveBook(int id, Person person) {
        bookRepository.updatePersonId(id, person.getId());
    }
    @Transactional
    public void setNull(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            book.setPerson(null);
            bookRepository.save(book);
        }
    }
}
