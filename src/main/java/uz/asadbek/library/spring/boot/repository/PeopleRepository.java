package uz.asadbek.library.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.asadbek.library.spring.boot.models.Book;
import uz.asadbek.library.spring.boot.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByName(String name);

    @Query("SELECT b FROM Book b WHERE b.person.id = :personId")
    List<Book> findByPersonId(@Param("personId") int personId);
}
