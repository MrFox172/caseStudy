package com.revature.caseStudy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class ProductDetailDTO {
    private int id;
    private String name;
    private String descriptionMain;
    private double price;
    private String pictureUrl;
    private String descriptionDetails;

    //if I wanted to make this spicier, I'd do ratings.
}
