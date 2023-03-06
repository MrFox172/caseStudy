package com.revature.caseStudy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class CheckOutDTO {
    private List<Integer> productIds ;
    private int userId;
    private long creditCardNumber;
    private long creditCardCode;
    private String street;
    private String city;
    private String state;
    private int zipCode;
    private String country;
}
