package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity{
    // Parent claas is EazyClass and its child class is Person class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int classId;
    @NotBlank(message = "Name must not be blank")
    @Size(min=3 , message = "Name must be at least 3 characters long")
    private String name;
    @OneToMany(mappedBy = "eazyClass",fetch = FetchType.LAZY, // @mappedBy annoation is used to tell the JPA that the EazyClass the parent class is linked by the Person class the child class is mapped by a feild called eazyClass inside the person class
            // child class
    cascade = CascadeType.PERSIST,targetEntity = Person.class)
    private Set<Person> persons;

}
