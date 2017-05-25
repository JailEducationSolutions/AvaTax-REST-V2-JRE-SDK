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
 * An account user who is permitted to use AvaTax.
 */
public class UserModel {


    private int id;

    /**
     * Getter for id
     *
     * The unique ID number of this user.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * The unique ID number of this user.
     */
    public void setId(int value) {
        this.id = value;
    }


    private int accountId;

    /**
     * Getter for accountId
     *
     * The unique ID number of the account to which this user belongs.
     */
    public int getAccountId() {
        return this.accountId;
    }

    /**
     * Setter for accountId
     *
     * The unique ID number of the account to which this user belongs.
     */
    public void setAccountId(int value) {
        this.accountId = value;
    }


    private Integer companyId;

    /**
     * Getter for companyId
     *
     * If this user is locked to one company (and its children), this is the unique ID number of the company to which this user belongs.
     */
    public Integer getCompanyId() {
        return this.companyId;
    }

    /**
     * Setter for companyId
     *
     * If this user is locked to one company (and its children), this is the unique ID number of the company to which this user belongs.
     */
    public void setCompanyId(Integer value) {
        this.companyId = value;
    }


    private string userName;

    /**
     * Getter for userName
     *
     * The username which is used to log on to the AvaTax website, or to authenticate against API calls.
     */
    public string getUserName() {
        return this.userName;
    }

    /**
     * Setter for userName
     *
     * The username which is used to log on to the AvaTax website, or to authenticate against API calls.
     */
    public void setUserName(string value) {
        this.userName = value;
    }


    private string firstName;

    /**
     * Getter for firstName
     *
     * The first or given name of the user.
     */
    public string getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for firstName
     *
     * The first or given name of the user.
     */
    public void setFirstName(string value) {
        this.firstName = value;
    }


    private string lastName;

    /**
     * Getter for lastName
     *
     * The last or family name of the user.
     */
    public string getLastName() {
        return this.lastName;
    }

    /**
     * Setter for lastName
     *
     * The last or family name of the user.
     */
    public void setLastName(string value) {
        this.lastName = value;
    }


    private string email;

    /**
     * Getter for email
     *
     * The email address to be used to contact this user. If the user has forgotten a password, an email can be sent to this email address with information on how to reset this password.
     */
    public string getEmail() {
        return this.email;
    }

    /**
     * Setter for email
     *
     * The email address to be used to contact this user. If the user has forgotten a password, an email can be sent to this email address with information on how to reset this password.
     */
    public void setEmail(string value) {
        this.email = value;
    }


    private string postalCode;

    /**
     * Getter for postalCode
     *
     * The postal code in which this user resides.
     */
    public string getPostalCode() {
        return this.postalCode;
    }

    /**
     * Setter for postalCode
     *
     * The postal code in which this user resides.
     */
    public void setPostalCode(string value) {
        this.postalCode = value;
    }


    private SecurityRoleId securityRoleId;

    /**
     * Getter for securityRoleId
     *
     * The security level for this user.
     */
    public SecurityRoleId getSecurityRoleId() {
        return this.securityRoleId;
    }

    /**
     * Setter for securityRoleId
     *
     * The security level for this user.
     */
    public void setSecurityRoleId(SecurityRoleId value) {
        this.securityRoleId = value;
    }


    private PasswordStatusId passwordStatus;

    /**
     * Getter for passwordStatus
     *
     * The status of the user's password.
     */
    public PasswordStatusId getPasswordStatus() {
        return this.passwordStatus;
    }

    /**
     * Setter for passwordStatus
     *
     * The status of the user's password.
     */
    public void setPasswordStatus(PasswordStatusId value) {
        this.passwordStatus = value;
    }


    private object isActive;

    /**
     * Getter for isActive
     *
     * True if this user is currently active.
     */
    public object getIsActive() {
        return this.isActive;
    }

    /**
     * Setter for isActive
     *
     * True if this user is currently active.
     */
    public void setIsActive(object value) {
        this.isActive = value;
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


    /**
     * Returns a JSON string representation of UserModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
