package com.revature.caseStudy.dtos;

import com.revature.caseStudy.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data                           //@EqualsAndHashCode
public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private String state;
    private int zipCode;
    private String country;
}
