package uz.asadbek.library.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.asadbek.library.spring.boot.models.Book;
import uz.asadbek.library.spring.boot.models.Person;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("SELECT b.person FROM Book b WHERE b.id = :bookId")
    Optional<Person> findByPersonByBookId(@Param("bookId") int bookId);


    @Modifying
    @Query("Update Book b Set b.person.id = :personId where b.id = :bookId")
    void updatePersonId(@Param("bookId") int bookId, @Param("personId") int personId);
}
