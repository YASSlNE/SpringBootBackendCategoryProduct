package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Builder
@Table(name="category")
public class Category {
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @JsonIgnoreProperties("id_cat")
    @OneToMany(mappedBy = "id_cat")
    private List<Product> product;
}
