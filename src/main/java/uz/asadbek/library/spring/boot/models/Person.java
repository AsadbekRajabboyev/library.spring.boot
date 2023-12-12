package uz.asadbek.library.spring.boot.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Ism bosh kiritildi")
    @Size(min = 2, max = 20, message = "Ism 2tdan kop 20dan kam bolishi kerak ")
    private String name;
    @Column(name = "age")
    @Min(value = 0, message = " 0dan katta yosh kriiting")
    @Max(value = 100, message = "Dinozavr")
    private int age;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "person")
    private List<Book> books;


    public Person(String name, int age, Date dateOfBirth, Date createdAt, List<Book> books) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.books = books;
    }

    public Person(String name, int age, String email, String phone, Date dateOfBirth, Date createdAt) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
    }

    public Person(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
