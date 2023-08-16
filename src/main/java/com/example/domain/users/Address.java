package com.example.domain.users;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Person address.
 *
 * @author Evhen Malysh
 */
@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    /**
     * Name of the city.
     */
    private String city;

    /**
     * Name of the street.
     */
    private String street;

    /**
     * Number of the house.
     */
    private String number;

    /**
     * Number of apartment.
     */
    private String apartment;

    /**
     * Postal code.
     */
    private String zip;

}
