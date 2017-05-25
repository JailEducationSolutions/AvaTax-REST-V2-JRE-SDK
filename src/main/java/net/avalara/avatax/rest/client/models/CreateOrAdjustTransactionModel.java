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
 * Create or adjust transaction model
 */
public class CreateOrAdjustTransactionModel {


    private CreateTransactionModel createTransactionModel;

    /**
     * Getter for createTransactionModel;
     * The create transaction model to be created or updated.
     */
    public CreateTransactionModel getcreateTransactionModel() {;
        return this.createTransactionModel;;
    }

    /**
     * Setter for createTransactionModel;
     * The create transaction model to be created or updated.
     */
    public void setcreateTransactionModel(CreateTransactionModel value) {;
        this.createTransactionModel = value;;
    }


    /**
     * Returns a JSON string representation of CreateOrAdjustTransactionModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
