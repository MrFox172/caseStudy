package com.revature.caseStudy.models;

import lombok.*;

import javax.persistence.*;

@Entity                         //@Data includes:
@Table(name="address")          //@RequiredArgsConstructor
@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data                           //@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code", nullable=false)
    private int zipCode;

    @Column(name = "country", nullable=false)
    private String country;


}
