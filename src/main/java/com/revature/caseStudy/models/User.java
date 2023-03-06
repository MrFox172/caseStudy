package com.revature.caseStudy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_address", nullable = false)
    private Address primaryAddress;

    @OneToMany(mappedBy = "user")
    List <Order> orders;


}
