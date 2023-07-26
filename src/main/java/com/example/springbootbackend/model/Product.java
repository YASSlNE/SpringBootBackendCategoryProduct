package com.example.springbootbackend.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="name")
    String name;
    @Column(name="price")
    double price;
    @Column(name="description")
    String description;
    @JoinColumn(name="id_cat")
    @ManyToOne
    Category id_cat;
}
