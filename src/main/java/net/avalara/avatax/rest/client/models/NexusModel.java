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
 * Represents a declaration of nexus within a particular taxing jurisdiction.
 */
public class NexusModel {


    private int id;

    /**
     * Getter for id
     *
     * The unique ID number of this declaration of nexus.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * The unique ID number of this declaration of nexus.
     */
    public void setId(int value) {
        this.id = value;
    }


    private int companyId;

    /**
     * Getter for companyId
     *
     * The unique ID number of the company that declared nexus.
     */
    public int getCompanyId() {
        return this.companyId;
    }

    /**
     * Setter for companyId
     *
     * The unique ID number of the company that declared nexus.
     */
    public void setCompanyId(int value) {
        this.companyId = value;
    }


    private string country;

    /**
     * Getter for country
     *
     * The two character ISO-3166 country code of the country in which this company declared nexus.
     */
    public string getCountry() {
        return this.country;
    }

    /**
     * Setter for country
     *
     * The two character ISO-3166 country code of the country in which this company declared nexus.
     */
    public void setCountry(string value) {
        this.country = value;
    }


    private string region;

    /**
     * Getter for region
     *
     * The two or three character ISO region code of the region, state, or province in which this company declared nexus.
     */
    public string getRegion() {
        return this.region;
    }

    /**
     * Setter for region
     *
     * The two or three character ISO region code of the region, state, or province in which this company declared nexus.
     */
    public void setRegion(string value) {
        this.region = value;
    }


    private JurisTypeId jurisTypeId;

    /**
     * Getter for jurisTypeId
     *
     * The jurisdiction type of the jurisdiction in which this company declared nexus.
     */
    public JurisTypeId getJurisTypeId() {
        return this.jurisTypeId;
    }

    /**
     * Setter for jurisTypeId
     *
     * The jurisdiction type of the jurisdiction in which this company declared nexus.
     */
    public void setJurisTypeId(JurisTypeId value) {
        this.jurisTypeId = value;
    }


    private string jurisCode;

    /**
     * Getter for jurisCode
     *
     * The code identifying the jurisdiction in which this company declared nexus.
     */
    public string getJurisCode() {
        return this.jurisCode;
    }

    /**
     * Setter for jurisCode
     *
     * The code identifying the jurisdiction in which this company declared nexus.
     */
    public void setJurisCode(string value) {
        this.jurisCode = value;
    }


    private string jurisName;

    /**
     * Getter for jurisName
     *
     * The common name of the jurisdiction in which this company declared nexus.
     */
    public string getJurisName() {
        return this.jurisName;
    }

    /**
     * Setter for jurisName
     *
     * The common name of the jurisdiction in which this company declared nexus.
     */
    public void setJurisName(string value) {
        this.jurisName = value;
    }


    private Instant effectiveDate;

    /**
     * Getter for effectiveDate
     *
     * The date when this nexus began. If not known, set to null.
     */
    public Instant getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * Setter for effectiveDate
     *
     * The date when this nexus began. If not known, set to null.
     */
    public void setEffectiveDate(Instant value) {
        this.effectiveDate = value;
    }


    private Instant endDate;

    /**
     * Getter for endDate
     *
     * If this nexus will end or has ended on a specific date, set this to the date when this nexus ends.
     */
    public Instant getEndDate() {
        return this.endDate;
    }

    /**
     * Setter for endDate
     *
     * If this nexus will end or has ended on a specific date, set this to the date when this nexus ends.
     */
    public void setEndDate(Instant value) {
        this.endDate = value;
    }


    private string shortName;

    /**
     * Getter for shortName
     *
     * The short name of the jurisdiction.
     */
    public string getShortName() {
        return this.shortName;
    }

    /**
     * Setter for shortName
     *
     * The short name of the jurisdiction.
     */
    public void setShortName(string value) {
        this.shortName = value;
    }


    private string signatureCode;

    /**
     * Getter for signatureCode
     *
     * The signature code of the boundary region as defined by Avalara.
     */
    public string getSignatureCode() {
        return this.signatureCode;
    }

    /**
     * Setter for signatureCode
     *
     * The signature code of the boundary region as defined by Avalara.
     */
    public void setSignatureCode(string value) {
        this.signatureCode = value;
    }


    private string stateAssignedNo;

    /**
     * Getter for stateAssignedNo
     *
     * The state assigned number of this jurisdiction.
     */
    public string getStateAssignedNo() {
        return this.stateAssignedNo;
    }

    /**
     * Setter for stateAssignedNo
     *
     * The state assigned number of this jurisdiction.
     */
    public void setStateAssignedNo(string value) {
        this.stateAssignedNo = value;
    }


    private NexusTypeId nexusTypeId;

    /**
     * Getter for nexusTypeId
     *
     * (DEPRECATED) The type of nexus that this company is declaring.
    * Please use NexusTaxTypeGroupId instead.
     */
    public NexusTypeId getNexusTypeId() {
        return this.nexusTypeId;
    }

    /**
     * Setter for nexusTypeId
     *
     * (DEPRECATED) The type of nexus that this company is declaring.
    * Please use NexusTaxTypeGroupId instead.
     */
    public void setNexusTypeId(NexusTypeId value) {
        this.nexusTypeId = value;
    }


    private Sourcing sourcing;

    /**
     * Getter for sourcing
     *
     * Indicates whether this nexus is defined as origin or destination nexus.
     */
    public Sourcing getSourcing() {
        return this.sourcing;
    }

    /**
     * Setter for sourcing
     *
     * Indicates whether this nexus is defined as origin or destination nexus.
     */
    public void setSourcing(Sourcing value) {
        this.sourcing = value;
    }


    private object hasLocalNexus;

    /**
     * Getter for hasLocalNexus
     *
     * True if you are also declaring local nexus within this jurisdiction.
    * Many U.S. states have options for declaring nexus in local jurisdictions as well as within the state.
     */
    public object getHasLocalNexus() {
        return this.hasLocalNexus;
    }

    /**
     * Setter for hasLocalNexus
     *
     * True if you are also declaring local nexus within this jurisdiction.
    * Many U.S. states have options for declaring nexus in local jurisdictions as well as within the state.
     */
    public void setHasLocalNexus(object value) {
        this.hasLocalNexus = value;
    }


    private LocalNexusTypeId localNexusTypeId;

    /**
     * Getter for localNexusTypeId
     *
     * If you are declaring local nexus within this jurisdiction, this indicates whether you are declaring only 
    * a specified list of local jurisdictions, all state-administered local jurisdictions, or all local jurisdictions.
     */
    public LocalNexusTypeId getLocalNexusTypeId() {
        return this.localNexusTypeId;
    }

    /**
     * Setter for localNexusTypeId
     *
     * If you are declaring local nexus within this jurisdiction, this indicates whether you are declaring only 
    * a specified list of local jurisdictions, all state-administered local jurisdictions, or all local jurisdictions.
     */
    public void setLocalNexusTypeId(LocalNexusTypeId value) {
        this.localNexusTypeId = value;
    }


    private object hasPermanentEstablishment;

    /**
     * Getter for hasPermanentEstablishment
     *
     * Set this value to true if your company has a permanent establishment within this jurisdiction.
     */
    public object getHasPermanentEstablishment() {
        return this.hasPermanentEstablishment;
    }

    /**
     * Setter for hasPermanentEstablishment
     *
     * Set this value to true if your company has a permanent establishment within this jurisdiction.
     */
    public void setHasPermanentEstablishment(object value) {
        this.hasPermanentEstablishment = value;
    }


    private string taxId;

    /**
     * Getter for taxId
     *
     * Optional - the tax identification number under which you declared nexus.
     */
    public string getTaxId() {
        return this.taxId;
    }

    /**
     * Setter for taxId
     *
     * Optional - the tax identification number under which you declared nexus.
     */
    public void setTaxId(string value) {
        this.taxId = value;
    }


    private object streamlinedSalesTax;

    /**
     * Getter for streamlinedSalesTax
     *
     * For the United States, this flag indicates whether this particular nexus falls within a U.S. State that participates 
    * in the Streamlined Sales Tax program. For countries other than the US, this flag is null.
     */
    public object getStreamlinedSalesTax() {
        return this.streamlinedSalesTax;
    }

    /**
     * Setter for streamlinedSalesTax
     *
     * For the United States, this flag indicates whether this particular nexus falls within a U.S. State that participates 
    * in the Streamlined Sales Tax program. For countries other than the US, this flag is null.
     */
    public void setStreamlinedSalesTax(object value) {
        this.streamlinedSalesTax = value;
    }


    private Instant createdDate;

    /**
     * Getter for createdDate
     *
     * The date when this record was created.
     */
    public Instant getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Setter for createdDate
     *
     * The date when this record was created.
     */
    public void setCreatedDate(Instant value) {
        this.createdDate = value;
    }


    private Integer createdUserId;

    /**
     * Getter for createdUserId
     *
     * The User ID of the user who created this record.
     */
    public Integer getCreatedUserId() {
        return this.createdUserId;
    }

    /**
     * Setter for createdUserId
     *
     * The User ID of the user who created this record.
     */
    public void setCreatedUserId(Integer value) {
        this.createdUserId = value;
    }


    private Instant modifiedDate;

    /**
     * Getter for modifiedDate
     *
     * The date/time when this record was last modified.
     */
    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Setter for modifiedDate
     *
     * The date/time when this record was last modified.
     */
    public void setModifiedDate(Instant value) {
        this.modifiedDate = value;
    }


    private Integer modifiedUserId;

    /**
     * Getter for modifiedUserId
     *
     * The user ID of the user who last modified this record.
     */
    public Integer getModifiedUserId() {
        return this.modifiedUserId;
    }

    /**
     * Setter for modifiedUserId
     *
     * The user ID of the user who last modified this record.
     */
    public void setModifiedUserId(Integer value) {
        this.modifiedUserId = value;
    }


    private string nexusTaxTypeGroup;

    /**
     * Getter for nexusTaxTypeGroup
     *
     * The type of nexus that this company is declaring.Replaces NexusTypeId.
    * Use /api/v2/definitions/nexustaxtypegroup for a list of tax type groups.
     */
    public string getNexusTaxTypeGroup() {
        return this.nexusTaxTypeGroup;
    }

    /**
     * Setter for nexusTaxTypeGroup
     *
     * The type of nexus that this company is declaring.Replaces NexusTypeId.
    * Use /api/v2/definitions/nexustaxtypegroup for a list of tax type groups.
     */
    public void setNexusTaxTypeGroup(string value) {
        this.nexusTaxTypeGroup = value;
    }


    /**
     * Returns a JSON string representation of NexusModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
