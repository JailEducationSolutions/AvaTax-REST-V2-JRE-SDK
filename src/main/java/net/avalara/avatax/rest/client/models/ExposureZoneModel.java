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
 * @link       https://github.com/avadev/AvaTax-REST-V2-JRE-SDK
 */

/**
 * Information about a zone in which this certificate is valid
 */
public class ExposureZoneModel {


    private Integer id;

    /**
     * Getter for id
     *
     * 
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * 
     */
    public void setId(Integer value) {
        this.id = value;
    }


    private String name;

    /**
     * Getter for name
     *
     * 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name
     *
     * 
     */
    public void setName(String value) {
        this.name = value;
    }


    private String tag;

    /**
     * Getter for tag
     *
     * 
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Setter for tag
     *
     * 
     */
    public void setTag(String value) {
        this.tag = value;
    }


    /**
     * Returns a JSON string representation of ExposureZoneModel
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
