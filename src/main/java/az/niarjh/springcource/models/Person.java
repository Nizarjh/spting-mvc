package az.niarjh.springcource.models;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private UUID id;

    @NotEmpty(message = "NAME SHOULD not be empty")
    @Size(min = 8, max = 30, message = "name should be between 8 and 30 character")
    private String name;

    @Min(value = 6, message = "age should be greater than 6")
    private int age;

}
