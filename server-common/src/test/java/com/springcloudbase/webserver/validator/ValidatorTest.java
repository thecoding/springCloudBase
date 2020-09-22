package com.springcloudbase.webserver.validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Mirko on 2020/5/1.
 */
public class ValidatorTest {

    @Test
    public void validatorTest1(){
        Person person = new Person();
        person.setName("test");
        person.setAge(15);

        Validator validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory().getValidator();

        Set<ConstraintViolation<Person>> result = validator.validate(person);
        result.stream().map(v ->
            v.getPropertyPath() + " " + v.getMessage() + " " + v.getInvalidValue()
        ).forEach(System.out::println);
    }
}
