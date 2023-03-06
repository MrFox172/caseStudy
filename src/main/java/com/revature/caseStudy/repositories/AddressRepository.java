package com.revature.caseStudy.repositories;

import com.revature.caseStudy.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(nativeQuery = true,value = "select * from case_study.address a " +
            "where a.street = :street " +
            "and a.city = :city " +
            "and a.state = :state " +
            "and a.zip_code = :zipCode " +
            "and a.country = :country")
    Optional<Address> findByMatchAll(@Param("street")String street,
                                     @Param("city") String city,
                                     @Param("state") String state,
                                     @Param("zipCode") int zipCode,
                                     @Param("country") String country);
}
