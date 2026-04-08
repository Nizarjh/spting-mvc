package az.niarjh.springcource.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column
    private UUID bookId;

    private UUID ownerId;

    @NotEmpty(message = "NAME SHOULD not be empty")
    @Size(min = 1, max = 30, message = "name should be between 1 and 30 character")
    private String title;

    @NotEmpty(message = "NAME SHOULD not be empty")
    @Size(min = 1, message = "Please ")
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime publishDate;
}
