package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Ping Result Model
 */
public class PingResultModel {
    private String version;

    /**
     * Getter for version - Ping Result Model
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Setter for version - Ping Result Model
     */
    public void setVersion(String version) {
        this.version = version;
    }

    
    private Boolean authenticated;

    /**
     * Getter for authenticated - Ping Result Model
     */
    public Boolean getAuthenticated() {
        return this.authenticated;
    }

    /**
     * Setter for authenticated - Ping Result Model
     */
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    
    private AuthenticationTypeId authenticationType;

    /**
     * Getter for authenticationType - Ping Result Model
     */
    public AuthenticationTypeId getAuthenticationType() {
        return this.authenticationType;
    }

    /**
     * Setter for authenticationType - Ping Result Model
     */
    public void setAuthenticationType(AuthenticationTypeId authenticationType) {
        this.authenticationType = authenticationType;
    }

    
    private String authenticatedUserName;

    /**
     * Getter for authenticatedUserName - Ping Result Model
     */
    public String getAuthenticatedUserName() {
        return this.authenticatedUserName;
    }

    /**
     * Setter for authenticatedUserName - Ping Result Model
     */
    public void setAuthenticatedUserName(String authenticatedUserName) {
        this.authenticatedUserName = authenticatedUserName;
    }

    


    /**
     * Returns a JSON string representation of PingResultModel.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    