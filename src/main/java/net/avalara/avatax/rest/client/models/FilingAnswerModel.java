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
public class FilingAnswerModel {


    private Int64 filingQuestionId;

    /**
     * Getter for filingQuestionId;
     * The ID number for a filing question
     */
    public Int64 getfilingQuestionId() {;
        return this.filingQuestionId;;
    }

    /**
     * Setter for filingQuestionId;
     * The ID number for a filing question
     */
    public void setfilingQuestionId(Int64 value) {;
        this.filingQuestionId = value;;
    }


    private Dictionary<string, string> answer;

    /**
     * Getter for answer;
     * The value of the answer for the filing question identified by filingQuestionId
     */
    public Dictionary<string, string> getanswer() {;
        return this.answer;;
    }

    /**
     * Setter for answer;
     * The value of the answer for the filing question identified by filingQuestionId
     */
    public void setanswer(Dictionary<string, string> value) {;
        this.answer = value;;
    }


    /**
     * Returns a JSON string representation of FilingAnswerModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
