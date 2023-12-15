package example;

import lombok.*;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dog")
@NamedQuery(name = "Dog.findAll", query = "FROM Dog", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Dog {

    @Id
    @SequenceGenerator(name = "dogsSequence", sequenceName = "dog_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "dogsSequence")
    private Integer id;

    @Column(length = 20, unique = true)
    private String breed;

    @Column(length = 20, unique = true)
    private String color;

}