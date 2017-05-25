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
 * An extra property that can change the behavior of tax transactions.
 */
public class ParameterModel {


    private Int64? id;

    /**
     * Getter for id;
     * The unique ID number of this property.
     */
    public Int64? getid() {;
        return this.id;;
    }

    /**
     * Setter for id;
     * The unique ID number of this property.
     */
    public void setid(Int64? value) {;
        this.id = value;;
    }


    private String category;

    /**
     * Getter for category;
     * The service category of this property. Some properties may require that you subscribe to certain features of avatax before they can be used.
     */
    public String getcategory() {;
        return this.category;;
    }

    /**
     * Setter for category;
     * The service category of this property. Some properties may require that you subscribe to certain features of avatax before they can be used.
     */
    public void setcategory(String value) {;
        this.category = value;;
    }


    private String name;

    /**
     * Getter for name;
     * The name of the property. To use this property, add a field on the "properties" object of a /api/v2/companies/(code)/transactions/create call.
     */
    public String getname() {;
        return this.name;;
    }

    /**
     * Setter for name;
     * The name of the property. To use this property, add a field on the "properties" object of a /api/v2/companies/(code)/transactions/create call.
     */
    public void setname(String value) {;
        this.name = value;;
    }


    private ParameterBagDataType? dataType;

    /**
     * Getter for dataType;
     * The data type of the property.
     */
    public ParameterBagDataType? getdataType() {;
        return this.dataType;;
    }

    /**
     * Setter for dataType;
     * The data type of the property.
     */
    public void setdataType(ParameterBagDataType? value) {;
        this.dataType = value;;
    }


    private String description;

    /**
     * Getter for description;
     * A full description of this property.
     */
    public String getdescription() {;
        return this.description;;
    }

    /**
     * Setter for description;
     * A full description of this property.
     */
    public void setdescription(String value) {;
        this.description = value;;
    }


    /**
     * Returns a JSON string representation of ParameterModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
