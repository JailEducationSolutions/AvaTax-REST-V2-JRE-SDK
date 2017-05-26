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
 * Tax Authority Info
 */
public class TaxAuthorityInfo {


    private String avalaraId;

    /**
     * Getter for avalaraId
     *
     * Avalara Id
     */
    public String getAvalaraId() {
        return this.avalaraId;
    }

    /**
     * Setter for avalaraId
     *
     * Avalara Id
     */
    public void setAvalaraId(String value) {
        this.avalaraId = value;
    }


    private String jurisdictionName;

    /**
     * Getter for jurisdictionName
     *
     * Jurisdiction Name
     */
    public String getJurisdictionName() {
        return this.jurisdictionName;
    }

    /**
     * Setter for jurisdictionName
     *
     * Jurisdiction Name
     */
    public void setJurisdictionName(String value) {
        this.jurisdictionName = value;
    }


    private JurisdictionType jurisdictionType;

    /**
     * Getter for jurisdictionType
     *
     * Jurisdiction Type
     */
    public JurisdictionType getJurisdictionType() {
        return this.jurisdictionType;
    }

    /**
     * Setter for jurisdictionType
     *
     * Jurisdiction Type
     */
    public void setJurisdictionType(JurisdictionType value) {
        this.jurisdictionType = value;
    }


    private String signatureCode;

    /**
     * Getter for signatureCode
     *
     * Signature Code
     */
    public String getSignatureCode() {
        return this.signatureCode;
    }

    /**
     * Setter for signatureCode
     *
     * Signature Code
     */
    public void setSignatureCode(String value) {
        this.signatureCode = value;
    }


    /**
     * Returns a JSON string representation of TaxAuthorityInfo
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
