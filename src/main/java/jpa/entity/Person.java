package jpa.entity;

//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import jpa.util.Identifiable;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
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
