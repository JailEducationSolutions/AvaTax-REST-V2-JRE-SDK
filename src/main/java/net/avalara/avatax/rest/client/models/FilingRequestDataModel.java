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
 * Represents a commitment to file a tax return on a recurring basis.
* Only used if you subscribe to Avalara Returns.
 */
public class FilingRequestDataModel {


    private Long companyReturnId;

    /**
     * Getter for companyReturnId
     *
     * The company return ID if requesting an update.
     */
    public Long getCompanyReturnId() {
        return this.companyReturnId;
    }

    /**
     * Setter for companyReturnId
     *
     * The company return ID if requesting an update.
     */
    public void setCompanyReturnId(Long value) {
        this.companyReturnId = value;
    }


    private string returnName;

    /**
     * Getter for returnName
     *
     * The return name of the requested calendar
     */
    public string getReturnName() {
        return this.returnName;
    }

    /**
     * Setter for returnName
     *
     * The return name of the requested calendar
     */
    public void setReturnName(string value) {
        this.returnName = value;
    }


    private FilingFrequencyId filingFrequencyId;

    /**
     * Getter for filingFrequencyId
     *
     * The filing frequency of the request
     */
    public FilingFrequencyId getFilingFrequencyId() {
        return this.filingFrequencyId;
    }

    /**
     * Setter for filingFrequencyId
     *
     * The filing frequency of the request
     */
    public void setFilingFrequencyId(FilingFrequencyId value) {
        this.filingFrequencyId = value;
    }


    private string registrationId;

    /**
     * Getter for registrationId
     *
     * State registration ID of the company requesting the filing calendar.
     */
    public string getRegistrationId() {
        return this.registrationId;
    }

    /**
     * Setter for registrationId
     *
     * State registration ID of the company requesting the filing calendar.
     */
    public void setRegistrationId(string value) {
        this.registrationId = value;
    }


    private short months;

    /**
     * Getter for months
     *
     * The months of the request
     */
    public short getMonths() {
        return this.months;
    }

    /**
     * Setter for months
     *
     * The months of the request
     */
    public void setMonths(short value) {
        this.months = value;
    }


    private MatchingTaxType taxTypeId;

    /**
     * Getter for taxTypeId
     *
     * The type of tax to report on this return.
     */
    public MatchingTaxType getTaxTypeId() {
        return this.taxTypeId;
    }

    /**
     * Setter for taxTypeId
     *
     * The type of tax to report on this return.
     */
    public void setTaxTypeId(MatchingTaxType value) {
        this.taxTypeId = value;
    }


    private string locationCode;

    /**
     * Getter for locationCode
     *
     * Location code of the request
     */
    public string getLocationCode() {
        return this.locationCode;
    }

    /**
     * Setter for locationCode
     *
     * Location code of the request
     */
    public void setLocationCode(string value) {
        this.locationCode = value;
    }


    private Instant effDate;

    /**
     * Getter for effDate
     *
     * Filing cycle effective date of the request
     */
    public Instant getEffDate() {
        return this.effDate;
    }

    /**
     * Setter for effDate
     *
     * Filing cycle effective date of the request
     */
    public void setEffDate(Instant value) {
        this.effDate = value;
    }


    private Instant endDate;

    /**
     * Getter for endDate
     *
     * Filing cycle end date of the request
     */
    public Instant getEndDate() {
        return this.endDate;
    }

    /**
     * Setter for endDate
     *
     * Filing cycle end date of the request
     */
    public void setEndDate(Instant value) {
        this.endDate = value;
    }


    private object isClone;

    /**
     * Getter for isClone
     *
     * Flag if the request is a clone of a current filing calendar
     */
    public object getIsClone() {
        return this.isClone;
    }

    /**
     * Setter for isClone
     *
     * Flag if the request is a clone of a current filing calendar
     */
    public void setIsClone(object value) {
        this.isClone = value;
    }


    private string region;

    /**
     * Getter for region
     *
     * The region this request is for
     */
    public string getRegion() {
        return this.region;
    }

    /**
     * Setter for region
     *
     * The region this request is for
     */
    public void setRegion(string value) {
        this.region = value;
    }


    private Integer taxAuthorityId;

    /**
     * Getter for taxAuthorityId
     *
     * The tax authority id of the return
     */
    public Integer getTaxAuthorityId() {
        return this.taxAuthorityId;
    }

    /**
     * Setter for taxAuthorityId
     *
     * The tax authority id of the return
     */
    public void setTaxAuthorityId(Integer value) {
        this.taxAuthorityId = value;
    }


    private string taxAuthorityName;

    /**
     * Getter for taxAuthorityName
     *
     * The tax authority name on the return
     */
    public string getTaxAuthorityName() {
        return this.taxAuthorityName;
    }

    /**
     * Setter for taxAuthorityName
     *
     * The tax authority name on the return
     */
    public void setTaxAuthorityName(string value) {
        this.taxAuthorityName = value;
    }


    private FilingAnswerModel[] answers;

    /**
     * Getter for answers
     *
     * Filing question answers
     */
    public FilingAnswerModel[] getAnswers() {
        return this.answers;
    }

    /**
     * Setter for answers
     *
     * Filing question answers
     */
    public void setAnswers(FilingAnswerModel[] value) {
        this.answers = value;
    }


    /**
     * Returns a JSON string representation of FilingRequestDataModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
