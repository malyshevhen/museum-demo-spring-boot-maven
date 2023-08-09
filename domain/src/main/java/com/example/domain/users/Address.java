package com.example.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Person address.
 *
 * @author Evhen Malysh
 */
@Embeddable
@Data
public class Address {

    /**
     * Name of the city.
     */
    @Column(name = "city")
    private String city;

    /**
     * Name of the street.
     */
    @Column(name = "street")
    private String street;

    /**
     * Number of the house.
     */
    @Column(name = "number")
    private String number;

    /**
     * Number of apartment.
     */
    @Column(name = "apartment")
    private String apartment;

    /**
     * Postal code.
     */
    private String zip;

}
