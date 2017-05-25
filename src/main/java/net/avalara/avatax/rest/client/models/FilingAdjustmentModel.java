package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A model for return adjustments.
 */
public class FilingAdjustmentModel {
    private Long filingId;

    /**
     * Getter for filingId - A model for return adjustments.
     */
    public Long getFilingId() {
        return this.filingId;
    }

    /**
     * Setter for filingId - A model for return adjustments.
     */
    public void setFilingId(Long filingId) {
        this.filingId = filingId;
    }

    
    private Date modifiedDate;

    /**
     * Getter for modifiedDate - A model for return adjustments.
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Setter for modifiedDate - A model for return adjustments.
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    
    private Boolean isCalculated;

    /**
     * Getter for isCalculated - A model for return adjustments.
     */
    public Boolean getIsCalculated() {
        return this.isCalculated;
    }

    /**
     * Setter for isCalculated - A model for return adjustments.
     */
    public void setIsCalculated(Boolean isCalculated) {
        this.isCalculated = isCalculated;
    }

    
    private String reason;

    /**
     * Getter for reason - A model for return adjustments.
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Setter for reason - A model for return adjustments.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    
    private BigDecimal amount;

    /**
     * Getter for amount - A model for return adjustments.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Setter for amount - A model for return adjustments.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    private Long id;

    /**
     * Getter for id - A model for return adjustments.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for id - A model for return adjustments.
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    private PaymentAccountTypeId accountType;

    /**
     * Getter for accountType - A model for return adjustments.
     */
    public PaymentAccountTypeId getAccountType() {
        return this.accountType;
    }

    /**
     * Setter for accountType - A model for return adjustments.
     */
    public void setAccountType(PaymentAccountTypeId accountType) {
        this.accountType = accountType;
    }

    
    private Integer modifiedUserId;

    /**
     * Getter for modifiedUserId - A model for return adjustments.
     */
    public Integer getModifiedUserId() {
        return this.modifiedUserId;
    }

    /**
     * Setter for modifiedUserId - A model for return adjustments.
     */
    public void setModifiedUserId(Integer modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }

    
    private Integer createdUserId;

    /**
     * Getter for createdUserId - A model for return adjustments.
     */
    public Integer getCreatedUserId() {
        return this.createdUserId;
    }

    /**
     * Setter for createdUserId - A model for return adjustments.
     */
    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    
    private AdjustmentTypeId type;

    /**
     * Getter for type - A model for return adjustments.
     */
    public AdjustmentTypeId getType() {
        return this.type;
    }

    /**
     * Setter for type - A model for return adjustments.
     */
    public void setType(AdjustmentTypeId type) {
        this.type = type;
    }

    
    private AdjustmentPeriodTypeId period;

    /**
     * Getter for period - A model for return adjustments.
     */
    public AdjustmentPeriodTypeId getPeriod() {
        return this.period;
    }

    /**
     * Setter for period - A model for return adjustments.
     */
    public void setPeriod(AdjustmentPeriodTypeId period) {
        this.period = period;
    }

    
    private Date createdDate;

    /**
     * Getter for createdDate - A model for return adjustments.
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Setter for createdDate - A model for return adjustments.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    


    /**
     * Returns a JSON string representation of FilingAdjustmentModel.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    