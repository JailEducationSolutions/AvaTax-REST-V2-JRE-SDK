package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 */
public class FundingInitiateModel {
    private Boolean requestEmail;

    /**
     * Getter for requestEmail - Set this value to true to request an email to the recipient
     */
    public Boolean getRequestEmail() {
        return this.requestEmail;
    }

    /**
     * Setter for requestEmail - Set this value to true to request an email to the recipient
     */
    public void setRequestEmail(Boolean requestEmail) {
        this.requestEmail = requestEmail;
    }

    
    private String fundingEmailRecipient;

    /**
     * Getter for fundingEmailRecipient - If you have requested an email for funding setup, this is the recipient who will receive an 
            email inviting them to setup funding configuration for Avalara Managed Returns.  The recipient can
            then click on a link in the email and setup funding configuration for this company.
     */
    public String getFundingEmailRecipient() {
        return this.fundingEmailRecipient;
    }

    /**
     * Setter for fundingEmailRecipient - If you have requested an email for funding setup, this is the recipient who will receive an 
            email inviting them to setup funding configuration for Avalara Managed Returns.  The recipient can
            then click on a link in the email and setup funding configuration for this company.
     */
    public void setFundingEmailRecipient(String fundingEmailRecipient) {
        this.fundingEmailRecipient = fundingEmailRecipient;
    }

    
    private Boolean requestWidget;

    /**
     * Getter for requestWidget - Set this value to true to request an HTML-based funding widget that can be embedded within an 
            existing user interface.  A user can then interact with the HTML-based funding widget to set up
            funding information for the company.
     */
    public Boolean getRequestWidget() {
        return this.requestWidget;
    }

    /**
     * Setter for requestWidget - Set this value to true to request an HTML-based funding widget that can be embedded within an 
            existing user interface.  A user can then interact with the HTML-based funding widget to set up
            funding information for the company.
     */
    public void setRequestWidget(Boolean requestWidget) {
        this.requestWidget = requestWidget;
    }

    


    /**
     * Returns a JSON string representation of FundingInitiateModel.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    