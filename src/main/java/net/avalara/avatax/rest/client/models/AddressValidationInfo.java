package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * TextCase info for input address
 */
public class AddressValidationInfo {
    private String city;

    /**
     * Getter for city - TextCase info for input address
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter for city - TextCase info for input address
     */
    public void setCity(String city) {
        this.city = city;
    }

    
    private BigDecimal latitude;

    /**
     * Getter for latitude - TextCase info for input address
     */
    public BigDecimal getLatitude() {
        return this.latitude;
    }

    /**
     * Setter for latitude - TextCase info for input address
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    
    private String country;

    /**
     * Getter for country - TextCase info for input address
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Setter for country - TextCase info for input address
     */
    public void setCountry(String country) {
        this.country = country;
    }

    
    private BigDecimal longitude;

    /**
     * Getter for longitude - TextCase info for input address
     */
    public BigDecimal getLongitude() {
        return this.longitude;
    }

    /**
     * Setter for longitude - TextCase info for input address
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    
    private String line1;

    /**
     * Getter for line1 - TextCase info for input address
     */
    public String getLine1() {
        return this.line1;
    }

    /**
     * Setter for line1 - TextCase info for input address
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    
    private String postalCode;

    /**
     * Getter for postalCode - TextCase info for input address
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Setter for postalCode - TextCase info for input address
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    private String region;

    /**
     * Getter for region - TextCase info for input address
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Setter for region - TextCase info for input address
     */
    public void setRegion(String region) {
        this.region = region;
    }

    
    private String line2;

    /**
     * Getter for line2 - TextCase info for input address
     */
    public String getLine2() {
        return this.line2;
    }

    /**
     * Setter for line2 - TextCase info for input address
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    
    private TextCase textCase;

    /**
     * Getter for textCase - TextCase info for input address
     */
    public TextCase getTextCase() {
        return this.textCase;
    }

    /**
     * Setter for textCase - TextCase info for input address
     */
    public void setTextCase(TextCase textCase) {
        this.textCase = textCase;
    }

    
    private String line3;

    /**
     * Getter for line3 - TextCase info for input address
     */
    public String getLine3() {
        return this.line3;
    }

    /**
     * Setter for line3 - TextCase info for input address
     */
    public void setLine3(String line3) {
        this.line3 = line3;
    }

    


    /**
     * Returns a JSON string representation of AddressValidationInfo.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    