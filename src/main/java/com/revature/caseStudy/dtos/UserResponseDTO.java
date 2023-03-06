package com.revature.caseStudy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class UserResponseDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
}
