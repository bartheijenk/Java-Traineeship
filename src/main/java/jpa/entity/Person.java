package jpa.entity;

//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
import jpa.util.Identifiable;
import lombok.*;

import javax.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
})
public class Person implements Identifiable<Long> {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 40)
    private String name;
    private int age;

    @OneToMany
    @Valid
    @EqualsAndHashCode.Exclude
    private List<Job> jobs;

}
