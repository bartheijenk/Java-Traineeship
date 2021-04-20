package jpa.entity;

import jpa.util.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Job.findAll", query = "SELECT p FROM Job p")
})
public class Job implements Identifiable<Long> {
    @Id @GeneratedValue
    private Long id;
    @Size(max = 40)
    private String name;
    @NotNull
    private int hoursAWeek;
}
