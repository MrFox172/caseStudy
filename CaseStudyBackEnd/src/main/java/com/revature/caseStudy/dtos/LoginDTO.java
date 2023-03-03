package com.revature.caseStudy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data                           //@EqualsAndHashCode
public class LoginDTO {
    private String email;
    private String password;


}
