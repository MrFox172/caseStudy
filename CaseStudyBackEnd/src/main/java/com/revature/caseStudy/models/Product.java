package com.revature.caseStudy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "products")
@NoArgsConstructor              //@ToString(includeFieldNames = true)
@AllArgsConstructor             //@Getter and @Setter
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "date", updatable=false)
    private Instant createdDate;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "description_main", nullable = false)
    private String descriptionMain;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;

    @Column(name = "description_details", nullable= true)
    private String descriptionDetails;

}
