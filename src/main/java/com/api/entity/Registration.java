package com.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Plugins: Ensure your IDE supports Lombok, providing a seamless development experience.
// Plugins which will make ur development activity faster,plugin will Automate  lots of work for u.
//plugins gives us the predefined class with the annotations are generated automatically.
// Lombok is a Java library that helps reduce boilerplate code We need not sit and write all the Whole code.
//lombok tales not write getters and setters method unnecessarily it gives us @getters and @setters and
// tales u ryt ony  @getters and @setters apart from that  I will take care of it.

@Getter
@Setter
@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Mobile", nullable = false, unique = true, length = 12)
    private String mobile;



}