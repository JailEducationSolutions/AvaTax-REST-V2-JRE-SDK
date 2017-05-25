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
 * User Entitlement Model
 */
public class UserEntitlementModel {


    private string[] permissions;

    /**
     * Getter for permissions
     *
     * List of API names and categories that this user is permitted to access
     */
    public string[] getPermissions() {
        return this.permissions;
    }

    /**
     * Setter for permissions
     *
     * List of API names and categories that this user is permitted to access
     */
    public void setPermissions(string[] value) {
        this.permissions = value;
    }


    private CompanyAccessLevel accessLevel;

    /**
     * Getter for accessLevel
     *
     * What access privileges does the current user have to see companies?
     */
    public CompanyAccessLevel getAccessLevel() {
        return this.accessLevel;
    }

    /**
     * Setter for accessLevel
     *
     * What access privileges does the current user have to see companies?
     */
    public void setAccessLevel(CompanyAccessLevel value) {
        this.accessLevel = value;
    }


    private int[] companies;

    /**
     * Getter for companies
     *
     * The identities of all companies this user is permitted to access
     */
    public int[] getCompanies() {
        return this.companies;
    }

    /**
     * Setter for companies
     *
     * The identities of all companies this user is permitted to access
     */
    public void setCompanies(int[] value) {
        this.companies = value;
    }


    /**
     * Returns a JSON string representation of UserEntitlementModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
