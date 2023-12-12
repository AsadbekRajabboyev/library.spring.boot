package uz.asadbek.library.spring.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uz.asadbek.library.spring.boot.models.Person;
import uz.asadbek.library.spring.boot.service.PeopleService;


@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (person.getAge()<0){
            errors.rejectValue("age", "", "Togri yosh kiritng");

        }

    }
}
