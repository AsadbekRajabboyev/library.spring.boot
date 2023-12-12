package uz.asadbek.library.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.asadbek.library.spring.boot.models.Book;
import uz.asadbek.library.spring.boot.models.Person;
import uz.asadbek.library.spring.boot.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
     return peopleRepository.findAll();
    }

    public Person show(int id)   {
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional
    public void saveUser(Person person) {
        peopleRepository.save(person);
    }
    @Transactional
    public void edit(int id, Person yangiPerson) {
       yangiPerson.setId(id);
       peopleRepository.save(yangiPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return peopleRepository.findByPersonId(id);
    }

}

