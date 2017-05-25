package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * AvaTax Software Development Kit for Java JRE based environments
 *
 * (c) 2004-2017 Avalara, Inc.
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 *
 * @author     Dustin Welden <dustin.welden@avalara.com>
 * @copyright  2004-2017 Avalara, Inc.
 * @license    https://www.apache.org/licenses/LICENSE-2.0
 * @version    17.5.2-77
 * @link       https://github.com/avadev/AvaTax-REST-V2-JRE-SDK
 */

/**
 * 
 */
public class AvaFileFormModel {


    private Integer id;

    /**
     * Getter for id
     *
     * Unique Id of the form
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * Unique Id of the form
     */
    public void setId(Integer value) {
        this.id = value;
    }


    private string returnName;

    /**
     * Getter for returnName
     *
     * Name of the file being returned
     */
    public string getReturnName() {
        return this.returnName;
    }

    /**
     * Setter for returnName
     *
     * Name of the file being returned
     */
    public void setReturnName(string value) {
        this.returnName = value;
    }


    private string formName;

    /**
     * Getter for formName
     *
     * Name of the submitted form
     */
    public string getFormName() {
        return this.formName;
    }

    /**
     * Setter for formName
     *
     * Name of the submitted form
     */
    public void setFormName(string value) {
        this.formName = value;
    }


    private string description;

    /**
     * Getter for description
     *
     * A description of the submitted form
     */
    public string getDescription() {
        return this.description;
    }

    /**
     * Setter for description
     *
     * A description of the submitted form
     */
    public void setDescription(string value) {
        this.description = value;
    }


    private Instant effDate;

    /**
     * Getter for effDate
     *
     * The date this form starts to take effect
     */
    public Instant getEffDate() {
        return this.effDate;
    }

    /**
     * Setter for effDate
     *
     * The date this form starts to take effect
     */
    public void setEffDate(Instant value) {
        this.effDate = value;
    }


    private Instant endDate;

    /**
     * Getter for endDate
     *
     * The date the form finishes to take effect
     */
    public Instant getEndDate() {
        return this.endDate;
    }

    /**
     * Setter for endDate
     *
     * The date the form finishes to take effect
     */
    public void setEndDate(Instant value) {
        this.endDate = value;
    }


    private string region;

    /**
     * Getter for region
     *
     * State/Province/Region where the form is submitted for
     */
    public string getRegion() {
        return this.region;
    }

    /**
     * Setter for region
     *
     * State/Province/Region where the form is submitted for
     */
    public void setRegion(string value) {
        this.region = value;
    }


    private string country;

    /**
     * Getter for country
     *
     * The country this form is submitted for
     */
    public string getCountry() {
        return this.country;
    }

    /**
     * Setter for country
     *
     * The country this form is submitted for
     */
    public void setCountry(string value) {
        this.country = value;
    }


    private Byte formTypeId;

    /**
     * Getter for formTypeId
     *
     * The type of the form being submitted
     */
    public Byte getFormTypeId() {
        return this.formTypeId;
    }

    /**
     * Setter for formTypeId
     *
     * The type of the form being submitted
     */
    public void setFormTypeId(Byte value) {
        this.formTypeId = value;
    }


    private Byte filingOptionTypeId;

    /**
     * Getter for filingOptionTypeId
     *
     * 
     */
    public Byte getFilingOptionTypeId() {
        return this.filingOptionTypeId;
    }

    /**
     * Setter for filingOptionTypeId
     *
     * 
     */
    public void setFilingOptionTypeId(Byte value) {
        this.filingOptionTypeId = value;
    }


    private Byte dueDateTypeId;

    /**
     * Getter for dueDateTypeId
     *
     * The type of the due date
     */
    public Byte getDueDateTypeId() {
        return this.dueDateTypeId;
    }

    /**
     * Setter for dueDateTypeId
     *
     * The type of the due date
     */
    public void setDueDateTypeId(Byte value) {
        this.dueDateTypeId = value;
    }


    private Byte dueDay;

    /**
     * Getter for dueDay
     *
     * Due date
     */
    public Byte getDueDay() {
        return this.dueDay;
    }

    /**
     * Setter for dueDay
     *
     * Due date
     */
    public void setDueDay(Byte value) {
        this.dueDay = value;
    }


    private Byte efileDueDateTypeId;

    /**
     * Getter for efileDueDateTypeId
     *
     * 
     */
    public Byte getEfileDueDateTypeId() {
        return this.efileDueDateTypeId;
    }

    /**
     * Setter for efileDueDateTypeId
     *
     * 
     */
    public void setEfileDueDateTypeId(Byte value) {
        this.efileDueDateTypeId = value;
    }


    private Byte efileDueDay;

    /**
     * Getter for efileDueDay
     *
     * The date by when the E-filing should be submitted
     */
    public Byte getEfileDueDay() {
        return this.efileDueDay;
    }

    /**
     * Setter for efileDueDay
     *
     * The date by when the E-filing should be submitted
     */
    public void setEfileDueDay(Byte value) {
        this.efileDueDay = value;
    }


    private Instant efileDueTime;

    /**
     * Getter for efileDueTime
     *
     * The time of day by when the E-filing should be submitted
     */
    public Instant getEfileDueTime() {
        return this.efileDueTime;
    }

    /**
     * Setter for efileDueTime
     *
     * The time of day by when the E-filing should be submitted
     */
    public void setEfileDueTime(Instant value) {
        this.efileDueTime = value;
    }


    private object hasVendorDiscount;

    /**
     * Getter for hasVendorDiscount
     *
     * Whether the customer has discount
     */
    public object getHasVendorDiscount() {
        return this.hasVendorDiscount;
    }

    /**
     * Setter for hasVendorDiscount
     *
     * Whether the customer has discount
     */
    public void setHasVendorDiscount(object value) {
        this.hasVendorDiscount = value;
    }


    private Byte roundingTypeId;

    /**
     * Getter for roundingTypeId
     *
     * The way system does the rounding
     */
    public Byte getRoundingTypeId() {
        return this.roundingTypeId;
    }

    /**
     * Setter for roundingTypeId
     *
     * The way system does the rounding
     */
    public void setRoundingTypeId(Byte value) {
        this.roundingTypeId = value;
    }


    /**
     * Returns a JSON string representation of AvaFileFormModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
