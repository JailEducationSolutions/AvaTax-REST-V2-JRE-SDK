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
 * An edit to be made on a filing calendar.
 */
public class FilingCalendarEditModel {


    private string fieldName;

    /**
     * Getter for fieldName
     *
     * The name of the field to be modified.
     */
    public string getFieldName() {
        return this.fieldName;
    }

    /**
     * Setter for fieldName
     *
     * The name of the field to be modified.
     */
    public void setFieldName(string value) {
        this.fieldName = value;
    }


    private int questionId;

    /**
     * Getter for questionId
     *
     * The unique ID of the filing calendar question. "Filing calendar question" is the wording displayed to users for a given field.
     */
    public int getQuestionId() {
        return this.questionId;
    }

    /**
     * Setter for questionId
     *
     * The unique ID of the filing calendar question. "Filing calendar question" is the wording displayed to users for a given field.
     */
    public void setQuestionId(int value) {
        this.questionId = value;
    }


    private object oldValue;

    /**
     * Getter for oldValue
     *
     * The current value of the field.
     */
    public object getOldValue() {
        return this.oldValue;
    }

    /**
     * Setter for oldValue
     *
     * The current value of the field.
     */
    public void setOldValue(object value) {
        this.oldValue = value;
    }


    private object newValue;

    /**
     * Getter for newValue
     *
     * The new/proposed value of the field.
     */
    public object getNewValue() {
        return this.newValue;
    }

    /**
     * Setter for newValue
     *
     * The new/proposed value of the field.
     */
    public void setNewValue(object value) {
        this.newValue = value;
    }


    /**
     * Returns a JSON string representation of FilingCalendarEditModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
