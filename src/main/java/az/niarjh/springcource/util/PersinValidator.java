package az.niarjh.springcource.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import az.niarjh.springcource.models.Person;

public class PersinValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

}
