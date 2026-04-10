package az.niarjh.springcource.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Person {
    @Id
    @Column
    private UUID id;

    @NotEmpty(message = "NAME SHOULD not be empty")
    @Size(min = 8, max = 30, message = "name should be between 8 and 30 character")
    @Column
    private String name;

    @Min(value = 6, message = "age should be greater than 6")
    @Column
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "email should be valid")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

}
