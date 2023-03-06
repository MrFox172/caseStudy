package com.revature.caseStudy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private String pictureUrl;

}
