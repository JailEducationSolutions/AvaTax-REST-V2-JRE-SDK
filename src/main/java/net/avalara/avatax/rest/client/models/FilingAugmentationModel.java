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
 * A model for return augmentations.
 */
public class FilingAugmentationModel {


    private Int64? id;

    /**
     * Getter for id;
     * The unique ID number for the augmentation.
     */
    public Int64? getid() {;
        return this.id;;
    }

    /**
     * Setter for id;
     * The unique ID number for the augmentation.
     */
    public void setid(Int64? value) {;
        this.id = value;;
    }


    private Int64? filingId;

    /**
     * Getter for filingId;
     * The filing return id that this applies too
     */
    public Int64? getfilingId() {;
        return this.filingId;;
    }

    /**
     * Setter for filingId;
     * The filing return id that this applies too
     */
    public void setfilingId(Int64? value) {;
        this.filingId = value;;
    }


    private Decimal fieldAmount;

    /**
     * Getter for fieldAmount;
     * The field amount.
     */
    public Decimal getfieldAmount() {;
        return this.fieldAmount;;
    }

    /**
     * Setter for fieldAmount;
     * The field amount.
     */
    public void setfieldAmount(Decimal value) {;
        this.fieldAmount = value;;
    }


    private String fieldName;

    /**
     * Getter for fieldName;
     * The field name.
     */
    public String getfieldName() {;
        return this.fieldName;;
    }

    /**
     * Setter for fieldName;
     * The field name.
     */
    public void setfieldName(String value) {;
        this.fieldName = value;;
    }


    private DateTime? createdDate;

    /**
     * Getter for createdDate;
     * The date when this record was created.
     */
    public DateTime? getcreatedDate() {;
        return this.createdDate;;
    }

    /**
     * Setter for createdDate;
     * The date when this record was created.
     */
    public void setcreatedDate(DateTime? value) {;
        this.createdDate = value;;
    }


    private Int32? createdUserId;

    /**
     * Getter for createdUserId;
     * The User ID of the user who created this record.
     */
    public Int32? getcreatedUserId() {;
        return this.createdUserId;;
    }

    /**
     * Setter for createdUserId;
     * The User ID of the user who created this record.
     */
    public void setcreatedUserId(Int32? value) {;
        this.createdUserId = value;;
    }


    private DateTime? modifiedDate;

    /**
     * Getter for modifiedDate;
     * The date/time when this record was last modified.
     */
    public DateTime? getmodifiedDate() {;
        return this.modifiedDate;;
    }

    /**
     * Setter for modifiedDate;
     * The date/time when this record was last modified.
     */
    public void setmodifiedDate(DateTime? value) {;
        this.modifiedDate = value;;
    }


    private Int32? modifiedUserId;

    /**
     * Getter for modifiedUserId;
     * The user ID of the user who last modified this record.
     */
    public Int32? getmodifiedUserId() {;
        return this.modifiedUserId;;
    }

    /**
     * Setter for modifiedUserId;
     * The user ID of the user who last modified this record.
     */
    public void setmodifiedUserId(Int32? value) {;
        this.modifiedUserId = value;;
    }


    /**
     * Returns a JSON string representation of FilingAugmentationModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
