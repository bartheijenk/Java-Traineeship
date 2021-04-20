package jpa.entity;

import jpa.util.Dao;
import jpa.util.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
})
public class Person implements Identifiable<Long> {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @OneToMany
    private List<Job> jobs;

}
