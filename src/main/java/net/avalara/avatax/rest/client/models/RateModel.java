package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Rate Model
 */
public class RateModel {
    private BigDecimal rate;

    /**
     * Getter for rate - Rate Model
     */
    public BigDecimal getRate() {
        return this.rate;
    }

    /**
     * Setter for rate - Rate Model
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    
    private String name;

    /**
     * Getter for name - Rate Model
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name - Rate Model
     */
    public void setName(String name) {
        this.name = name;
    }

    
    private JurisdictionType type;

    /**
     * Getter for type - Rate Model
     */
    public JurisdictionType getType() {
        return this.type;
    }

    /**
     * Setter for type - Rate Model
     */
    public void setType(JurisdictionType type) {
        this.type = type;
    }

    


    /**
     * Returns a JSON string representation of RateModel.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    