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
 * Tax Notice Status Model
 */
public class NoticeStatusModel {


    private Int32 id;

    /**
     * Getter for id;
     * The unique ID number of this tax authority type.
     */
    public Int32 getid() {;
        return this.id;;
    }

    /**
     * Setter for id;
     * The unique ID number of this tax authority type.
     */
    public void setid(Int32 value) {;
        this.id = value;;
    }


    private String description;

    /**
     * Getter for description;
     * The description name of this tax authority type.
     */
    public String getdescription() {;
        return this.description;;
    }

    /**
     * Setter for description;
     * The description name of this tax authority type.
     */
    public void setdescription(String value) {;
        this.description = value;;
    }


    private Boolean? isOpen;

    /**
     * Getter for isOpen;
     * True if a tax notice in this status is considered 'open' and has more work expected to be done before it is closed.
     */
    public Boolean? getisOpen() {;
        return this.isOpen;;
    }

    /**
     * Setter for isOpen;
     * True if a tax notice in this status is considered 'open' and has more work expected to be done before it is closed.
     */
    public void setisOpen(Boolean? value) {;
        this.isOpen = value;;
    }


    private Int32? sortOrder;

    /**
     * Getter for sortOrder;
     * If a list of status values is to be displayed in a dropdown, they should be displayed in this numeric order.
     */
    public Int32? getsortOrder() {;
        return this.sortOrder;;
    }

    /**
     * Setter for sortOrder;
     * If a list of status values is to be displayed in a dropdown, they should be displayed in this numeric order.
     */
    public void setsortOrder(Int32? value) {;
        this.sortOrder = value;;
    }


    /**
     * Returns a JSON string representation of NoticeStatusModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
