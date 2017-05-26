package net.avalara.avatax.rest.client;

import com.google.gson.reflect.TypeToken;
import net.avalara.avatax.rest.client.models.*;
import net.avalara.avatax.rest.client.enums.*;

import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;

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
 
public class AvaTaxClient {

    private final ExecutorService threadPool;
    private RestCallFactory restCallFactory;

    private AvaTaxClient() {
        this(null);
    }

    private AvaTaxClient(ExecutorService threadPool) {
        if (threadPool != null) {
            this.threadPool = threadPool;
        } else {
            this.threadPool = Executors.newFixedThreadPool(3);
        }
    }

    public AvaTaxClient(String appName, String appVersion, String machineName, AvaTaxEnvironment environment) {
        this(appName, appVersion, machineName, environment == AvaTaxEnvironment.Production ? AvaTaxConstants.Production_Url : AvaTaxConstants.Sandbox_Url);
    }

    public AvaTaxClient(String appName, String appVersion, String machineName, String environmentUrl) {
        this();
        this.restCallFactory = new RestCallFactory(appName, appVersion, machineName, environmentUrl);
    }

    public AvaTaxClient(String appName, String appVersion, String machineName, AvaTaxEnvironment environment, String proxyHost, int proxyPort, String proxySchema) {
        this(appName, appVersion, machineName, environment == AvaTaxEnvironment.Production ? AvaTaxConstants.Production_Url : AvaTaxConstants.Sandbox_Url, proxyHost, proxyPort, proxySchema);
    }

    public AvaTaxClient(String appName, String appVersion, String machineName, String environmentUrl, String proxyHost, int proxyPort, String proxySchema) {
        this();
        this.restCallFactory = new RestCallFactory(appName, appVersion, machineName, environmentUrl, proxyHost, proxyPort, proxySchema);
    }


    public AvaTaxClient withSecurity(String securityHeader) {
        this.restCallFactory.addSecurityHeader(securityHeader);

        return this;
    }

    public AvaTaxClient withSecurity(String username, String password) {
        String header = null;

        try {
            header = Base64.encodeBase64String((username + ":" + password).getBytes("utf-8"));
        } catch (java.io.UnsupportedEncodingException ex) {
            System.out.println("Could not find encoding for UTF-8.");
            ex.printStackTrace();
        }

        return withSecurity(header);
    }


//region Methods
    /**
     * Reset this account's license key
     * 
     * Resets the existing license key for this account to a new key.
     * To reset your account, you must specify the ID of the account you wish to reset and confirm the action.
     * 
     * @param id The ID of the account you wish to update.
     * @param model A request confirming that you wish to reset the license key of this account.
     * @return LicenseKeyModel
     */
    public LicenseKeyModel accountResetLicenseKey(int id, ResetLicenseKeyModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/resetlicensekey");
        path.applyField("id", id);
        return ((RestCall<LicenseKeyModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<LicenseKeyModel>(){})).call();
    }

    /**
     * Reset this account's license key
     * 
     * Resets the existing license key for this account to a new key.
     * To reset your account, you must specify the ID of the account you wish to reset and confirm the action.
     * 
     * @param id The ID of the account you wish to update.
     * @param model A request confirming that you wish to reset the license key of this account.
     * @return LicenseKeyModel
     */
    public Future<LicenseKeyModel> accountResetLicenseKeyAsync(int id, ResetLicenseKeyModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/resetlicensekey");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<LicenseKeyModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<LicenseKeyModel>(){}));
    }

    /**
     * Activate an account by accepting terms and conditions
     * 
     * Activate the account specified by the unique accountId number.
     * 
     * This activation request can only be called by account administrators.  You must indicate 
     * that you have read and accepted Avalara's terms and conditions to call this API.
     * 
     * If you have not read or accepted the terms and conditions, this API call will return the
     * 
     * @param id The ID of the account to activate
     * @param model The activation request
     * @return AccountModel
     */
    public AccountModel activateAccount(int id, ActivateAccountModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/activate");
        path.applyField("id", id);
        return ((RestCall<AccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AccountModel>(){})).call();
    }

    /**
     * Activate an account by accepting terms and conditions
     * 
     * Activate the account specified by the unique accountId number.
     * 
     * This activation request can only be called by account administrators.  You must indicate 
     * that you have read and accepted Avalara's terms and conditions to call this API.
     * 
     * If you have not read or accepted the terms and conditions, this API call will return the
     * 
     * @param id The ID of the account to activate
     * @param model The activation request
     * @return AccountModel
     */
    public Future<AccountModel> activateAccountAsync(int id, ActivateAccountModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/activate");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<AccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AccountModel>(){}));
    }

    /**
     * Retrieve a single account
     * 
     * Get the account object identified by this URL.
     * You may use the '$include' parameter to fetch additional nested data:
     * 
     * * Subscriptions
     * 
     * @param id The ID of the account to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return AccountModel
     */
    public AccountModel getAccount(int id, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return ((RestCall<AccountModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AccountModel>(){})).call();
    }

    /**
     * Retrieve a single account
     * 
     * Get the account object identified by this URL.
     * You may use the '$include' parameter to fetch additional nested data:
     * 
     * * Subscriptions
     * 
     * @param id The ID of the account to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return AccountModel
     */
    public Future<AccountModel> getAccountAsync(int id, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<AccountModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AccountModel>(){}));
    }

    /**
     * Get configuration settings for this account
     * 
     * Retrieve a list of all configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `TaxServiceConfig` and `AddressServiceConfig` are reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Account settings are permanent settings that cannot be deleted.  You can set the value of an
     * account setting to null if desired.
     * 
     * Avalara-based account settings for `TaxServiceConfig` and `AddressServiceConfig` affect your account's
     * 
     * @param id 
     * @return ArrayList<AccountConfigurationModel>
     */
    public ArrayList<AccountConfigurationModel> getAccountConfiguration(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/configuration");
        path.applyField("id", id);
        return ((RestCall<ArrayList<AccountConfigurationModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<AccountConfigurationModel>>(){})).call();
    }

    /**
     * Get configuration settings for this account
     * 
     * Retrieve a list of all configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `TaxServiceConfig` and `AddressServiceConfig` are reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Account settings are permanent settings that cannot be deleted.  You can set the value of an
     * account setting to null if desired.
     * 
     * Avalara-based account settings for `TaxServiceConfig` and `AddressServiceConfig` affect your account's
     * 
     * @param id 
     * @return ArrayList<AccountConfigurationModel>
     */
    public Future<ArrayList<AccountConfigurationModel>> getAccountConfigurationAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/configuration");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<AccountConfigurationModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<AccountConfigurationModel>>(){}));
    }

    /**
     * Change configuration settings for this account
     * 
     * Update configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `TaxServiceConfig` and `AddressServiceConfig` are reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Account settings are permanent settings that cannot be deleted.  You can set the value of an
     * account setting to null if desired.
     * 
     * Avalara-based account settings for `TaxServiceConfig` and `AddressServiceConfig` affect your account's
     * 
     * @param id 
     * @param model 
     * @return ArrayList<AccountConfigurationModel>
     */
    public ArrayList<AccountConfigurationModel> setAccountConfiguration(int id, ArrayList<AccountConfigurationModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/configuration");
        path.applyField("id", id);
        return ((RestCall<ArrayList<AccountConfigurationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<AccountConfigurationModel>>(){})).call();
    }

    /**
     * Change configuration settings for this account
     * 
     * Update configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `TaxServiceConfig` and `AddressServiceConfig` are reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Account settings are permanent settings that cannot be deleted.  You can set the value of an
     * account setting to null if desired.
     * 
     * Avalara-based account settings for `TaxServiceConfig` and `AddressServiceConfig` affect your account's
     * 
     * @param id 
     * @param model 
     * @return ArrayList<AccountConfigurationModel>
     */
    public Future<ArrayList<AccountConfigurationModel>> setAccountConfigurationAsync(int id, ArrayList<AccountConfigurationModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}/configuration");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<AccountConfigurationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<AccountConfigurationModel>>(){}));
    }

    /**
     * Retrieve geolocation information for a specified address
     * 
     * Resolve an address against Avalara's address-validation system.  If the address can be resolved, this API 
     * provides the latitude and longitude of the resolved location.  The value 'resolutionQuality' can be used 
     * to identify how closely this address can be located.  If the address cannot be clearly located, use the 
     * 'messages' structure to learn more about problems with this address.
     * This is the same API as the POST /api/v2/addresses/resolve endpoint.
     * 
     * @param line1 Line 1
     * @param line2 Line 2
     * @param line3 Line 3
     * @param city City
     * @param region State / Province / Region
     * @param postalCode Postal Code / Zip Code
     * @param country Two character ISO 3166 Country Code (see /api/v2/definitions/countries for a full list)
     * @param textCase selectable text case for address validation (See TextCase::* for a list of allowable values)
     * @param latitude Geospatial latitude measurement
     * @param longitude Geospatial longitude measurement
     * @return AddressResolutionModel
     */
    public AddressResolutionModel resolveAddress(String line1, String line2, String line3, String city, String region, String postalCode, String country, TextCase textCase, BigDecimal latitude, BigDecimal longitude) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/addresses/resolve");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        path.addQuery("textCase", textCase);
        path.addQuery("latitude", latitude);
        path.addQuery("longitude", longitude);
        return ((RestCall<AddressResolutionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AddressResolutionModel>(){})).call();
    }

    /**
     * Retrieve geolocation information for a specified address
     * 
     * Resolve an address against Avalara's address-validation system.  If the address can be resolved, this API 
     * provides the latitude and longitude of the resolved location.  The value 'resolutionQuality' can be used 
     * to identify how closely this address can be located.  If the address cannot be clearly located, use the 
     * 'messages' structure to learn more about problems with this address.
     * This is the same API as the POST /api/v2/addresses/resolve endpoint.
     * 
     * @param line1 Line 1
     * @param line2 Line 2
     * @param line3 Line 3
     * @param city City
     * @param region State / Province / Region
     * @param postalCode Postal Code / Zip Code
     * @param country Two character ISO 3166 Country Code (see /api/v2/definitions/countries for a full list)
     * @param textCase selectable text case for address validation (See TextCase::* for a list of allowable values)
     * @param latitude Geospatial latitude measurement
     * @param longitude Geospatial longitude measurement
     * @return AddressResolutionModel
     */
    public Future<AddressResolutionModel> resolveAddressAsync(String line1, String line2, String line3, String city, String region, String postalCode, String country, TextCase textCase, BigDecimal latitude, BigDecimal longitude) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/addresses/resolve");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        path.addQuery("textCase", textCase);
        path.addQuery("latitude", latitude);
        path.addQuery("longitude", longitude);
        return this.threadPool.submit((RestCall<AddressResolutionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AddressResolutionModel>(){}));
    }

    /**
     * Retrieve geolocation information for a specified address
     * 
     * Resolve an address against Avalara's address-validation system.  If the address can be resolved, this API 
     * provides the latitude and longitude of the resolved location.  The value 'resolutionQuality' can be used 
     * to identify how closely this address can be located.  If the address cannot be clearly located, use the 
     * 'messages' structure to learn more about problems with this address.
     * This is the same API as the GET /api/v2/addresses/resolve endpoint.
     * 
     * @param model The address to resolve
     * @return AddressResolutionModel
     */
    public AddressResolutionModel resolveAddressPost(AddressValidationInfo model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/addresses/resolve");
        return ((RestCall<AddressResolutionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AddressResolutionModel>(){})).call();
    }

    /**
     * Retrieve geolocation information for a specified address
     * 
     * Resolve an address against Avalara's address-validation system.  If the address can be resolved, this API 
     * provides the latitude and longitude of the resolved location.  The value 'resolutionQuality' can be used 
     * to identify how closely this address can be located.  If the address cannot be clearly located, use the 
     * 'messages' structure to learn more about problems with this address.
     * This is the same API as the GET /api/v2/addresses/resolve endpoint.
     * 
     * @param model The address to resolve
     * @return AddressResolutionModel
     */
    public Future<AddressResolutionModel> resolveAddressPostAsync(AddressValidationInfo model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/addresses/resolve");
        return this.threadPool.submit((RestCall<AddressResolutionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AddressResolutionModel>(){}));
    }

    /**
     * Create a new batch
     * 
     * Create one or more new batch objects attached to this company.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * 
     * @param companyId The ID of the company that owns this batch.
     * @param model The batch you wish to create.
     * @return ArrayList<BatchModel>
     */
    public ArrayList<BatchModel> createBatches(int companyId, ArrayList<BatchModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<BatchModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<BatchModel>>(){})).call();
    }

    /**
     * Create a new batch
     * 
     * Create one or more new batch objects attached to this company.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * 
     * @param companyId The ID of the company that owns this batch.
     * @param model The batch you wish to create.
     * @return ArrayList<BatchModel>
     */
    public Future<ArrayList<BatchModel>> createBatchesAsync(int companyId, ArrayList<BatchModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<BatchModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<BatchModel>>(){}));
    }

    /**
     * Delete a single batch
     * 
     * @param companyId The ID of the company that owns this batch.
     * @param id The ID of the batch you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteBatch(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single batch
     * 
     * @param companyId The ID of the company that owns this batch.
     * @param id The ID of the batch you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteBatchAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Download a single batch file
     * 
     * @param companyId The ID of the company that owns this batch
     * @param batchId The ID of the batch object
     * @param id The primary key of this batch file object
     * @return HashMap<String, String>
     */
    public HashMap<String, String> downloadBatch(int companyId, int batchId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{batchId}/files/{id}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("batchId", batchId);
        path.applyField("id", id);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Download a single batch file
     * 
     * @param companyId The ID of the company that owns this batch
     * @param batchId The ID of the batch object
     * @param id The primary key of this batch file object
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> downloadBatchAsync(int companyId, int batchId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{batchId}/files/{id}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("batchId", batchId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Retrieve a single batch
     * 
     * Get the batch object identified by this URL.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * 
     * @param companyId The ID of the company that owns this batch
     * @param id The primary key of this batch
     * @return BatchModel
     */
    public BatchModel getBatch(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<BatchModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<BatchModel>(){})).call();
    }

    /**
     * Retrieve a single batch
     * 
     * Get the batch object identified by this URL.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * 
     * @param companyId The ID of the company that owns this batch
     * @param id The primary key of this batch
     * @return BatchModel
     */
    public Future<BatchModel> getBatchAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<BatchModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<BatchModel>(){}));
    }

    /**
     * Retrieve all batches for this company
     * 
     * List all batch objects attached to the specified company.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listBatchesByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all batches for this company
     * 
     * List all batch objects attached to the specified company.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listBatchesByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/batches");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all batches
     * 
     * Get multiple batch objects across all companies.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryBatches(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/batches");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all batches
     * 
     * Get multiple batch objects across all companies.
     * A batch object is a large collection of API calls stored in a compact file.
     * When you create a batch, it is added to the AvaTax Batch Queue and will be processed in the order it was received.
     * You may fetch a batch to check on its status and retrieve the results of the batch operation.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryBatchesAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/batches");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Quick setup for a company with a single physical address
     * 
     * Shortcut to quickly setup a single-physical-location company with critical information and activate it.
     * This API provides quick and simple company setup functionality and does the following things:
     *             
     * * Create a company object with its own tax profile
     * * Add a key contact person for the company
     * * Set up one physical location for the main office
     * * Declare nexus in all taxing jurisdictions for that main office address
     * * Activate the company
     *             
     * This API only provides a limited subset of functionality compared to the 'Create Company' API call.  
     * 
     * @param model Information about the company you wish to create.
     * @return CompanyModel
     */
    public CompanyModel companyInitialize(CompanyInitializationModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/initialize");
        return ((RestCall<CompanyModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<CompanyModel>(){})).call();
    }

    /**
     * Quick setup for a company with a single physical address
     * 
     * Shortcut to quickly setup a single-physical-location company with critical information and activate it.
     * This API provides quick and simple company setup functionality and does the following things:
     *             
     * * Create a company object with its own tax profile
     * * Add a key contact person for the company
     * * Set up one physical location for the main office
     * * Declare nexus in all taxing jurisdictions for that main office address
     * * Activate the company
     *             
     * This API only provides a limited subset of functionality compared to the 'Create Company' API call.  
     * 
     * @param model Information about the company you wish to create.
     * @return CompanyModel
     */
    public Future<CompanyModel> companyInitializeAsync(CompanyInitializationModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/initialize");
        return this.threadPool.submit((RestCall<CompanyModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<CompanyModel>(){}));
    }

    /**
     * Create new companies
     * 
     * Create one or more new company objects.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * 
     * @param model Either a single company object or an array of companies to create
     * @return ArrayList<CompanyModel>
     */
    public ArrayList<CompanyModel> createCompanies(ArrayList<CompanyModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies");
        return ((RestCall<ArrayList<CompanyModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<CompanyModel>>(){})).call();
    }

    /**
     * Create new companies
     * 
     * Create one or more new company objects.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * 
     * @param model Either a single company object or an array of companies to create
     * @return ArrayList<CompanyModel>
     */
    public Future<ArrayList<CompanyModel>> createCompaniesAsync(ArrayList<CompanyModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies");
        return this.threadPool.submit((RestCall<ArrayList<CompanyModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<CompanyModel>>(){}));
    }

    /**
     * Request managed returns funding setup for a company
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API records that an ambedded HTML funding setup widget was activated.
     * 
     * @param id The unique identifier of the company
     * @param model The funding initialization request
     * @return FundingStatusModel
     */
    public FundingStatusModel createFundingRequest(int id, FundingInitiateModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/funding/setup");
        path.applyField("id", id);
        return ((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FundingStatusModel>(){})).call();
    }

    /**
     * Request managed returns funding setup for a company
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API records that an ambedded HTML funding setup widget was activated.
     * 
     * @param id The unique identifier of the company
     * @param model The funding initialization request
     * @return FundingStatusModel
     */
    public Future<FundingStatusModel> createFundingRequestAsync(int id, FundingInitiateModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/funding/setup");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FundingStatusModel>(){}));
    }

    /**
     * Delete a single company
     * 
     * @param id The ID of the company you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteCompany(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single company
     * 
     * @param id The ID of the company you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteCompanyAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single company
     * 
     * Get the company object identified by this URL.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     * 
     *  * Contacts
     *  * Items
     *  * Locations
     *  * Nexus
     *  * Settings
     *  * TaxCodes
     *  * TaxRules
     * 
     * @param id The ID of the company to retrieve.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return CompanyModel
     */
    public CompanyModel getCompany(int id, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return ((RestCall<CompanyModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<CompanyModel>(){})).call();
    }

    /**
     * Retrieve a single company
     * 
     * Get the company object identified by this URL.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     * 
     *  * Contacts
     *  * Items
     *  * Locations
     *  * Nexus
     *  * Settings
     *  * TaxCodes
     *  * TaxRules
     * 
     * @param id The ID of the company to retrieve.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return CompanyModel
     */
    public Future<CompanyModel> getCompanyAsync(int id, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<CompanyModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<CompanyModel>(){}));
    }

    /**
     * Get configuration settings for this company
     * 
     * Retrieve a list of all configuration settings tied to this company.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `AvaCertServiceConfig` is reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Company settings are permanent settings that cannot be deleted.  You can set the value of a
     * company setting to null if desired.
     * 
     * Avalara-based account settings for `AvaCertServiceConfig` affect your account's exemption certificate
     * 
     * @param id 
     * @return ArrayList<CompanyConfigurationModel>
     */
    public ArrayList<CompanyConfigurationModel> getCompanyConfiguration(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/configuration");
        path.applyField("id", id);
        return ((RestCall<ArrayList<CompanyConfigurationModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<CompanyConfigurationModel>>(){})).call();
    }

    /**
     * Get configuration settings for this company
     * 
     * Retrieve a list of all configuration settings tied to this company.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `AvaCertServiceConfig` is reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Company settings are permanent settings that cannot be deleted.  You can set the value of a
     * company setting to null if desired.
     * 
     * Avalara-based account settings for `AvaCertServiceConfig` affect your account's exemption certificate
     * 
     * @param id 
     * @return ArrayList<CompanyConfigurationModel>
     */
    public Future<ArrayList<CompanyConfigurationModel>> getCompanyConfigurationAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/configuration");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<CompanyConfigurationModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<CompanyConfigurationModel>>(){}));
    }

    /**
     * Check managed returns funding configuration for a company
     * 
     * This API is available by invitation only.
     * Requires a subscription to Avalara Managed Returns or SST Certified Service Provider.
     * Returns a list of funding setup requests and their current status.
     * 
     * @param id The unique identifier of the company
     * @return ArrayList<FundingStatusModel>
     */
    public ArrayList<FundingStatusModel> listFundingRequestsByCompany(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/funding");
        path.applyField("id", id);
        return ((RestCall<ArrayList<FundingStatusModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<FundingStatusModel>>(){})).call();
    }

    /**
     * Check managed returns funding configuration for a company
     * 
     * This API is available by invitation only.
     * Requires a subscription to Avalara Managed Returns or SST Certified Service Provider.
     * Returns a list of funding setup requests and their current status.
     * 
     * @param id The unique identifier of the company
     * @return ArrayList<FundingStatusModel>
     */
    public Future<ArrayList<FundingStatusModel>> listFundingRequestsByCompanyAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/funding");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<FundingStatusModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<FundingStatusModel>>(){}));
    }

    /**
     * Retrieve all companies
     * 
     * Get multiple company objects.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Contacts
     * * Items
     * * Locations
     * * Nexus
     * * Settings
     * * TaxCodes
     * * TaxRules
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryCompanies(String include, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all companies
     * 
     * Get multiple company objects.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Contacts
     * * Items
     * * Locations
     * * Nexus
     * * Settings
     * * TaxCodes
     * * TaxRules
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryCompaniesAsync(String include, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Change configuration settings for this account
     * 
     * Update configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `AvaCertServiceConfig` is reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Company settings are permanent settings that cannot be deleted.  You can set the value of a
     * company setting to null if desired.
     * 
     * Avalara-based account settings for `AvaCertServiceConfig` affect your account's exemption certificate
     * 
     * @param id 
     * @param model 
     * @return ArrayList<CompanyConfigurationModel>
     */
    public ArrayList<CompanyConfigurationModel> setCompanyConfiguration(int id, ArrayList<CompanyConfigurationModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/configuration");
        path.applyField("id", id);
        return ((RestCall<ArrayList<CompanyConfigurationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<CompanyConfigurationModel>>(){})).call();
    }

    /**
     * Change configuration settings for this account
     * 
     * Update configuration settings tied to this account.
     * 
     * Configuration settings provide you with the ability to control features of your account and of your
     * tax software.  The category names `AvaCertServiceConfig` is reserved for
     * Avalara internal software configuration values; to store your own account-level settings, please
     * create a new category name that begins with `X-`, for example, `X-MyCustomCategory`.
     * 
     * Company settings are permanent settings that cannot be deleted.  You can set the value of a
     * company setting to null if desired.
     * 
     * Avalara-based account settings for `AvaCertServiceConfig` affect your account's exemption certificate
     * 
     * @param id 
     * @param model 
     * @return ArrayList<CompanyConfigurationModel>
     */
    public Future<ArrayList<CompanyConfigurationModel>> setCompanyConfigurationAsync(int id, ArrayList<CompanyConfigurationModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}/configuration");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<CompanyConfigurationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<CompanyConfigurationModel>>(){}));
    }

    /**
     * Update a single company
     * 
     * Replace the existing company object at this URL with an updated object.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param id The ID of the company you wish to update.
     * @param model The company object you wish to update.
     * @return CompanyModel
     */
    public CompanyModel updateCompany(int id, CompanyModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        return ((RestCall<CompanyModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<CompanyModel>(){})).call();
    }

    /**
     * Update a single company
     * 
     * Replace the existing company object at this URL with an updated object.
     * A 'company' represents a single corporation or individual that is registered to handle transactional taxes.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param id The ID of the company you wish to update.
     * @param model The company object you wish to update.
     * @return CompanyModel
     */
    public Future<CompanyModel> updateCompanyAsync(int id, CompanyModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{id}");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<CompanyModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<CompanyModel>(){}));
    }

    /**
     * Create a new contact
     * 
     * Create one or more new contact objects.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * 
     * @param companyId The ID of the company that owns this contact.
     * @param model The contacts you wish to create.
     * @return ArrayList<ContactModel>
     */
    public ArrayList<ContactModel> createContacts(int companyId, ArrayList<ContactModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<ContactModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<ContactModel>>(){})).call();
    }

    /**
     * Create a new contact
     * 
     * Create one or more new contact objects.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * 
     * @param companyId The ID of the company that owns this contact.
     * @param model The contacts you wish to create.
     * @return ArrayList<ContactModel>
     */
    public Future<ArrayList<ContactModel>> createContactsAsync(int companyId, ArrayList<ContactModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<ContactModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<ContactModel>>(){}));
    }

    /**
     * Delete a single contact
     * 
     * @param companyId The ID of the company that owns this contact.
     * @param id The ID of the contact you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteContact(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single contact
     * 
     * @param companyId The ID of the company that owns this contact.
     * @param id The ID of the contact you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteContactAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single contact
     * 
     * Get the contact object identified by this URL.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * 
     * @param companyId The ID of the company for this contact
     * @param id The primary key of this contact
     * @return ContactModel
     */
    public ContactModel getContact(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ContactModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ContactModel>(){})).call();
    }

    /**
     * Retrieve a single contact
     * 
     * Get the contact object identified by this URL.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * 
     * @param companyId The ID of the company for this contact
     * @param id The primary key of this contact
     * @return ContactModel
     */
    public Future<ContactModel> getContactAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ContactModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ContactModel>(){}));
    }

    /**
     * Retrieve contacts for this company
     * 
     * List all contact objects assigned to this company.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these contacts
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listContactsByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve contacts for this company
     * 
     * List all contact objects assigned to this company.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these contacts
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listContactsByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all contacts
     * 
     * Get multiple contact objects across all companies.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * a tax collecting and filing entity.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryContacts(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/contacts");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all contacts
     * 
     * Get multiple contact objects across all companies.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * a tax collecting and filing entity.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryContactsAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/contacts");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single contact
     * 
     * Replace the existing contact object at this URL with an updated object.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * a tax collecting and filing entity.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this contact belongs to.
     * @param id The ID of the contact you wish to update
     * @param model The contact you wish to update.
     * @return ContactModel
     */
    public ContactModel updateContact(int companyId, int id, ContactModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ContactModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<ContactModel>(){})).call();
    }

    /**
     * Update a single contact
     * 
     * Replace the existing contact object at this URL with an updated object.
     * A 'contact' is a person associated with a company who is designated to handle certain responsibilities of
     * a tax collecting and filing entity.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this contact belongs to.
     * @param id The ID of the contact you wish to update
     * @param model The contact you wish to update.
     * @return ContactModel
     */
    public Future<ContactModel> updateContactAsync(int companyId, int id, ContactModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/contacts/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ContactModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<ContactModel>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for a country and region.
     * 
     * Returns all Avalara-supported nexus for the specified country and region.
     * 
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> apiV2DefinitionsNexusByCountryByRegionGet(String country, String region) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/{country}/{region}");
        path.applyField("country", country);
        path.applyField("region", region);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for a country and region.
     * 
     * Returns all Avalara-supported nexus for the specified country and region.
     * 
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> apiV2DefinitionsNexusByCountryByRegionGetAsync(String country, String region) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/{country}/{region}");
        path.applyField("country", country);
        path.applyField("region", region);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for a country.
     * 
     * Returns all Avalara-supported nexus for the specified country.
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> apiV2DefinitionsNexusByCountryGet(String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/{country}");
        path.applyField("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for a country.
     * 
     * Returns all Avalara-supported nexus for the specified country.
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> apiV2DefinitionsNexusByCountryGetAsync(String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/{country}");
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for all countries and regions.
     * 
     * Returns the full list of all Avalara-supported nexus for all countries and regions.  
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> apiV2DefinitionsNexusGet() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported nexus for all countries and regions.
     * 
     * Returns the full list of all Avalara-supported nexus for all countries and regions.  
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> apiV2DefinitionsNexusGetAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Test whether a form supports online login verification
     * 
     * This API is intended to be useful to identify whether the user should be allowed
     * 
     * @param form The name of the form you would like to verify. This can be the tax form code or the legacy return name
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getLoginVerifierByForm(String form) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingcalendars/loginverifiers/{form}");
        path.applyField("form", form);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Test whether a form supports online login verification
     * 
     * This API is intended to be useful to identify whether the user should be allowed
     * 
     * @param form The name of the form you would like to verify. This can be the tax form code or the legacy return name
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getLoginVerifierByFormAsync(String form) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingcalendars/loginverifiers/{form}");
        path.applyField("form", form);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of the AvaFile Forms available
     * 
     * Returns the full list of Avalara-supported AvaFile Forms
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listAvaFileForms() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/avafileforms");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of the AvaFile Forms available
     * 
     * Returns the full list of Avalara-supported AvaFile Forms
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listAvaFileFormsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/avafileforms");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List all ISO 3166 countries
     * 
     * Returns a list of all ISO 3166 country codes, and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a country for 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listCountries() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all ISO 3166 countries
     * 
     * Returns a list of all ISO 3166 country codes, and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a country for 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listCountriesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported entity use codes
     * 
     * Returns the full list of Avalara-supported entity use codes.
     * Entity/Use Codes are definitions of the entity who is purchasing something, or the purpose for which the transaction
     * is occurring.  This information is generally used to determine taxability of the product.
     * In order to facilitate correct reporting of your taxes, you are encouraged to select the proper entity use codes for
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listEntityUseCodes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/entityusecodes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported entity use codes
     * 
     * Returns the full list of Avalara-supported entity use codes.
     * Entity/Use Codes are definitions of the entity who is purchasing something, or the purpose for which the transaction
     * is occurring.  This information is generally used to determine taxability of the product.
     * In order to facilitate correct reporting of your taxes, you are encouraged to select the proper entity use codes for
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listEntityUseCodesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/entityusecodes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported filing frequencies.
     * 
     * Returns the full list of Avalara-supported filing frequencies.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listFilingFrequencies() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingfrequencies");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported filing frequencies.
     * 
     * Returns the full list of Avalara-supported filing frequencies.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listFilingFrequenciesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingfrequencies");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List jurisdictions near a specific address
     * 
     * Returns a list of all Avalara-supported taxing jurisdictions that apply to this address.
     * 
     * This API allows you to identify which jurisdictions are nearby a specific address according to the best available geocoding information.
     * It is intended to allow you to create a "Jurisdiction Override", which allows an address to be configured as belonging to a nearby 
     * jurisdiction in AvaTax.
     *             
     * 
     * @param line1 The first address line portion of this address.
     * @param line2 The second address line portion of this address.
     * @param line3 The third address line portion of this address.
     * @param city The city portion of this address.
     * @param region The region, state, or province code portion of this address.
     * @param postalCode The postal code or zip code portion of this address.
     * @param country The two-character ISO-3166 code of the country portion of this address.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listJurisdictionsByAddress(String line1, String line2, String line3, String city, String region, String postalCode, String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/jurisdictionsnearaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List jurisdictions near a specific address
     * 
     * Returns a list of all Avalara-supported taxing jurisdictions that apply to this address.
     * 
     * This API allows you to identify which jurisdictions are nearby a specific address according to the best available geocoding information.
     * It is intended to allow you to create a "Jurisdiction Override", which allows an address to be configured as belonging to a nearby 
     * jurisdiction in AvaTax.
     *             
     * 
     * @param line1 The first address line portion of this address.
     * @param line2 The second address line portion of this address.
     * @param line3 The third address line portion of this address.
     * @param city The city portion of this address.
     * @param region The region, state, or province code portion of this address.
     * @param postalCode The postal code or zip code portion of this address.
     * @param country The two-character ISO-3166 code of the country portion of this address.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listJurisdictionsByAddressAsync(String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/jurisdictionsnearaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the list of questions that are required for a tax location
     * 
     * Returns the list of additional questions you must answer when declaring a location in certain taxing jurisdictions.
     * Some tax jurisdictions require that you register or provide additional information to configure each physical place where
     * your company does business.
     * This information is not usually required in order to calculate tax correctly, but is almost always required to file your tax correctly.
     * You can call this API call for any address and obtain information about what questions must be answered in order to properly
     * 
     * @param line1 The first line of this location's address.
     * @param line2 The second line of this location's address.
     * @param line3 The third line of this location's address.
     * @param city The city part of this location's address.
     * @param region The region, state, or province part of this location's address.
     * @param postalCode The postal code of this location's address.
     * @param country The country part of this location's address.
     * @param latitude Optionally identify the location via latitude/longitude instead of via address.
     * @param longitude Optionally identify the location via latitude/longitude instead of via address.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listLocationQuestionsByAddress(String line1, String line2, String line3, String city, String region, String postalCode, String country, BigDecimal latitude, BigDecimal longitude) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/locationquestions");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        path.addQuery("latitude", latitude);
        path.addQuery("longitude", longitude);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the list of questions that are required for a tax location
     * 
     * Returns the list of additional questions you must answer when declaring a location in certain taxing jurisdictions.
     * Some tax jurisdictions require that you register or provide additional information to configure each physical place where
     * your company does business.
     * This information is not usually required in order to calculate tax correctly, but is almost always required to file your tax correctly.
     * You can call this API call for any address and obtain information about what questions must be answered in order to properly
     * 
     * @param line1 The first line of this location's address.
     * @param line2 The second line of this location's address.
     * @param line3 The third line of this location's address.
     * @param city The city part of this location's address.
     * @param region The region, state, or province part of this location's address.
     * @param postalCode The postal code of this location's address.
     * @param country The country part of this location's address.
     * @param latitude Optionally identify the location via latitude/longitude instead of via address.
     * @param longitude Optionally identify the location via latitude/longitude instead of via address.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listLocationQuestionsByAddressAsync(String line1, String line2, String line3, String city, String region, String postalCode, String country, BigDecimal latitude, BigDecimal longitude) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/locationquestions");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        path.addQuery("latitude", latitude);
        path.addQuery("longitude", longitude);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List all forms where logins can be verified automatically
     * 
     * List all forms where logins can be verified automatically.
     * This API is intended to be useful to identify whether the user should be allowed
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listLoginVerifiers() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingcalendars/loginverifiers");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all forms where logins can be verified automatically
     * 
     * List all forms where logins can be verified automatically.
     * This API is intended to be useful to identify whether the user should be allowed
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listLoginVerifiersAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/filingcalendars/loginverifiers");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List all nexus that apply to a specific address.
     * 
     * Returns a list of all Avalara-supported taxing jurisdictions that apply to this address.
     * This API allows you to identify which tax authorities apply to a physical location, salesperson address, or point of sale.
     * In general, it is usually expected that a company will declare nexus in all the jurisdictions that apply to each physical address
     * where the company does business.
     * 
     * @param line1 The first address line portion of this address.
     * @param line2 The first address line portion of this address.
     * @param line3 The first address line portion of this address.
     * @param city The city portion of this address.
     * @param region The region, state, or province code portion of this address.
     * @param postalCode The postal code or zip code portion of this address.
     * @param country The two-character ISO-3166 code of the country portion of this address.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNexusByAddress(String line1, String line2, String line3, String city, String region, String postalCode, String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/byaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all nexus that apply to a specific address.
     * 
     * Returns a list of all Avalara-supported taxing jurisdictions that apply to this address.
     * This API allows you to identify which tax authorities apply to a physical location, salesperson address, or point of sale.
     * In general, it is usually expected that a company will declare nexus in all the jurisdictions that apply to each physical address
     * where the company does business.
     * 
     * @param line1 The first address line portion of this address.
     * @param line2 The first address line portion of this address.
     * @param line3 The first address line portion of this address.
     * @param city The city portion of this address.
     * @param region The region, state, or province code portion of this address.
     * @param postalCode The postal code or zip code portion of this address.
     * @param country The two-character ISO-3166 code of the country portion of this address.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNexusByAddressAsync(String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/byaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List nexus related to a tax form
     * 
     * Retrieves a list of nexus related to a tax form.
     * 
     * The concept of `Nexus` indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * 
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * This API is intended to provide useful information when examining a tax form.  If you are about to begin filing
     * a tax form, you may want to know whether you have declared nexus in all the jurisdictions related to that tax 
     * 
     * @param formCode The form code that we are looking up the nexus for
     * @return NexusByTaxFormModel
     */
    public NexusByTaxFormModel listNexusByFormCode(String formCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/byform/{formCode}");
        path.applyField("formCode", formCode);
        return ((RestCall<NexusByTaxFormModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusByTaxFormModel>(){})).call();
    }

    /**
     * List nexus related to a tax form
     * 
     * Retrieves a list of nexus related to a tax form.
     * 
     * The concept of `Nexus` indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * 
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * This API is intended to provide useful information when examining a tax form.  If you are about to begin filing
     * a tax form, you may want to know whether you have declared nexus in all the jurisdictions related to that tax 
     * 
     * @param formCode The form code that we are looking up the nexus for
     * @return NexusByTaxFormModel
     */
    public Future<NexusByTaxFormModel> listNexusByFormCodeAsync(String formCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexus/byform/{formCode}");
        path.applyField("formCode", formCode);
        return this.threadPool.submit((RestCall<NexusByTaxFormModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusByTaxFormModel>(){}));
    }

    /**
     * Retrieve the full list of nexus tax type groups
     * 
     * Returns the full list of Avalara-supported nexus tax type groups
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNexusTaxTypeGroups() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexustaxtypegroups");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of nexus tax type groups
     * 
     * Returns the full list of Avalara-supported nexus tax type groups
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNexusTaxTypeGroupsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/nexustaxtypegroups");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice customer funding options.
     * 
     * Returns the full list of Avalara-supported tax notice customer funding options.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeCustomerFundingOptions() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticecustomerfundingoptions");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice customer funding options.
     * 
     * Returns the full list of Avalara-supported tax notice customer funding options.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeCustomerFundingOptionsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticecustomerfundingoptions");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice customer types.
     * 
     * Returns the full list of Avalara-supported tax notice customer types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeCustomerTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticecustomertypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice customer types.
     * 
     * Returns the full list of Avalara-supported tax notice customer types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeCustomerTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticecustomertypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice filing types.
     * 
     * Returns the full list of Avalara-supported tax notice filing types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeFilingtypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticefilingtypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice filing types.
     * 
     * Returns the full list of Avalara-supported tax notice filing types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeFilingtypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticefilingtypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice priorities.
     * 
     * Returns the full list of Avalara-supported tax notice priorities.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticePriorities() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticepriorities");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice priorities.
     * 
     * Returns the full list of Avalara-supported tax notice priorities.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticePrioritiesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticepriorities");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice reasons.
     * 
     * Returns the full list of Avalara-supported tax notice reasons.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeReasons() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticereasons");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice reasons.
     * 
     * Returns the full list of Avalara-supported tax notice reasons.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeReasonsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticereasons");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice responsibility ids
     * 
     * Returns the full list of Avalara-supported tax notice responsibility ids
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeResponsibilities() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticeresponsibilities");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice responsibility ids
     * 
     * Returns the full list of Avalara-supported tax notice responsibility ids
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeResponsibilitiesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticeresponsibilities");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice root causes
     * 
     * Returns the full list of Avalara-supported tax notice root causes
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeRootCauses() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticerootcauses");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice root causes
     * 
     * Returns the full list of Avalara-supported tax notice root causes
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeRootCausesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticerootcauses");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice statuses.
     * 
     * Returns the full list of Avalara-supported tax notice statuses.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeStatuses() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticestatuses");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice statuses.
     * 
     * Returns the full list of Avalara-supported tax notice statuses.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeStatusesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticestatuses");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice types.
     * 
     * Returns the full list of Avalara-supported tax notice types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticeTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticetypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax notice types.
     * 
     * Returns the full list of Avalara-supported tax notice types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticeTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/noticetypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported extra parameters for creating transactions.
     * 
     * Returns the full list of Avalara-supported extra parameters for the 'Create Transaction' API call.
     * This list of parameters is available for use when configuring your transaction.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listParameters() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/parameters");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported extra parameters for creating transactions.
     * 
     * Returns the full list of Avalara-supported extra parameters for the 'Create Transaction' API call.
     * This list of parameters is available for use when configuring your transaction.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listParametersAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/parameters");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported permissions
     * 
     * Returns the full list of Avalara-supported permission types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listPermissions() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/permissions");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported permissions
     * 
     * Returns the full list of Avalara-supported permission types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listPermissionsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/permissions");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of rate types for each country
     * 
     * Returns the full list of Avalara-supported rate type file types
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listRateTypesByCountry(String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries/{country}/ratetypes");
        path.applyField("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of rate types for each country
     * 
     * Returns the full list of Avalara-supported rate type file types
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listRateTypesByCountryAsync(String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries/{country}/ratetypes");
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List all ISO 3166 regions
     * 
     * Returns a list of all ISO 3166 region codes and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a region 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listRegions() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/regions");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all ISO 3166 regions
     * 
     * Returns a list of all ISO 3166 region codes and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a region 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listRegionsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/regions");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * List all ISO 3166 regions for a country
     * 
     * Returns a list of all ISO 3166 region codes for a specific country code, and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a region 
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listRegionsByCountry(String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries/{country}/regions");
        path.applyField("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all ISO 3166 regions for a country
     * 
     * Returns a list of all ISO 3166 region codes for a specific country code, and their US English friendly names.
     * This API is intended to be useful when presenting a dropdown box in your website to allow customers to select a region 
     * 
     * @param country 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listRegionsByCountryAsync(String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/countries/{country}/regions");
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported resource file types
     * 
     * Returns the full list of Avalara-supported resource file types
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listResourceFileTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/resourcefiletypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported resource file types
     * 
     * Returns the full list of Avalara-supported resource file types
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listResourceFileTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/resourcefiletypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported permissions
     * 
     * Returns the full list of Avalara-supported permission types.
     * This API is intended to be useful when designing a user interface for selecting the security role of a user account.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listSecurityRoles() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/securityroles");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported permissions
     * 
     * Returns the full list of Avalara-supported permission types.
     * This API is intended to be useful when designing a user interface for selecting the security role of a user account.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listSecurityRolesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/securityroles");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported subscription types
     * 
     * Returns the full list of Avalara-supported subscription types.
     * This API is intended to be useful for identifying which features you have added to your account.
     * You may always contact Avalara's sales department for information on available products or services.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listSubscriptionTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/subscriptiontypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported subscription types
     * 
     * Returns the full list of Avalara-supported subscription types.
     * This API is intended to be useful for identifying which features you have added to your account.
     * You may always contact Avalara's sales department for information on available products or services.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listSubscriptionTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/subscriptiontypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax authorities.
     * 
     * Returns the full list of Avalara-supported tax authorities.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxAuthorities() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthorities");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax authorities.
     * 
     * Returns the full list of Avalara-supported tax authorities.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxAuthoritiesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthorities");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported forms for each tax authority.
     * 
     * Returns the full list of Avalara-supported forms for each tax authority.
     * This list represents tax forms that Avalara recognizes.
     * Customers who subscribe to Avalara Managed Returns Service can request these forms to be filed automatically 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxAuthorityForms() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthorityforms");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported forms for each tax authority.
     * 
     * Returns the full list of Avalara-supported forms for each tax authority.
     * This list represents tax forms that Avalara recognizes.
     * Customers who subscribe to Avalara Managed Returns Service can request these forms to be filed automatically 
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxAuthorityFormsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthorityforms");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax authority types.
     * 
     * Returns the full list of Avalara-supported tax authority types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxAuthorityTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthoritytypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax authority types.
     * 
     * Returns the full list of Avalara-supported tax authority types.
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxAuthorityTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxauthoritytypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax codes.
     * 
     * Retrieves the list of Avalara-supported system tax codes.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxCodes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxcodes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax codes.
     * 
     * Retrieves the list of Avalara-supported system tax codes.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxCodesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxcodes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of Avalara-supported tax code types.
     * 
     * Returns the full list of recognized tax code types.
     * A 'Tax Code Type' represents a broad category of tax codes, and is less detailed than a single TaxCode.
     * 
     * @return TaxCodeTypesModel
     */
    public TaxCodeTypesModel listTaxCodeTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxcodetypes");
        return ((RestCall<TaxCodeTypesModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxCodeTypesModel>(){})).call();
    }

    /**
     * Retrieve the full list of Avalara-supported tax code types.
     * 
     * Returns the full list of recognized tax code types.
     * A 'Tax Code Type' represents a broad category of tax codes, and is less detailed than a single TaxCode.
     * 
     * @return TaxCodeTypesModel
     */
    public Future<TaxCodeTypesModel> listTaxCodeTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxcodetypes");
        return this.threadPool.submit((RestCall<TaxCodeTypesModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxCodeTypesModel>(){}));
    }

    /**
     * Retrieve the full list of tax sub types
     * 
     * Returns the full list of Avalara-supported tax sub-types
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxSubTypes() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxsubtypes");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of tax sub types
     * 
     * Returns the full list of Avalara-supported tax sub-types
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxSubTypesAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxsubtypes");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve the full list of tax type groups
     * 
     * Returns the full list of Avalara-supported tax type groups
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxTypeGroups() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxtypegroups");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve the full list of tax type groups
     * 
     * Returns the full list of Avalara-supported tax type groups
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxTypeGroupsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/definitions/taxtypegroups");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve a single filing calendar
     * 
     * @param companyId The ID of the company that owns this filing calendar
     * @param id The primary key of this filing calendar
     * @return FilingCalendarModel
     */
    public FilingCalendarModel apiV2CompaniesByCompanyIdFilingcalendarsByIdGet(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingCalendarModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingCalendarModel>(){})).call();
    }

    /**
     * Retrieve a single filing calendar
     * 
     * @param companyId The ID of the company that owns this filing calendar
     * @param id The primary key of this filing calendar
     * @return FilingCalendarModel
     */
    public Future<FilingCalendarModel> apiV2CompaniesByCompanyIdFilingcalendarsByIdGetAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingCalendarModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingCalendarModel>(){}));
    }

    /**
     * Retrieve all filing calendars for this company
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> apiV2CompaniesByCompanyIdFilingcalendarsGet(int companyId, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all filing calendars for this company
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> apiV2CompaniesByCompanyIdFilingcalendarsGetAsync(int companyId, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all filing requests for this company
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> apiV2CompaniesByCompanyIdFilingrequestsGet(int companyId, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all filing requests for this company
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The ID of the company that owns these batches
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> apiV2CompaniesByCompanyIdFilingrequestsGetAsync(int companyId, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Returns a list of options for adding the specified form.
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param formCode The unique code of the form
     * @return ArrayList<CycleAddOptionModel>
     */
    public ArrayList<CycleAddOptionModel> cycleSafeAdd(int companyId, String formCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/add/options");
        path.applyField("companyId", companyId);
        path.addQuery("formCode", formCode);
        return ((RestCall<ArrayList<CycleAddOptionModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<CycleAddOptionModel>>(){})).call();
    }

    /**
     * Returns a list of options for adding the specified form.
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param formCode The unique code of the form
     * @return ArrayList<CycleAddOptionModel>
     */
    public Future<ArrayList<CycleAddOptionModel>> cycleSafeAddAsync(int companyId, String formCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/add/options");
        path.applyField("companyId", companyId);
        path.addQuery("formCode", formCode);
        return this.threadPool.submit((RestCall<ArrayList<CycleAddOptionModel>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ArrayList<CycleAddOptionModel>>(){}));
    }

    /**
     * Indicates when changes are allowed to be made to a filing calendar.
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID of the filing calendar object
     * @param model A list of filing calendar edits to be made
     * @return CycleEditOptionModel
     */
    public CycleEditOptionModel cycleSafeEdit(int companyId, int id, ArrayList<FilingCalendarEditModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/edit/options");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<CycleEditOptionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<CycleEditOptionModel>(){})).call();
    }

    /**
     * Indicates when changes are allowed to be made to a filing calendar.
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID of the filing calendar object
     * @param model A list of filing calendar edits to be made
     * @return CycleEditOptionModel
     */
    public Future<CycleEditOptionModel> cycleSafeEditAsync(int companyId, int id, ArrayList<FilingCalendarEditModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/edit/options");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<CycleEditOptionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<CycleEditOptionModel>(){}));
    }

    /**
     * Returns a list of options for expiring a filing calendar
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID of the filing calendar object
     * @return CycleExpireModel
     */
    public CycleExpireModel cycleSafeExpiration(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/cancel/options");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<CycleExpireModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<CycleExpireModel>(){})).call();
    }

    /**
     * Returns a list of options for expiring a filing calendar
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID of the filing calendar object
     * @return CycleExpireModel
     */
    public Future<CycleExpireModel> cycleSafeExpirationAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/cancel/options");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<CycleExpireModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<CycleExpireModel>(){}));
    }

    /**
     * Delete a single filing calendar.
     * 
     * This API is available by invitation only.
     * Mark the existing notice object at this URL as deleted.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this filing calendar.
     * @param id The ID of the filing calendar you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteFilingCalendar(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single filing calendar.
     * 
     * This API is available by invitation only.
     * Mark the existing notice object at this URL as deleted.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this filing calendar.
     * @param id The ID of the filing calendar you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteFilingCalendarAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Edit existing Filing Calendar's Notes
     * 
     * This API is available by invitation only.
     * This API only allows updating of internal notes and company filing instructions.
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing calendar object
     * @param model The filing calendar model you are wishing to update with.
     * @return FilingCalendarModel
     */
    public FilingCalendarModel filingCalendarUpdate(int companyId, int id, FilingCalendarModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingCalendarModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingCalendarModel>(){})).call();
    }

    /**
     * Edit existing Filing Calendar's Notes
     * 
     * This API is available by invitation only.
     * This API only allows updating of internal notes and company filing instructions.
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing calendar object
     * @param model The filing calendar model you are wishing to update with.
     * @return FilingCalendarModel
     */
    public Future<FilingCalendarModel> filingCalendarUpdateAsync(int companyId, int id, FilingCalendarModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingCalendarModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingCalendarModel>(){}));
    }

    /**
     * Retrieve a single filing request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The ID of the company that owns this filing calendar
     * @param id The primary key of this filing calendar
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequests(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Retrieve a single filing request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The ID of the company that owns this filing calendar
     * @param id The primary key of this filing calendar
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Create a new filing request to create a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that will add the new filing calendar
     * @param model Information about the proposed new filing calendar
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsAdd(int companyId, ArrayList<FilingRequestModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/add/request");
        path.applyField("companyId", companyId);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Create a new filing request to create a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that will add the new filing calendar
     * @param model Information about the proposed new filing calendar
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsAddAsync(int companyId, ArrayList<FilingRequestModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/add/request");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Approve existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * are reviewed and validated by Avalara Compliance before being implemented.
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsApprove(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}/approve");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, null, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Approve existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * are reviewed and validated by Avalara Compliance before being implemented.
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsApproveAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}/approve");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, null, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Cancel existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsCancel(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}/cancel");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, null, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Cancel existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsCancelAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}/cancel");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, null, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Create a new filing request to cancel a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID number of the filing calendar to cancel
     * @param model The cancellation request for this filing calendar
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsNewCancel(int companyId, int id, ArrayList<FilingRequestModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/cancel/request");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Create a new filing request to cancel a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID number of the filing calendar to cancel
     * @param model The cancellation request for this filing calendar
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsNewCancelAsync(int companyId, int id, ArrayList<FilingRequestModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/cancel/request");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Create a new filing request to edit a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID number of the filing calendar to edit
     * @param model A list of filing calendar edits to be made
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsNewEdit(int companyId, int id, ArrayList<FilingRequestModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/edit/request");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Create a new filing request to edit a filing calendar
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing calendar object
     * @param id The unique ID number of the filing calendar to edit
     * @param model A list of filing calendar edits to be made
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsNewEditAsync(int companyId, int id, ArrayList<FilingRequestModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingcalendars/{id}/edit/request");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Edit existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @param model A list of filing calendar edits to be made
     * @return FilingRequestModel
     */
    public FilingRequestModel filingRequestsUpdate(int companyId, int id, FilingRequestModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingRequestModel>(){})).call();
    }

    /**
     * Edit existing Filing Request
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * 
     * @param companyId The unique ID of the company that owns the filing request object
     * @param id The unique ID of the filing request object
     * @param model A list of filing calendar edits to be made
     * @return FilingRequestModel
     */
    public Future<FilingRequestModel> filingRequestsUpdateAsync(int companyId, int id, FilingRequestModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filingrequests/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingRequestModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingRequestModel>(){}));
    }

    /**
     * Gets the request status and Login Result
     * 
     * @param jobId The unique ID number of this login request
     * @return LoginVerificationOutputModel
     */
    public LoginVerificationOutputModel loginVerificationGet(int jobId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars/credentials/{jobId}");
        path.applyField("jobId", jobId);
        return ((RestCall<LoginVerificationOutputModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LoginVerificationOutputModel>(){})).call();
    }

    /**
     * Gets the request status and Login Result
     * 
     * @param jobId The unique ID number of this login request
     * @return LoginVerificationOutputModel
     */
    public Future<LoginVerificationOutputModel> loginVerificationGetAsync(int jobId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars/credentials/{jobId}");
        path.applyField("jobId", jobId);
        return this.threadPool.submit((RestCall<LoginVerificationOutputModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LoginVerificationOutputModel>(){}));
    }

    /**
     * New request for getting for validating customer's login credentials
     * 
     * @param model The model of the login information we are verifying
     * @return LoginVerificationOutputModel
     */
    public LoginVerificationOutputModel loginVerificationPost(LoginVerificationInputModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars/credentials/verify");
        return ((RestCall<LoginVerificationOutputModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<LoginVerificationOutputModel>(){})).call();
    }

    /**
     * New request for getting for validating customer's login credentials
     * 
     * @param model The model of the login information we are verifying
     * @return LoginVerificationOutputModel
     */
    public Future<LoginVerificationOutputModel> loginVerificationPostAsync(LoginVerificationInputModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars/credentials/verify");
        return this.threadPool.submit((RestCall<LoginVerificationOutputModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<LoginVerificationOutputModel>(){}));
    }

    /**
     * Retrieve all filing calendars
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryFilingCalendars(String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all filing calendars
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryFilingCalendarsAsync(String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingcalendars");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all filing requests
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * are reviewed and validated by Avalara Compliance before being implemented.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryFilingRequests(String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingrequests");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all filing requests
     * 
     * This API is available by invitation only.
     * A "filing request" represents a request to change an existing filing calendar.  Filing requests
     * are reviewed and validated by Avalara Compliance before being implemented.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryFilingRequestsAsync(String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/filingrequests");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Approve all filings for the specified company in the given filing period.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle.
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public ArrayList<FilingModel> approveFilings(int companyId, short year, byte month, ApproveFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){})).call();
    }

    /**
     * Approve all filings for the specified company in the given filing period.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle.
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public Future<ArrayList<FilingModel>> approveFilingsAsync(int companyId, short year, byte month, ApproveFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){}));
    }

    /**
     * Approve all filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle.
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param country The two-character ISO-3166 code for the country.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public ArrayList<FilingModel> approveFilingsCountry(int companyId, short year, byte month, String country, ApproveFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return ((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){})).call();
    }

    /**
     * Approve all filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle.
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param country The two-character ISO-3166 code for the country.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public Future<ArrayList<FilingModel>> approveFilingsCountryAsync(int companyId, short year, byte month, String country, ApproveFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){}));
    }

    /**
     * Approve all filings for the specified company in the given filing period, country and region.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public ArrayList<FilingModel> approveFilingsCountryRegion(int companyId, short year, byte month, String country, String region, ApproveFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return ((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){})).call();
    }

    /**
     * Approve all filings for the specified company in the given filing period, country and region.
     * 
     * This API is available by invitation only.
     * Approving a return means the customer is ready to let Avalara file that return.
     * Customer either approves themselves from admin console, 
     * else system auto-approves the night before the filing cycle
     * Sometimes Compliance has to manually unapprove and reapprove to modify liability or filing for the customer.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to approve.
     * @param month The month of the filing period to approve.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param model The approve request you wish to execute.
     * @return ArrayList<FilingModel>
     */
    public Future<ArrayList<FilingModel>> approveFilingsCountryRegionAsync(int companyId, short year, byte month, String country, String region, ApproveFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/approve");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return this.threadPool.submit((RestCall<ArrayList<FilingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingModel>>(){}));
    }

    /**
     * Add an adjustment to a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API creates a new adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param year The year of the filing's filing period being adjusted.
     * @param month The month of the filing's filing period being adjusted.
     * @param country The two-character ISO-3166 code for the country of the filing being adjusted.
     * @param region The two or three character region code for the region.
     * @param formCode The unique code of the form being adjusted.
     * @param model A list of Adjustments to be created for the specified filing.
     * @return ArrayList<FilingAdjustmentModel>
     */
    public ArrayList<FilingAdjustmentModel> createReturnAdjustment(int companyId, short year, byte month, String country, String region, String formCode, ArrayList<FilingAdjustmentModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}/adjust");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return ((RestCall<ArrayList<FilingAdjustmentModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingAdjustmentModel>>(){})).call();
    }

    /**
     * Add an adjustment to a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API creates a new adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param year The year of the filing's filing period being adjusted.
     * @param month The month of the filing's filing period being adjusted.
     * @param country The two-character ISO-3166 code for the country of the filing being adjusted.
     * @param region The two or three character region code for the region.
     * @param formCode The unique code of the form being adjusted.
     * @param model A list of Adjustments to be created for the specified filing.
     * @return ArrayList<FilingAdjustmentModel>
     */
    public Future<ArrayList<FilingAdjustmentModel>> createReturnAdjustmentAsync(int companyId, short year, byte month, String country, String region, String formCode, ArrayList<FilingAdjustmentModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}/adjust");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return this.threadPool.submit((RestCall<ArrayList<FilingAdjustmentModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingAdjustmentModel>>(){}));
    }

    /**
     * Add an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API creates a new augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param year The month of the filing's filing period being changed.
     * @param month The month of the filing's filing period being changed.
     * @param country The two-character ISO-3166 code for the country of the filing being changed.
     * @param region The two or three character region code for the region of the filing being changed.
     * @param formCode The unique code of the form being changed.
     * @param model A list of augmentations to be created for the specified filing.
     * @return ArrayList<FilingAugmentationModel>
     */
    public ArrayList<FilingAugmentationModel> createReturnAugmentation(int companyId, short year, byte month, String country, String region, String formCode, ArrayList<FilingAugmentationModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}/augment");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return ((RestCall<ArrayList<FilingAugmentationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingAugmentationModel>>(){})).call();
    }

    /**
     * Add an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API creates a new augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param year The month of the filing's filing period being changed.
     * @param month The month of the filing's filing period being changed.
     * @param country The two-character ISO-3166 code for the country of the filing being changed.
     * @param region The two or three character region code for the region of the filing being changed.
     * @param formCode The unique code of the form being changed.
     * @param model A list of augmentations to be created for the specified filing.
     * @return ArrayList<FilingAugmentationModel>
     */
    public Future<ArrayList<FilingAugmentationModel>> createReturnAugmentationAsync(int companyId, short year, byte month, String country, String region, String formCode, ArrayList<FilingAugmentationModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}/augment");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return this.threadPool.submit((RestCall<ArrayList<FilingAugmentationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<FilingAugmentationModel>>(){}));
    }

    /**
     * Delete an adjustment for a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API deletes an adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param id The ID of the adjustment being deleted.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteReturnAdjustment(int companyId, long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/adjust/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete an adjustment for a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API deletes an adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param id The ID of the adjustment being deleted.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteReturnAdjustmentAsync(int companyId, long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/adjust/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Delete an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API deletes an augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param id The ID of the augmentation being added.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteReturnAugmentation(int companyId, long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/augment/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API deletes an augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param id The ID of the augmentation being added.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteReturnAugmentationAsync(int companyId, long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/augment/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve worksheet checkup report for company and filing period.
     * 
     * @param worksheetId The unique id of the worksheet.
     * @param companyId The unique ID of the company that owns the worksheet.
     * @return FilingsCheckupModel
     */
    public FilingsCheckupModel filingsCheckupReport(int worksheetId, int companyId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{worksheetId}/checkup");
        path.applyField("worksheetId", worksheetId);
        path.applyField("companyId", companyId);
        return ((RestCall<FilingsCheckupModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingsCheckupModel>(){})).call();
    }

    /**
     * Retrieve worksheet checkup report for company and filing period.
     * 
     * @param worksheetId The unique id of the worksheet.
     * @param companyId The unique ID of the company that owns the worksheet.
     * @return FilingsCheckupModel
     */
    public Future<FilingsCheckupModel> filingsCheckupReportAsync(int worksheetId, int companyId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{worksheetId}/checkup");
        path.applyField("worksheetId", worksheetId);
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FilingsCheckupModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingsCheckupModel>(){}));
    }

    /**
     * Retrieve worksheet checkup report for company and filing period.
     * 
     * @param companyId The unique ID of the company that owns the worksheets object.
     * @param year The year of the filing period.
     * @param month The month of the filing period.
     * @return FilingsCheckupModel
     */
    public FilingsCheckupModel filingsCheckupReports(int companyId, int year, int month) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/checkup");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<FilingsCheckupModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingsCheckupModel>(){})).call();
    }

    /**
     * Retrieve worksheet checkup report for company and filing period.
     * 
     * @param companyId The unique ID of the company that owns the worksheets object.
     * @param year The year of the filing period.
     * @param month The month of the filing period.
     * @return FilingsCheckupModel
     */
    public Future<FilingsCheckupModel> filingsCheckupReportsAsync(int companyId, int year, int month) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/checkup");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<FilingsCheckupModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FilingsCheckupModel>(){}));
    }

    /**
     * Retrieve a single attachment for a filing
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param filingId The unique id of the worksheet return.
     * @param fileId The unique id of the document you are downloading
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getFilingAttachment(int companyId, long filingId, Long fileId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{filingId}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("filingId", filingId);
        path.addQuery("fileId", fileId);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Retrieve a single attachment for a filing
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param filingId The unique id of the worksheet return.
     * @param fileId The unique id of the document you are downloading
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> getFilingAttachmentAsync(int companyId, long filingId, Long fileId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{filingId}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("filingId", filingId);
        path.addQuery("fileId", fileId);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Retrieve a list of filings for the specified company in the year and month of a given filing period.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getFilingAttachments(int companyId, short year, byte month) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/attachments");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Retrieve a list of filings for the specified company in the year and month of a given filing period.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> getFilingAttachmentsAsync(int companyId, short year, byte month) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/attachments");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Retrieve a single trace file for a company filing period
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> getFilingAttachmentsTraceFile(int companyId, short year, byte month) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/attachments/tracefile");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Retrieve a single trace file for a company filing period
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> getFilingAttachmentsTraceFileAsync(int companyId, short year, byte month) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/attachments/tracefile");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Retrieve a list of filings for the specified company in the year and month of a given filing period.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getFilings(int companyId, short year, byte month) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve a list of filings for the specified company in the year and month of a given filing period.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getFilingsAsync(int companyId, short year, byte month) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve a list of filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getFilingsByCountry(int companyId, short year, byte month, String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve a list of filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getFilingsByCountryAsync(int companyId, short year, byte month, String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve a list of filings for the specified company in the filing period, country and region.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getFilingsByCountryRegion(int companyId, short year, byte month, String country, String region) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve a list of filings for the specified company in the filing period, country and region.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getFilingsByCountryRegionAsync(int companyId, short year, byte month, String country, String region) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve a list of filings for the specified company in the given filing period, country, region and form.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param formCode The unique code of the form.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getFilingsByReturnName(int companyId, short year, byte month, String country, String region, String formCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve a list of filings for the specified company in the given filing period, country, region and form.
     * 
     * This API is available by invitation only.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period.
     * @param month The two digit month of the filing period.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param formCode The unique code of the form.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getFilingsByReturnNameAsync(int companyId, short year, byte month, String country, String region, String formCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/{formCode}");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        path.applyField("formCode", formCode);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed (worksheet) for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> rebuildFilings(int companyId, short year, byte month, RebuildFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed (worksheet) for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> rebuildFilingsAsync(int companyId, short year, byte month, RebuildFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed (worksheet) for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param country The two-character ISO-3166 code for the country.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> rebuildFilingsByCountry(int companyId, short year, byte month, String country, RebuildFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period and country.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed (worksheet) for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param country The two-character ISO-3166 code for the country.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> rebuildFilingsByCountryAsync(int companyId, short year, byte month, String country, RebuildFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period, country and region.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.        
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> rebuildFilingsByCountryRegion(int companyId, short year, byte month, String country, String region, RebuildFilingsModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Rebuild a set of filings for the specified company in the given filing period, country and region.
     * 
     * This API is available by invitation only.
     * Rebuilding a return means re-creating or updating the amounts to be filed for a filing.
     * Rebuilding has to be done whenever a customer adds transactions to a filing.        
     * A "filing period" is the year and month of the date of the latest customer transaction allowed to be reported on a filing, 
     * based on filing frequency of filing.
     * 
     * @param companyId The ID of the company that owns the filings.
     * @param year The year of the filing period to be rebuilt.
     * @param month The month of the filing period to be rebuilt.
     * @param country The two-character ISO-3166 code for the country.
     * @param region The two or three character region code for the region.
     * @param model The rebuild request you wish to execute.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> rebuildFilingsByCountryRegionAsync(int companyId, short year, byte month, String country, String region, RebuildFilingsModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/{year}/{month}/{country}/{region}/rebuild");
        path.applyField("companyId", companyId);
        path.applyField("year", year);
        path.applyField("month", month);
        path.applyField("country", country);
        path.applyField("region", region);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Edit an adjustment for a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API modifies an adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param id The ID of the adjustment being edited.
     * @param model The updated Adjustment.
     * @return FilingAdjustmentModel
     */
    public FilingAdjustmentModel updateReturnAdjustment(int companyId, long id, FilingAdjustmentModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/adjust/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingAdjustmentModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingAdjustmentModel>(){})).call();
    }

    /**
     * Edit an adjustment for a given filing.
     * 
     * This API is available by invitation only.
     * An "Adjustment" is usually an increase or decrease to customer funding to Avalara,
     * such as early filer discount amounts that are refunded to the customer, or efile fees from websites. 
     * Sometimes may be a manual change in tax liability similar to an augmentation.
     * This API modifies an adjustment for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being adjusted.
     * @param id The ID of the adjustment being edited.
     * @param model The updated Adjustment.
     * @return FilingAdjustmentModel
     */
    public Future<FilingAdjustmentModel> updateReturnAdjustmentAsync(int companyId, long id, FilingAdjustmentModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/adjust/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingAdjustmentModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingAdjustmentModel>(){}));
    }

    /**
     * Edit an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API modifies an augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param id The ID of the augmentation being edited.
     * @param model The updated Augmentation.
     * @return FilingModel
     */
    public FilingModel updateReturnAugmentation(int companyId, long id, FilingAugmentationModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/augment/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<FilingModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingModel>(){})).call();
    }

    /**
     * Edit an augmentation for a given filing.
     * 
     * This API is available by invitation only.
     * An "Augmentation" is a manually added increase or decrease in tax liability, by either customer or Avalara 
     * usually due to customer wanting to report tax Avatax does not support, e.g. bad debts, rental tax.
     * This API modifies an augmentation for an existing tax filing.
     * 
     * @param companyId The ID of the company that owns the filing being changed.
     * @param id The ID of the augmentation being edited.
     * @param model The updated Augmentation.
     * @return FilingModel
     */
    public Future<FilingModel> updateReturnAugmentationAsync(int companyId, long id, FilingAugmentationModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/filings/augment/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FilingModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<FilingModel>(){}));
    }

    /**
     * FREE API - Request a free trial of AvaTax
     * 
     * Call this API to obtain a free AvaTax sandbox account.
     * 
     * This API is free to use.  No authentication credentials are required to call this API.
     * The account will grant a full trial version of AvaTax (e.g. AvaTaxPro) for a limited period of time.
     * After this introductory period, you may continue to use the free TaxRates API.
     * 
     * Limitations on free trial accounts:
     *             
     * * Only one free trial per company.
     * * The free trial account does not expire.
     * * Includes a limited time free trial of AvaTaxPro; after that date, the free TaxRates API will continue to work.
     * 
     * @param model Required information to provision a free trial account.
     * @return NewAccountModel
     */
    public NewAccountModel requestFreeTrial(FreeTrialRequestModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/freetrials/request");
        return ((RestCall<NewAccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<NewAccountModel>(){})).call();
    }

    /**
     * FREE API - Request a free trial of AvaTax
     * 
     * Call this API to obtain a free AvaTax sandbox account.
     * 
     * This API is free to use.  No authentication credentials are required to call this API.
     * The account will grant a full trial version of AvaTax (e.g. AvaTaxPro) for a limited period of time.
     * After this introductory period, you may continue to use the free TaxRates API.
     * 
     * Limitations on free trial accounts:
     *             
     * * Only one free trial per company.
     * * The free trial account does not expire.
     * * Includes a limited time free trial of AvaTaxPro; after that date, the free TaxRates API will continue to work.
     * 
     * @param model Required information to provision a free trial account.
     * @return NewAccountModel
     */
    public Future<NewAccountModel> requestFreeTrialAsync(FreeTrialRequestModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/freetrials/request");
        return this.threadPool.submit((RestCall<NewAccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<NewAccountModel>(){}));
    }

    /**
     * FREE API - Sales tax rates for a specified address
     * 
     * # Free-To-Use
     * 
     * The TaxRates API is a free-to-use, no cost option for estimating sales tax rates.
     * Any customer can request a free AvaTax account and make use of the TaxRates API.
     * However, this API is currently limited for US only
     * 
     * Note that the TaxRates API assumes the sale of general tangible personal property when estimating the sales tax
     * rate for a specified address.  Avalara provides the `CreateTransaction` API, which provides extensive tax calculation 
     * support for scenarios including, but not limited to:
     * 
     * * Nexus declarations
     * * Taxability based on product/service type
     * * Sourcing rules affecting origin/destination states
     * * Customers who are exempt from certain taxes
     * * States that have dollar value thresholds for tax amounts
     * * Refunds for products purchased on a different date
     * * Detailed jurisdiction names and state assigned codes
     * * And more!
     * 
     * Please see [Estimating Tax with REST v2](http://developer.avalara.com/blog/2016/11/04/estimating-tax-with-rest-v2/)
     * 
     * @param line1 The street address of the location.
     * @param line2 The street address of the location.
     * @param line3 The street address of the location.
     * @param city The city name of the location.
     * @param region The state or region of the location
     * @param postalCode The postal code of the location.
     * @param country The two letter ISO-3166 country code.
     * @return TaxRateModel
     */
    public TaxRateModel taxRatesByAddress(String line1, String line2, String line3, String city, String region, String postalCode, String country) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrates/byaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return ((RestCall<TaxRateModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRateModel>(){})).call();
    }

    /**
     * FREE API - Sales tax rates for a specified address
     * 
     * # Free-To-Use
     * 
     * The TaxRates API is a free-to-use, no cost option for estimating sales tax rates.
     * Any customer can request a free AvaTax account and make use of the TaxRates API.
     * However, this API is currently limited for US only
     * 
     * Note that the TaxRates API assumes the sale of general tangible personal property when estimating the sales tax
     * rate for a specified address.  Avalara provides the `CreateTransaction` API, which provides extensive tax calculation 
     * support for scenarios including, but not limited to:
     * 
     * * Nexus declarations
     * * Taxability based on product/service type
     * * Sourcing rules affecting origin/destination states
     * * Customers who are exempt from certain taxes
     * * States that have dollar value thresholds for tax amounts
     * * Refunds for products purchased on a different date
     * * Detailed jurisdiction names and state assigned codes
     * * And more!
     * 
     * Please see [Estimating Tax with REST v2](http://developer.avalara.com/blog/2016/11/04/estimating-tax-with-rest-v2/)
     * 
     * @param line1 The street address of the location.
     * @param line2 The street address of the location.
     * @param line3 The street address of the location.
     * @param city The city name of the location.
     * @param region The state or region of the location
     * @param postalCode The postal code of the location.
     * @param country The two letter ISO-3166 country code.
     * @return TaxRateModel
     */
    public Future<TaxRateModel> taxRatesByAddressAsync(String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrates/byaddress");
        path.addQuery("line1", line1);
        path.addQuery("line2", line2);
        path.addQuery("line3", line3);
        path.addQuery("city", city);
        path.addQuery("region", region);
        path.addQuery("postalCode", postalCode);
        path.addQuery("country", country);
        return this.threadPool.submit((RestCall<TaxRateModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRateModel>(){}));
    }

    /**
     * FREE API - Sales tax rates for a specified country and postal code
     * 
     * # Free-To-Use
     * 
     * The TaxRates API is a free-to-use, no cost option for estimating sales tax rates.
     * Any customer can request a free AvaTax account and make use of the TaxRates API.
     * However, this API is currently limited for US only
     * 
     * Note that the TaxRates API assumes the sale of general tangible personal property when estimating the sales tax
     * rate for a specified address.  Avalara provides the `CreateTransaction` API, which provides extensive tax calculation 
     * support for scenarios including, but not limited to:
     * 
     * * Nexus declarations
     * * Taxability based on product/service type
     * * Sourcing rules affecting origin/destination states
     * * Customers who are exempt from certain taxes
     * * States that have dollar value thresholds for tax amounts
     * * Refunds for products purchased on a different date
     * * Detailed jurisdiction names and state assigned codes
     * * And more!
     * 
     * Please see [Estimating Tax with REST v2](http://developer.avalara.com/blog/2016/11/04/estimating-tax-with-rest-v2/)
     * 
     * @param country The two letter ISO-3166 country code.
     * @param postalCode The postal code of the location.
     * @return TaxRateModel
     */
    public TaxRateModel taxRatesByPostalCode(String country, String postalCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrates/bypostalcode");
        path.addQuery("country", country);
        path.addQuery("postalCode", postalCode);
        return ((RestCall<TaxRateModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRateModel>(){})).call();
    }

    /**
     * FREE API - Sales tax rates for a specified country and postal code
     * 
     * # Free-To-Use
     * 
     * The TaxRates API is a free-to-use, no cost option for estimating sales tax rates.
     * Any customer can request a free AvaTax account and make use of the TaxRates API.
     * However, this API is currently limited for US only
     * 
     * Note that the TaxRates API assumes the sale of general tangible personal property when estimating the sales tax
     * rate for a specified address.  Avalara provides the `CreateTransaction` API, which provides extensive tax calculation 
     * support for scenarios including, but not limited to:
     * 
     * * Nexus declarations
     * * Taxability based on product/service type
     * * Sourcing rules affecting origin/destination states
     * * Customers who are exempt from certain taxes
     * * States that have dollar value thresholds for tax amounts
     * * Refunds for products purchased on a different date
     * * Detailed jurisdiction names and state assigned codes
     * * And more!
     * 
     * Please see [Estimating Tax with REST v2](http://developer.avalara.com/blog/2016/11/04/estimating-tax-with-rest-v2/)
     * 
     * @param country The two letter ISO-3166 country code.
     * @param postalCode The postal code of the location.
     * @return TaxRateModel
     */
    public Future<TaxRateModel> taxRatesByPostalCodeAsync(String country, String postalCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrates/bypostalcode");
        path.addQuery("country", country);
        path.addQuery("postalCode", postalCode);
        return this.threadPool.submit((RestCall<TaxRateModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRateModel>(){}));
    }

    /**
     * Request the javascript for a funding setup widget
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API returns back the actual javascript code to insert into your application to render the 
     * JavaScript funding setup widget inline.
     * Use the 'methodReturn.javaScript' return value to insert this widget into your HTML page.
     * 
     * @param id The unique ID number of this funding request
     * @return FundingStatusModel
     */
    public FundingStatusModel activateFundingRequest(long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/fundingrequests/{id}/widget");
        path.applyField("id", id);
        return ((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FundingStatusModel>(){})).call();
    }

    /**
     * Request the javascript for a funding setup widget
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API returns back the actual javascript code to insert into your application to render the 
     * JavaScript funding setup widget inline.
     * Use the 'methodReturn.javaScript' return value to insert this widget into your HTML page.
     * 
     * @param id The unique ID number of this funding request
     * @return FundingStatusModel
     */
    public Future<FundingStatusModel> activateFundingRequestAsync(long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/fundingrequests/{id}/widget");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FundingStatusModel>(){}));
    }

    /**
     * Retrieve status about a funding setup request
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API checks the status on an existing funding request.
     * 
     * @param id The unique ID number of this funding request
     * @return FundingStatusModel
     */
    public FundingStatusModel fundingRequestStatus(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/fundingrequests/{id}");
        path.applyField("id", id);
        return ((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FundingStatusModel>(){})).call();
    }

    /**
     * Retrieve status about a funding setup request
     * 
     * This API is available by invitation only.
     * Companies that use the Avalara Managed Returns or the SST Certified Service Provider services are 
     * required to setup their funding configuration before Avalara can begin filing tax returns on their 
     * behalf.
     * Funding configuration for each company is set up by submitting a funding setup request, which can
     * be sent either via email or via an embedded HTML widget.
     * When the funding configuration is submitted to Avalara, it will be reviewed by treasury team members
     * before approval.
     * This API checks the status on an existing funding request.
     * 
     * @param id The unique ID number of this funding request
     * @return FundingStatusModel
     */
    public Future<FundingStatusModel> fundingRequestStatusAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/fundingrequests/{id}");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<FundingStatusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FundingStatusModel>(){}));
    }

    /**
     * Create a new item
     * 
     * @param companyId The ID of the company that owns this item.
     * @param model The item you wish to create.
     * @return ArrayList<ItemModel>
     */
    public ArrayList<ItemModel> createItems(int companyId, ArrayList<ItemModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<ItemModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<ItemModel>>(){})).call();
    }

    /**
     * Create a new item
     * 
     * @param companyId The ID of the company that owns this item.
     * @param model The item you wish to create.
     * @return ArrayList<ItemModel>
     */
    public Future<ArrayList<ItemModel>> createItemsAsync(int companyId, ArrayList<ItemModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<ItemModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<ItemModel>>(){}));
    }

    /**
     * Delete a single item
     * 
     * @param companyId The ID of the company that owns this item.
     * @param id The ID of the item you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteItem(int companyId, long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single item
     * 
     * @param companyId The ID of the company that owns this item.
     * @param id The ID of the item you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteItemAsync(int companyId, long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single item
     * 
     * Get the item object identified by this URL.
     * 
     * @param companyId The ID of the company that owns this item object
     * @param id The primary key of this item
     * @return ItemModel
     */
    public ItemModel getItem(int companyId, long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ItemModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ItemModel>(){})).call();
    }

    /**
     * Retrieve a single item
     * 
     * Get the item object identified by this URL.
     * 
     * @param companyId The ID of the company that owns this item object
     * @param id The primary key of this item
     * @return ItemModel
     */
    public Future<ItemModel> getItemAsync(int companyId, long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ItemModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<ItemModel>(){}));
    }

    /**
     * Retrieve items for this company
     * 
     * List all items defined for the current company.
     * 
     * An 'Item' represents a product or service that your company offers for sale.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that defined these items
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listItemsByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve items for this company
     * 
     * List all items defined for the current company.
     * 
     * An 'Item' represents a product or service that your company offers for sale.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that defined these items
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listItemsByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all items
     * 
     * Get multiple item objects across all companies.
     * An 'Item' represents a product or service that your company offers for sale.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryItems(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/items");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all items
     * 
     * Get multiple item objects across all companies.
     * An 'Item' represents a product or service that your company offers for sale.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryItemsAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/items");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single item
     * 
     * Replace the existing item object at this URL with an updated object.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this item belongs to.
     * @param id The ID of the item you wish to update
     * @param model The item object you wish to update.
     * @return ItemModel
     */
    public ItemModel updateItem(int companyId, long id, ItemModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ItemModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<ItemModel>(){})).call();
    }

    /**
     * Update a single item
     * 
     * Replace the existing item object at this URL with an updated object.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this item belongs to.
     * @param id The ID of the item you wish to update
     * @param model The item object you wish to update.
     * @return ItemModel
     */
    public Future<ItemModel> updateItemAsync(int companyId, long id, ItemModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/items/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ItemModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<ItemModel>(){}));
    }

    /**
     * Create one or more overrides
     * 
     * Creates one or more jurisdiction override objects for this account.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * 
     * @param accountId The ID of the account that owns this override
     * @param model The jurisdiction override objects to create
     * @return ArrayList<JurisdictionOverrideModel>
     */
    public ArrayList<JurisdictionOverrideModel> createJurisdictionOverrides(int accountId, ArrayList<JurisdictionOverrideModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides");
        path.applyField("accountId", accountId);
        return ((RestCall<ArrayList<JurisdictionOverrideModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<JurisdictionOverrideModel>>(){})).call();
    }

    /**
     * Create one or more overrides
     * 
     * Creates one or more jurisdiction override objects for this account.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * 
     * @param accountId The ID of the account that owns this override
     * @param model The jurisdiction override objects to create
     * @return ArrayList<JurisdictionOverrideModel>
     */
    public Future<ArrayList<JurisdictionOverrideModel>> createJurisdictionOverridesAsync(int accountId, ArrayList<JurisdictionOverrideModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides");
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<ArrayList<JurisdictionOverrideModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<JurisdictionOverrideModel>>(){}));
    }

    /**
     * Delete a single override
     * 
     * @param accountId The ID of the account that owns this override
     * @param id The ID of the override you wish to delete
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteJurisdictionOverride(int accountId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single override
     * 
     * @param accountId The ID of the account that owns this override
     * @param id The ID of the override you wish to delete
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteJurisdictionOverrideAsync(int accountId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single override
     * 
     * Get the item object identified by this URL.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * 
     * @param accountId The ID of the account that owns this override
     * @param id The primary key of this override
     * @return JurisdictionOverrideModel
     */
    public JurisdictionOverrideModel getJurisdictionOverride(int accountId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<JurisdictionOverrideModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<JurisdictionOverrideModel>(){})).call();
    }

    /**
     * Retrieve a single override
     * 
     * Get the item object identified by this URL.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * 
     * @param accountId The ID of the account that owns this override
     * @param id The primary key of this override
     * @return JurisdictionOverrideModel
     */
    public Future<JurisdictionOverrideModel> getJurisdictionOverrideAsync(int accountId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<JurisdictionOverrideModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<JurisdictionOverrideModel>(){}));
    }

    /**
     * Retrieve overrides for this account
     * 
     * List all jurisdiction override objects defined for this account.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * to switch this address to use different taxing jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The ID of the account that owns this override
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listJurisdictionOverridesByAccount(int accountId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides");
        path.applyField("accountId", accountId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve overrides for this account
     * 
     * List all jurisdiction override objects defined for this account.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * to switch this address to use different taxing jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The ID of the account that owns this override
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listJurisdictionOverridesByAccountAsync(int accountId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides");
        path.applyField("accountId", accountId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all overrides
     * 
     * Get multiple jurisdiction override objects across all companies.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * to switch this address to use different taxing jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryJurisdictionOverrides(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/jurisdictionoverrides");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all overrides
     * 
     * Get multiple jurisdiction override objects across all companies.
     * 
     * A Jurisdiction Override is a configuration setting that allows you to select the taxing
     * jurisdiction for a specific address.  If you encounter an address that is on the boundary
     * between two different jurisdictions, you can choose to set up a jurisdiction override
     * to switch this address to use different taxing jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryJurisdictionOverridesAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/jurisdictionoverrides");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single jurisdictionoverride
     * 
     * @param accountId The ID of the account that this jurisdictionoverride belongs to.
     * @param id The ID of the jurisdictionoverride you wish to update
     * @param model The jurisdictionoverride object you wish to update.
     * @return JurisdictionOverrideModel
     */
    public JurisdictionOverrideModel updateJurisdictionOverride(int accountId, int id, JurisdictionOverrideModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<JurisdictionOverrideModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<JurisdictionOverrideModel>(){})).call();
    }

    /**
     * Update a single jurisdictionoverride
     * 
     * @param accountId The ID of the account that this jurisdictionoverride belongs to.
     * @param id The ID of the jurisdictionoverride you wish to update
     * @param model The jurisdictionoverride object you wish to update.
     * @return JurisdictionOverrideModel
     */
    public Future<JurisdictionOverrideModel> updateJurisdictionOverrideAsync(int accountId, int id, JurisdictionOverrideModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/jurisdictionoverrides/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<JurisdictionOverrideModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<JurisdictionOverrideModel>(){}));
    }

    /**
     * Point of sale data file generation
     * 
     * Builds a point-of-sale data file containing tax rates and rules for this location, containing tax rates for all
     * items defined for this company.  This data file can be used to correctly calculate tax in the event a 
     * point-of-sale device is not able to reach AvaTax.
     * This data file can be customized for specific partner devices and usage conditions.
     * The result of this API is the file you requested in the format you requested using the 'responseType' field.
     * 
     * @param companyId The ID number of the company that owns this location.
     * @param id The ID number of the location to retrieve point-of-sale data.
     * @param date The date for which point-of-sale data would be calculated (today by default)
     * @param format The format of the file (JSON by default) (See PointOfSaleFileType::* for a list of allowable values)
     * @param partnerId If specified, requests a custom partner-formatted version of the file. (See PointOfSalePartnerId::* for a list of allowable values)
     * @param includeJurisCodes When true, the file will include jurisdiction codes in the result.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> buildPointOfSaleDataForLocation(int companyId, int id, Date date, PointOfSaleFileType format, PointOfSalePartnerId partnerId, Boolean includeJurisCodes) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}/pointofsaledata");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        path.addQuery("date", date);
        path.addQuery("format", format);
        path.addQuery("partnerId", partnerId);
        path.addQuery("includeJurisCodes", includeJurisCodes);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Point of sale data file generation
     * 
     * Builds a point-of-sale data file containing tax rates and rules for this location, containing tax rates for all
     * items defined for this company.  This data file can be used to correctly calculate tax in the event a 
     * point-of-sale device is not able to reach AvaTax.
     * This data file can be customized for specific partner devices and usage conditions.
     * The result of this API is the file you requested in the format you requested using the 'responseType' field.
     * 
     * @param companyId The ID number of the company that owns this location.
     * @param id The ID number of the location to retrieve point-of-sale data.
     * @param date The date for which point-of-sale data would be calculated (today by default)
     * @param format The format of the file (JSON by default) (See PointOfSaleFileType::* for a list of allowable values)
     * @param partnerId If specified, requests a custom partner-formatted version of the file. (See PointOfSalePartnerId::* for a list of allowable values)
     * @param includeJurisCodes When true, the file will include jurisdiction codes in the result.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> buildPointOfSaleDataForLocationAsync(int companyId, int id, Date date, PointOfSaleFileType format, PointOfSalePartnerId partnerId, Boolean includeJurisCodes) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}/pointofsaledata");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        path.addQuery("date", date);
        path.addQuery("format", format);
        path.addQuery("partnerId", partnerId);
        path.addQuery("includeJurisCodes", includeJurisCodes);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Create a new location
     * 
     * @param companyId The ID of the company that owns this location.
     * @param model The location you wish to create.
     * @return ArrayList<LocationModel>
     */
    public ArrayList<LocationModel> createLocations(int companyId, ArrayList<LocationModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<LocationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<LocationModel>>(){})).call();
    }

    /**
     * Create a new location
     * 
     * @param companyId The ID of the company that owns this location.
     * @param model The location you wish to create.
     * @return ArrayList<LocationModel>
     */
    public Future<ArrayList<LocationModel>> createLocationsAsync(int companyId, ArrayList<LocationModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<LocationModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<LocationModel>>(){}));
    }

    /**
     * Delete a single location
     * 
     * @param companyId The ID of the company that owns this location.
     * @param id The ID of the location you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteLocation(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single location
     * 
     * @param companyId The ID of the company that owns this location.
     * @param id The ID of the location you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteLocationAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single location
     * 
     * Get the location object identified by this URL.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * 
     * @param companyId The ID of the company that owns this location
     * @param id The primary key of this location
     * @return LocationModel
     */
    public LocationModel getLocation(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<LocationModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LocationModel>(){})).call();
    }

    /**
     * Retrieve a single location
     * 
     * Get the location object identified by this URL.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * 
     * @param companyId The ID of the company that owns this location
     * @param id The primary key of this location
     * @return LocationModel
     */
    public Future<LocationModel> getLocationAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<LocationModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LocationModel>(){}));
    }

    /**
     * Retrieve locations for this company
     * 
     * List all location objects defined for this company.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * For more information on metadata requirements, see the '/api/v2/definitions/locationquestions' API.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these locations
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listLocationsByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve locations for this company
     * 
     * List all location objects defined for this company.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * For more information on metadata requirements, see the '/api/v2/definitions/locationquestions' API.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these locations
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listLocationsByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all locations
     * 
     * Get multiple location objects across all companies.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * For more information on metadata requirements, see the '/api/v2/definitions/locationquestions' API.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryLocations(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/locations");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all locations
     * 
     * Get multiple location objects across all companies.
     * An 'Location' represents a physical address where a company does business.
     * Many taxing authorities require that you define a list of all locations where your company does business.
     * These locations may require additional custom configuration or tax registration with these authorities.
     * For more information on metadata requirements, see the '/api/v2/definitions/locationquestions' API.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryLocationsAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/locations");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single location
     * 
     * Replace the existing location object at this URL with an updated object.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this location belongs to.
     * @param id The ID of the location you wish to update
     * @param model The location you wish to update.
     * @return LocationModel
     */
    public LocationModel updateLocation(int companyId, int id, LocationModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<LocationModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<LocationModel>(){})).call();
    }

    /**
     * Update a single location
     * 
     * Replace the existing location object at this URL with an updated object.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this location belongs to.
     * @param id The ID of the location you wish to update
     * @param model The location you wish to update.
     * @return LocationModel
     */
    public Future<LocationModel> updateLocationAsync(int companyId, int id, LocationModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<LocationModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<LocationModel>(){}));
    }

    /**
     * Validate the location against local requirements
     * 
     * Returns validation information for this location.
     * This API call is intended to compare this location against the currently known taxing authority rules and regulations,
     * 
     * @param companyId The ID of the company that owns this location
     * @param id The primary key of this location
     * @return LocationValidationModel
     */
    public LocationValidationModel validateLocation(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}/validate");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<LocationValidationModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LocationValidationModel>(){})).call();
    }

    /**
     * Validate the location against local requirements
     * 
     * Returns validation information for this location.
     * This API call is intended to compare this location against the currently known taxing authority rules and regulations,
     * 
     * @param companyId The ID of the company that owns this location
     * @param id The primary key of this location
     * @return LocationValidationModel
     */
    public Future<LocationValidationModel> validateLocationAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/locations/{id}/validate");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<LocationValidationModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<LocationValidationModel>(){}));
    }

    /**
     * Create a new nexus
     * 
     * Creates one or more new nexus objects attached to this company.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * Note that not all fields within a nexus can be updated; Avalara publishes a list of all defined nexus at the
     * '/api/v2/definitions/nexus' endpoint.
     * 
     * @param companyId The ID of the company that owns this nexus.
     * @param model The nexus you wish to create.
     * @return ArrayList<NexusModel>
     */
    public ArrayList<NexusModel> createNexus(int companyId, ArrayList<NexusModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<NexusModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NexusModel>>(){})).call();
    }

    /**
     * Create a new nexus
     * 
     * Creates one or more new nexus objects attached to this company.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * Note that not all fields within a nexus can be updated; Avalara publishes a list of all defined nexus at the
     * '/api/v2/definitions/nexus' endpoint.
     * 
     * @param companyId The ID of the company that owns this nexus.
     * @param model The nexus you wish to create.
     * @return ArrayList<NexusModel>
     */
    public Future<ArrayList<NexusModel>> createNexusAsync(int companyId, ArrayList<NexusModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<NexusModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NexusModel>>(){}));
    }

    /**
     * Delete a single nexus
     * 
     * @param companyId The ID of the company that owns this nexus.
     * @param id The ID of the nexus you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteNexus(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single nexus
     * 
     * @param companyId The ID of the company that owns this nexus.
     * @param id The ID of the nexus you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteNexusAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single nexus
     * 
     * Get the nexus object identified by this URL.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * 
     * @param companyId The ID of the company that owns this nexus object
     * @param id The primary key of this nexus
     * @return NexusModel
     */
    public NexusModel getNexus(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<NexusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusModel>(){})).call();
    }

    /**
     * Retrieve a single nexus
     * 
     * Get the nexus object identified by this URL.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * 
     * @param companyId The ID of the company that owns this nexus object
     * @param id The primary key of this nexus
     * @return NexusModel
     */
    public Future<NexusModel> getNexusAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<NexusModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusModel>(){}));
    }

    /**
     * List company nexus related to a tax form
     * 
     * Retrieves a list of nexus related to a tax form.
     * 
     * The concept of `Nexus` indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * 
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * This API is intended to provide useful information when examining a tax form.  If you are about to begin filing
     * a tax form, you may want to know whether you have declared nexus in all the jurisdictions related to that tax 
     * 
     * @param companyId The ID of the company that owns this nexus object
     * @param formCode The form code that we are looking up the nexus for
     * @return NexusByTaxFormModel
     */
    public NexusByTaxFormModel getNexusByFormCode(int companyId, String formCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/byform/{formCode}");
        path.applyField("companyId", companyId);
        path.applyField("formCode", formCode);
        return ((RestCall<NexusByTaxFormModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusByTaxFormModel>(){})).call();
    }

    /**
     * List company nexus related to a tax form
     * 
     * Retrieves a list of nexus related to a tax form.
     * 
     * The concept of `Nexus` indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * 
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * This API is intended to provide useful information when examining a tax form.  If you are about to begin filing
     * a tax form, you may want to know whether you have declared nexus in all the jurisdictions related to that tax 
     * 
     * @param companyId The ID of the company that owns this nexus object
     * @param formCode The form code that we are looking up the nexus for
     * @return NexusByTaxFormModel
     */
    public Future<NexusByTaxFormModel> getNexusByFormCodeAsync(int companyId, String formCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/byform/{formCode}");
        path.applyField("companyId", companyId);
        path.applyField("formCode", formCode);
        return this.threadPool.submit((RestCall<NexusByTaxFormModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NexusByTaxFormModel>(){}));
    }

    /**
     * Retrieve nexus for this company
     * 
     * List all nexus objects defined for this company.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these nexus objects
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNexusByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve nexus for this company
     * 
     * List all nexus objects defined for this company.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these nexus objects
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNexusByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all nexus
     * 
     * Get multiple nexus objects across all companies.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryNexus(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/nexus");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all nexus
     * 
     * Get multiple nexus objects across all companies.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryNexusAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/nexus");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single nexus
     * 
     * Replace the existing nexus object at this URL with an updated object.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * Note that not all fields within a nexus can be updated; Avalara publishes a list of all defined nexus at the
     * '/api/v2/definitions/nexus' endpoint.
     * You may only define nexus matching the official list of declared nexus.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this nexus belongs to.
     * @param id The ID of the nexus you wish to update
     * @param model The nexus object you wish to update.
     * @return NexusModel
     */
    public NexusModel updateNexus(int companyId, int id, NexusModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<NexusModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<NexusModel>(){})).call();
    }

    /**
     * Update a single nexus
     * 
     * Replace the existing nexus object at this URL with an updated object.
     * The concept of 'Nexus' indicates a place where your company has sufficient physical presence and is obligated
     * to collect and remit transaction-based taxes.
     * When defining companies in AvaTax, you must declare nexus for your company in order to correctly calculate tax
     * in all jurisdictions affected by your transactions.
     * Note that not all fields within a nexus can be updated; Avalara publishes a list of all defined nexus at the
     * '/api/v2/definitions/nexus' endpoint.
     * You may only define nexus matching the official list of declared nexus.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this nexus belongs to.
     * @param id The ID of the nexus you wish to update
     * @param model The nexus object you wish to update.
     * @return NexusModel
     */
    public Future<NexusModel> updateNexusAsync(int companyId, int id, NexusModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/nexus/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<NexusModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<NexusModel>(){}));
    }

    /**
     * Create a new notice comment.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the comment for.
     * @param model The notice comments you wish to create.
     * @return ArrayList<NoticeCommentModel>
     */
    public ArrayList<NoticeCommentModel> createNoticeComment(int companyId, int id, ArrayList<NoticeCommentModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/comments");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<NoticeCommentModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeCommentModel>>(){})).call();
    }

    /**
     * Create a new notice comment.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the comment for.
     * @param model The notice comments you wish to create.
     * @return ArrayList<NoticeCommentModel>
     */
    public Future<ArrayList<NoticeCommentModel>> createNoticeCommentAsync(int companyId, int id, ArrayList<NoticeCommentModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/comments");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<NoticeCommentModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeCommentModel>>(){}));
    }

    /**
     * Create a new notice finance details.
     * 
     * This API is available by invitation only.
     * 'Notice finance details' is the categorical breakdown of the total charge levied by the tax authority on our customer,
     * as broken down in our "notice log" found in Workflow. Main examples of the categories are 'Tax Due', 'Interest', 'Penalty', 'Total Abated'.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the notice added to the finance details.
     * @param model The notice finance details you wish to create.
     * @return ArrayList<NoticeFinanceModel>
     */
    public ArrayList<NoticeFinanceModel> createNoticeFinanceDetails(int companyId, int id, ArrayList<NoticeFinanceModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/financedetails");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<NoticeFinanceModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeFinanceModel>>(){})).call();
    }

    /**
     * Create a new notice finance details.
     * 
     * This API is available by invitation only.
     * 'Notice finance details' is the categorical breakdown of the total charge levied by the tax authority on our customer,
     * as broken down in our "notice log" found in Workflow. Main examples of the categories are 'Tax Due', 'Interest', 'Penalty', 'Total Abated'.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the notice added to the finance details.
     * @param model The notice finance details you wish to create.
     * @return ArrayList<NoticeFinanceModel>
     */
    public Future<ArrayList<NoticeFinanceModel>> createNoticeFinanceDetailsAsync(int companyId, int id, ArrayList<NoticeFinanceModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/financedetails");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<NoticeFinanceModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeFinanceModel>>(){}));
    }

    /**
     * Create a new notice responsibility.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the responsibility for.
     * @param model The notice responsibilities you wish to create.
     * @return ArrayList<NoticeResponsibilityDetailModel>
     */
    public ArrayList<NoticeResponsibilityDetailModel> createNoticeResponsibilities(int companyId, int id, ArrayList<NoticeResponsibilityDetailModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/responsibilities");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<NoticeResponsibilityDetailModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeResponsibilityDetailModel>>(){})).call();
    }

    /**
     * Create a new notice responsibility.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the responsibility for.
     * @param model The notice responsibilities you wish to create.
     * @return ArrayList<NoticeResponsibilityDetailModel>
     */
    public Future<ArrayList<NoticeResponsibilityDetailModel>> createNoticeResponsibilitiesAsync(int companyId, int id, ArrayList<NoticeResponsibilityDetailModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/responsibilities");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<NoticeResponsibilityDetailModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeResponsibilityDetailModel>>(){}));
    }

    /**
     * Create a new notice root cause.
     * 
     * This API is available by invitation only.
     * 'Notice root causes' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the responsibility for.
     * @param model The notice root causes you wish to create.
     * @return ArrayList<NoticeRootCauseDetailModel>
     */
    public ArrayList<NoticeRootCauseDetailModel> createNoticeRootCauses(int companyId, int id, ArrayList<NoticeRootCauseDetailModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/rootcauses");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<NoticeRootCauseDetailModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeRootCauseDetailModel>>(){})).call();
    }

    /**
     * Create a new notice root cause.
     * 
     * This API is available by invitation only.
     * 'Notice root causes' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the tax notice we are adding the responsibility for.
     * @param model The notice root causes you wish to create.
     * @return ArrayList<NoticeRootCauseDetailModel>
     */
    public Future<ArrayList<NoticeRootCauseDetailModel>> createNoticeRootCausesAsync(int companyId, int id, ArrayList<NoticeRootCauseDetailModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/rootcauses");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<NoticeRootCauseDetailModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeRootCauseDetailModel>>(){}));
    }

    /**
     * Create a new notice.
     * 
     * This API is available by invitation only.
     * Create one or more new notice objects.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param model The notice object you wish to create.
     * @return ArrayList<NoticeModel>
     */
    public ArrayList<NoticeModel> createNotices(int companyId, ArrayList<NoticeModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<NoticeModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeModel>>(){})).call();
    }

    /**
     * Create a new notice.
     * 
     * This API is available by invitation only.
     * Create one or more new notice objects.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param model The notice object you wish to create.
     * @return ArrayList<NoticeModel>
     */
    public Future<ArrayList<NoticeModel>> createNoticesAsync(int companyId, ArrayList<NoticeModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<NoticeModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<NoticeModel>>(){}));
    }

    /**
     * Delete a single notice.
     * 
     * This API is available by invitation only.
     * Mark the existing notice object at this URL as deleted.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the notice you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteNotice(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single notice.
     * 
     * This API is available by invitation only.
     * Mark the existing notice object at this URL as deleted.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company that owns this notice.
     * @param id The ID of the notice you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteNoticeAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single attachment
     * 
     * This API is available by invitation only.
     * 
     * @param companyId The ID of the company for this attachment.
     * @param id The ResourceFileId of the attachment to download.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> downloadNoticeAttachment(int companyId, long id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/files/{id}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Retrieve a single attachment
     * 
     * This API is available by invitation only.
     * 
     * @param companyId The ID of the company for this attachment.
     * @param id The ResourceFileId of the attachment to download.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> downloadNoticeAttachmentAsync(int companyId, long id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/files/{id}/attachment");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Retrieve a single notice.
     * 
     * This API is available by invitation only.
     * Get the tax notice object identified by this URL.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company for this notice.
     * @param id The ID of this notice.
     * @return NoticeModel
     */
    public NoticeModel getNotice(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<NoticeModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NoticeModel>(){})).call();
    }

    /**
     * Retrieve a single notice.
     * 
     * This API is available by invitation only.
     * Get the tax notice object identified by this URL.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param companyId The ID of the company for this notice.
     * @param id The ID of this notice.
     * @return NoticeModel
     */
    public Future<NoticeModel> getNoticeAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<NoticeModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<NoticeModel>(){}));
    }

    /**
     * Retrieve notice comments for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getNoticeComments(int id, int companyId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/comments");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve notice comments for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice comments' are updates by the notice team on the work to be done and that has been done so far on a notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getNoticeCommentsAsync(int id, int companyId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/comments");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve notice finance details for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice finance details' is the categorical breakdown of the total charge levied by the tax authority on our customer,
     * as broken down in our "notice log" found in Workflow. Main examples of the categories are 'Tax Due', 'Interest', 'Penalty', 'Total Abated'.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the company that owns these notices.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getNoticeFinanceDetails(int id, int companyId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/financedetails");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve notice finance details for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice finance details' is the categorical breakdown of the total charge levied by the tax authority on our customer,
     * as broken down in our "notice log" found in Workflow. Main examples of the categories are 'Tax Due', 'Interest', 'Penalty', 'Total Abated'.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the company that owns these notices.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getNoticeFinanceDetailsAsync(int id, int companyId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/financedetails");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve notice responsibilities for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice responsibilities' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getNoticeResponsibilities(int id, int companyId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/responsibilities");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve notice responsibilities for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice responsibilities' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getNoticeResponsibilitiesAsync(int id, int companyId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/responsibilities");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve notice root causes for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice root causes' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> getNoticeRootCauses(int id, int companyId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/rootcauses");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve notice root causes for a specific notice.
     * 
     * This API is available by invitation only.
     * 'Notice root causes' are are those who are responsible for the notice.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * 
     * @param id The ID of the notice.
     * @param companyId The ID of the company that owns these notices.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> getNoticeRootCausesAsync(int id, int companyId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}/rootcauses");
        path.applyField("id", id);
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve notices for a company.
     * 
     * This API is available by invitation only.
     * List all tax notice objects assigned to this company.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these notices.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listNoticesByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve notices for a company.
     * 
     * This API is available by invitation only.
     * List all tax notice objects assigned to this company.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these notices.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listNoticesByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all notices.
     * 
     * This API is available by invitation only.
     * Get multiple notice objects across all companies.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryNotices(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/notices");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all notices.
     * 
     * This API is available by invitation only.
     * Get multiple notice objects across all companies.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryNoticesAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/notices");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single notice.
     * 
     * This API is available by invitation only.
     * Replace the existing notice object at this URL with an updated object.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this notice belongs to.
     * @param id The ID of the notice you wish to update.
     * @param model The notice object you wish to update.
     * @return NoticeModel
     */
    public NoticeModel updateNotice(int companyId, int id, NoticeModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<NoticeModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<NoticeModel>(){})).call();
    }

    /**
     * Update a single notice.
     * 
     * This API is available by invitation only.
     * Replace the existing notice object at this URL with an updated object.
     * A 'notice' represents a letter sent to a business by a tax authority regarding tax filing issues.  Avalara
     * Returns customers often receive support and assistance from the Compliance Notices team in handling notices received by taxing authorities.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this notice belongs to.
     * @param id The ID of the notice you wish to update.
     * @param model The notice object you wish to update.
     * @return NoticeModel
     */
    public Future<NoticeModel> updateNoticeAsync(int companyId, int id, NoticeModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<NoticeModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<NoticeModel>(){}));
    }

    /**
     * Retrieve a single attachment
     * 
     * This API is available by invitation only.
     * 
     * @param companyId The ID of the company for this attachment.
     * @param model The ResourceFileId of the attachment to download.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> uploadAttachment(int companyId, ResourceFileUploadRequestModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/files/attachment");
        path.applyField("companyId", companyId);
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Retrieve a single attachment
     * 
     * This API is available by invitation only.
     * 
     * @param companyId The ID of the company for this attachment.
     * @param model The ResourceFileId of the attachment to download.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> uploadAttachmentAsync(int companyId, ResourceFileUploadRequestModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/notices/files/attachment");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Request a new Avalara account
     * 
     * This API is for use by partner onboarding services customers only.
     * Calling this API creates an account with the specified product subscriptions, but does not configure billing.
     * The customer will receive information from Avalara about how to configure billing for their account.
     * 
     * @param model Information about the account you wish to create and the selected product offerings.
     * @return NewAccountModel
     */
    public NewAccountModel requestNewAccount(NewAccountRequestModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/request");
        return ((RestCall<NewAccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<NewAccountModel>(){})).call();
    }

    /**
     * Request a new Avalara account
     * 
     * This API is for use by partner onboarding services customers only.
     * Calling this API creates an account with the specified product subscriptions, but does not configure billing.
     * The customer will receive information from Avalara about how to configure billing for their account.
     * 
     * @param model Information about the account you wish to create and the selected product offerings.
     * @return NewAccountModel
     */
    public Future<NewAccountModel> requestNewAccountAsync(NewAccountRequestModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/request");
        return this.threadPool.submit((RestCall<NewAccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<NewAccountModel>(){}));
    }

    /**
     * Point of sale data file generation
     * 
     * Builds a point-of-sale data file containing tax rates and rules for items and locations that can be used
     * to correctly calculate tax in the event a point-of-sale device is not able to reach AvaTax.
     * This data file can be customized for specific partner devices and usage conditions.
     * The result of this API is the file you requested in the format you requested using the 'responseType' field.
     * 
     * @param model Parameters about the desired file format and report format, specifying which company, locations and TaxCodes to include.
     * @return HashMap<String, String>
     */
    public HashMap<String, String> buildPointOfSaleDataFile(PointOfSaleDataRequestModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/pointofsaledata/build");
        return ((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<HashMap<String, String>>(){})).call();
    }

    /**
     * Point of sale data file generation
     * 
     * Builds a point-of-sale data file containing tax rates and rules for items and locations that can be used
     * to correctly calculate tax in the event a point-of-sale device is not able to reach AvaTax.
     * This data file can be customized for specific partner devices and usage conditions.
     * The result of this API is the file you requested in the format you requested using the 'responseType' field.
     * 
     * @param model Parameters about the desired file format and report format, specifying which company, locations and TaxCodes to include.
     * @return HashMap<String, String>
     */
    public Future<HashMap<String, String>> buildPointOfSaleDataFileAsync(PointOfSaleDataRequestModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/pointofsaledata/build");
        return this.threadPool.submit((RestCall<HashMap<String, String>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<HashMap<String, String>>(){}));
    }

    /**
     * Change Password
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Allows a user to change their password via the API.
     * This API only allows the currently authenticated user to change their password; it cannot be used to apply to a
     * 
     * @param model An object containing your current password and the new password.
     * @return String
     */
    public String changePassword(PasswordChangeModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/passwords");
        return ((RestCall<String>)restCallFactory.createRestCall("Put", path, model, new TypeToken<String>(){})).call();
    }

    /**
     * Change Password
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Allows a user to change their password via the API.
     * This API only allows the currently authenticated user to change their password; it cannot be used to apply to a
     * 
     * @param model An object containing your current password and the new password.
     * @return String
     */
    public Future<String> changePasswordAsync(PasswordChangeModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/passwords");
        return this.threadPool.submit((RestCall<String>)restCallFactory.createRestCall("Put", path, model, new TypeToken<String>(){}));
    }

    /**
     * Create a new account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create a single new account object.  
     * 
     * @param model The account you wish to create.
     * @return AccountModel
     */
    public AccountModel createAccount(AccountModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts");
        return ((RestCall<AccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AccountModel>(){})).call();
    }

    /**
     * Create a new account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create a single new account object.  
     * 
     * @param model The account you wish to create.
     * @return AccountModel
     */
    public Future<AccountModel> createAccountAsync(AccountModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts");
        return this.threadPool.submit((RestCall<AccountModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<AccountModel>(){}));
    }

    /**
     * Create a new subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create one or more new subscription objects attached to this account.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * 
     * @param accountId The ID of the account that owns this subscription.
     * @param model The subscription you wish to create.
     * @return ArrayList<SubscriptionModel>
     */
    public ArrayList<SubscriptionModel> createSubscriptions(int accountId, ArrayList<SubscriptionModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions");
        path.applyField("accountId", accountId);
        return ((RestCall<ArrayList<SubscriptionModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<SubscriptionModel>>(){})).call();
    }

    /**
     * Create a new subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create one or more new subscription objects attached to this account.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * 
     * @param accountId The ID of the account that owns this subscription.
     * @param model The subscription you wish to create.
     * @return ArrayList<SubscriptionModel>
     */
    public Future<ArrayList<SubscriptionModel>> createSubscriptionsAsync(int accountId, ArrayList<SubscriptionModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions");
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<ArrayList<SubscriptionModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<SubscriptionModel>>(){}));
    }

    /**
     * Create new users
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create one or more new user objects attached to this account.
     * 
     * @param accountId The unique ID number of the account where these users will be created.
     * @param model The user or array of users you wish to create.
     * @return ArrayList<UserModel>
     */
    public ArrayList<UserModel> createUsers(int accountId, ArrayList<UserModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users");
        path.applyField("accountId", accountId);
        return ((RestCall<ArrayList<UserModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<UserModel>>(){})).call();
    }

    /**
     * Create new users
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Create one or more new user objects attached to this account.
     * 
     * @param accountId The unique ID number of the account where these users will be created.
     * @param model The user or array of users you wish to create.
     * @return ArrayList<UserModel>
     */
    public Future<ArrayList<UserModel>> createUsersAsync(int accountId, ArrayList<UserModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users");
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<ArrayList<UserModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<UserModel>>(){}));
    }

    /**
     * Delete a single account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Delete an account.
     * 
     * @param id The ID of the account you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteAccount(int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Delete an account.
     * 
     * @param id The ID of the account you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteAccountAsync(int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Delete a single subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param accountId The ID of the account that owns this subscription.
     * @param id The ID of the subscription you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteSubscription(int accountId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param accountId The ID of the account that owns this subscription.
     * @param id The ID of the subscription you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteSubscriptionAsync(int accountId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Delete a single user
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param id The ID of the user you wish to delete.
     * @param accountId The accountID of the user you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteUser(int id, int accountId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single user
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param id The ID of the user you wish to delete.
     * @param accountId The accountID of the user you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteUserAsync(int id, int accountId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve all accounts
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Get multiple account objects.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Subscriptions
     * * Users
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryAccounts(String include, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all accounts
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Get multiple account objects.
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Subscriptions
     * * Users
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryAccountsAsync(String include, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Reset a user's password programmatically
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Allows a system admin to reset the password for a specific user via the API.
     * This API is only available for Avalara Registrar Admins, and can be used to reset the password of any
     * 
     * @param userId The unique ID of the user whose password will be changed
     * @param model The new password for this user
     * @return String
     */
    public String resetPassword(int userId, SetPasswordModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/passwords/{userId}/reset");
        path.applyField("userId", userId);
        return ((RestCall<String>)restCallFactory.createRestCall("Post", path, model, new TypeToken<String>(){})).call();
    }

    /**
     * Reset a user's password programmatically
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Allows a system admin to reset the password for a specific user via the API.
     * This API is only available for Avalara Registrar Admins, and can be used to reset the password of any
     * 
     * @param userId The unique ID of the user whose password will be changed
     * @param model The new password for this user
     * @return String
     */
    public Future<String> resetPasswordAsync(int userId, SetPasswordModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/passwords/{userId}/reset");
        path.applyField("userId", userId);
        return this.threadPool.submit((RestCall<String>)restCallFactory.createRestCall("Post", path, model, new TypeToken<String>(){}));
    }

    /**
     * Update a single account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param id The ID of the account you wish to update.
     * @param model The account object you wish to update.
     * @return AccountModel
     */
    public AccountModel updateAccount(int id, AccountModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        return ((RestCall<AccountModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<AccountModel>(){})).call();
    }

    /**
     * Update a single account
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * 
     * @param id The ID of the account you wish to update.
     * @param model The account object you wish to update.
     * @return AccountModel
     */
    public Future<AccountModel> updateAccountAsync(int id, AccountModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{id}");
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<AccountModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<AccountModel>(){}));
    }

    /**
     * Update a single subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Replace the existing subscription object at this URL with an updated object.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param accountId The ID of the account that this subscription belongs to.
     * @param id The ID of the subscription you wish to update
     * @param model The subscription you wish to update.
     * @return SubscriptionModel
     */
    public SubscriptionModel updateSubscription(int accountId, int id, SubscriptionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<SubscriptionModel>(){})).call();
    }

    /**
     * Update a single subscription
     * 
     * # For Registrar Use Only
     * This API is for use by Avalara Registrar administrative users only.
     * 
     * Replace the existing subscription object at this URL with an updated object.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param accountId The ID of the account that this subscription belongs to.
     * @param id The ID of the subscription you wish to update
     * @param model The subscription you wish to update.
     * @return SubscriptionModel
     */
    public Future<SubscriptionModel> updateSubscriptionAsync(int accountId, int id, SubscriptionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<SubscriptionModel>(){}));
    }

    /**
     * Create a new setting
     * 
     * Create one or more new setting objects attached to this company.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * 
     * @param companyId The ID of the company that owns this setting.
     * @param model The setting you wish to create.
     * @return ArrayList<SettingModel>
     */
    public ArrayList<SettingModel> createSettings(int companyId, ArrayList<SettingModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<SettingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<SettingModel>>(){})).call();
    }

    /**
     * Create a new setting
     * 
     * Create one or more new setting objects attached to this company.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * 
     * @param companyId The ID of the company that owns this setting.
     * @param model The setting you wish to create.
     * @return ArrayList<SettingModel>
     */
    public Future<ArrayList<SettingModel>> createSettingsAsync(int companyId, ArrayList<SettingModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<SettingModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<SettingModel>>(){}));
    }

    /**
     * Delete a single setting
     * 
     * @param companyId The ID of the company that owns this setting.
     * @param id The ID of the setting you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteSetting(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single setting
     * 
     * @param companyId The ID of the company that owns this setting.
     * @param id The ID of the setting you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteSettingAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single setting
     * 
     * Get a single setting object by its unique ID.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * 
     * @param companyId The ID of the company that owns this setting
     * @param id The primary key of this setting
     * @return SettingModel
     */
    public SettingModel getSetting(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<SettingModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SettingModel>(){})).call();
    }

    /**
     * Retrieve a single setting
     * 
     * Get a single setting object by its unique ID.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * 
     * @param companyId The ID of the company that owns this setting
     * @param id The primary key of this setting
     * @return SettingModel
     */
    public Future<SettingModel> getSettingAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<SettingModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SettingModel>(){}));
    }

    /**
     * Retrieve all settings for this company
     * 
     * List all setting objects attached to this company.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these settings
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listSettingsByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all settings for this company
     * 
     * List all setting objects attached to this company.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these settings
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listSettingsByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all settings
     * 
     * Get multiple setting objects across all companies.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> querySettings(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/settings");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all settings
     * 
     * Get multiple setting objects across all companies.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> querySettingsAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/settings");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single setting
     * 
     * Replace the existing setting object at this URL with an updated object.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this setting belongs to.
     * @param id The ID of the setting you wish to update
     * @param model The setting you wish to update.
     * @return SettingModel
     */
    public SettingModel updateSetting(int companyId, int id, SettingModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<SettingModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<SettingModel>(){})).call();
    }

    /**
     * Update a single setting
     * 
     * Replace the existing setting object at this URL with an updated object.
     * A 'setting' is a piece of user-defined data that can be attached to a company, and it provides you the ability to store information
     * not defined or managed by Avalara.
     * You may create, update, and delete your own settings objects as required, and there is no mandatory data format for the 'name' and 
     * 'value' data fields.
     * To ensure correct operation of other programs or connectors, please create a new GUID for your application and use that value for
     * the 'set' data field.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this setting belongs to.
     * @param id The ID of the setting you wish to update
     * @param model The setting you wish to update.
     * @return SettingModel
     */
    public Future<SettingModel> updateSettingAsync(int companyId, int id, SettingModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/settings/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<SettingModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<SettingModel>(){}));
    }

    /**
     * Retrieve a single subscription
     * 
     * Get the subscription object identified by this URL.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * 
     * @param accountId The ID of the account that owns this subscription
     * @param id The primary key of this subscription
     * @return SubscriptionModel
     */
    public SubscriptionModel getSubscription(int accountId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return ((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SubscriptionModel>(){})).call();
    }

    /**
     * Retrieve a single subscription
     * 
     * Get the subscription object identified by this URL.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * 
     * @param accountId The ID of the account that owns this subscription
     * @param id The primary key of this subscription
     * @return SubscriptionModel
     */
    public Future<SubscriptionModel> getSubscriptionAsync(int accountId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions/{id}");
        path.applyField("accountId", accountId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SubscriptionModel>(){}));
    }

    /**
     * Retrieve subscriptions for this account
     * 
     * List all subscription objects attached to this account.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The ID of the account that owns these subscriptions
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listSubscriptionsByAccount(int accountId, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions");
        path.applyField("accountId", accountId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve subscriptions for this account
     * 
     * List all subscription objects attached to this account.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The ID of the account that owns these subscriptions
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listSubscriptionsByAccountAsync(int accountId, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/subscriptions");
        path.applyField("accountId", accountId);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all subscriptions
     * 
     * Get multiple subscription objects across all accounts.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> querySubscriptions(String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/subscriptions");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all subscriptions
     * 
     * Get multiple subscription objects across all accounts.
     * A 'subscription' indicates a licensed subscription to a named Avalara service.
     * To request or remove subscriptions, please contact Avalara sales or your customer account manager.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> querySubscriptionsAsync(String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/subscriptions");
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Create a new tax code
     * 
     * Create one or more new taxcode objects attached to this company.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @param companyId The ID of the company that owns this tax code.
     * @param model The tax code you wish to create.
     * @return ArrayList<TaxCodeModel>
     */
    public ArrayList<TaxCodeModel> createTaxCodes(int companyId, ArrayList<TaxCodeModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<TaxCodeModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<TaxCodeModel>>(){})).call();
    }

    /**
     * Create a new tax code
     * 
     * Create one or more new taxcode objects attached to this company.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @param companyId The ID of the company that owns this tax code.
     * @param model The tax code you wish to create.
     * @return ArrayList<TaxCodeModel>
     */
    public Future<ArrayList<TaxCodeModel>> createTaxCodesAsync(int companyId, ArrayList<TaxCodeModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<TaxCodeModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<TaxCodeModel>>(){}));
    }

    /**
     * Delete a single tax code
     * 
     * @param companyId The ID of the company that owns this tax code.
     * @param id The ID of the tax code you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteTaxCode(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single tax code
     * 
     * @param companyId The ID of the company that owns this tax code.
     * @param id The ID of the tax code you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteTaxCodeAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single tax code
     * 
     * Get the taxcode object identified by this URL.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @param companyId The ID of the company that owns this tax code
     * @param id The primary key of this tax code
     * @return TaxCodeModel
     */
    public TaxCodeModel getTaxCode(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<TaxCodeModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxCodeModel>(){})).call();
    }

    /**
     * Retrieve a single tax code
     * 
     * Get the taxcode object identified by this URL.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * 
     * @param companyId The ID of the company that owns this tax code
     * @param id The primary key of this tax code
     * @return TaxCodeModel
     */
    public Future<TaxCodeModel> getTaxCodeAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<TaxCodeModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxCodeModel>(){}));
    }

    /**
     * Retrieve tax codes for this company
     * 
     * List all taxcode objects attached to this company.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these tax codes
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxCodesByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve tax codes for this company
     * 
     * List all taxcode objects attached to this company.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these tax codes
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxCodesByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all tax codes
     * 
     * Get multiple taxcode objects across all companies.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryTaxCodes(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxcodes");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all tax codes
     * 
     * Get multiple taxcode objects across all companies.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryTaxCodesAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxcodes");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single tax code
     * 
     * Replace the existing taxcode object at this URL with an updated object.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this tax code belongs to.
     * @param id The ID of the tax code you wish to update
     * @param model The tax code you wish to update.
     * @return TaxCodeModel
     */
    public TaxCodeModel updateTaxCode(int companyId, int id, TaxCodeModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<TaxCodeModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<TaxCodeModel>(){})).call();
    }

    /**
     * Update a single tax code
     * 
     * Replace the existing taxcode object at this URL with an updated object.
     * A 'TaxCode' represents a uniquely identified type of product, good, or service.
     * Avalara supports correct tax rates and taxability rules for all TaxCodes in all supported jurisdictions.
     * If you identify your products by tax code in your 'Create Transacion' API calls, Avalara will correctly calculate tax rates and
     * taxability rules for this product in all supported jurisdictions.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this tax code belongs to.
     * @param id The ID of the tax code you wish to update
     * @param model The tax code you wish to update.
     * @return TaxCodeModel
     */
    public Future<TaxCodeModel> updateTaxCodeAsync(int companyId, int id, TaxCodeModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxcodes/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<TaxCodeModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<TaxCodeModel>(){}));
    }

    /**
     * Create a new tax rule
     * 
     * Create one or more new taxrule objects attached to this company.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * 
     * @param companyId The ID of the company that owns this tax rule.
     * @param model The tax rule you wish to create.
     * @return ArrayList<TaxRuleModel>
     */
    public ArrayList<TaxRuleModel> createTaxRules(int companyId, ArrayList<TaxRuleModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<TaxRuleModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<TaxRuleModel>>(){})).call();
    }

    /**
     * Create a new tax rule
     * 
     * Create one or more new taxrule objects attached to this company.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * 
     * @param companyId The ID of the company that owns this tax rule.
     * @param model The tax rule you wish to create.
     * @return ArrayList<TaxRuleModel>
     */
    public Future<ArrayList<TaxRuleModel>> createTaxRulesAsync(int companyId, ArrayList<TaxRuleModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<TaxRuleModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<TaxRuleModel>>(){}));
    }

    /**
     * Delete a single tax rule
     * 
     * @param companyId The ID of the company that owns this tax rule.
     * @param id The ID of the tax rule you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteTaxRule(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single tax rule
     * 
     * @param companyId The ID of the company that owns this tax rule.
     * @param id The ID of the tax rule you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteTaxRuleAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single tax rule
     * 
     * Get the taxrule object identified by this URL.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * 
     * @param companyId The ID of the company that owns this tax rule
     * @param id The primary key of this tax rule
     * @return TaxRuleModel
     */
    public TaxRuleModel getTaxRule(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<TaxRuleModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRuleModel>(){})).call();
    }

    /**
     * Retrieve a single tax rule
     * 
     * Get the taxrule object identified by this URL.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * 
     * @param companyId The ID of the company that owns this tax rule
     * @param id The primary key of this tax rule
     * @return TaxRuleModel
     */
    public Future<TaxRuleModel> getTaxRuleAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<TaxRuleModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TaxRuleModel>(){}));
    }

    /**
     * Retrieve tax rules for this company
     * 
     * List all taxrule objects attached to this company.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these tax rules
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTaxRules(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve tax rules for this company
     * 
     * List all taxrule objects attached to this company.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these tax rules
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTaxRulesAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all tax rules
     * 
     * Get multiple taxrule objects across all companies.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryTaxRules(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrules");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all tax rules
     * 
     * Get multiple taxrule objects across all companies.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryTaxRulesAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/taxrules");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single tax rule
     * 
     * Replace the existing taxrule object at this URL with an updated object.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this tax rule belongs to.
     * @param id The ID of the tax rule you wish to update
     * @param model The tax rule you wish to update.
     * @return TaxRuleModel
     */
    public TaxRuleModel updateTaxRule(int companyId, int id, TaxRuleModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<TaxRuleModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<TaxRuleModel>(){})).call();
    }

    /**
     * Update a single tax rule
     * 
     * Replace the existing taxrule object at this URL with an updated object.
     * A tax rule represents a custom taxability rule for a product or service sold by your company.
     * If you have obtained a custom tax ruling from an auditor that changes the behavior of certain goods or services
     * within certain taxing jurisdictions, or you have obtained special tax concessions for certain dates or locations,
     * you may wish to create a TaxRule object to override the AvaTax engine's default behavior in those circumstances.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this tax rule belongs to.
     * @param id The ID of the tax rule you wish to update
     * @param model The tax rule you wish to update.
     * @return TaxRuleModel
     */
    public Future<TaxRuleModel> updateTaxRuleAsync(int companyId, int id, TaxRuleModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/taxrules/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<TaxRuleModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<TaxRuleModel>(){}));
    }

    /**
     * Add lines to an existing unlocked transaction
     * 
     * Add lines to an existing unlocked transaction.
     * 
     * The `AddLines` API allows you to add additional transaction lines to existing transaction, so that customer will
     * be able to append multiple calls together and form an extremely large transaction. If customer does not specify line number
     * in the lines to be added, a new random Guid string will be generated for line number. If customer are not satisfied with
     * the line number for the transaction lines, they can turn on the renumber switch to have REST v2 automatically renumber all 
     * transaction lines for them, in this case, the line number becomes: "1", "2", "3", ...
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model information about the transaction and lines to be added
     * @return TransactionModel
     */
    public TransactionModel addLines(String include, AddTransactionLineModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/transactions/lines/add");
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Add lines to an existing unlocked transaction
     * 
     * Add lines to an existing unlocked transaction.
     * 
     * The `AddLines` API allows you to add additional transaction lines to existing transaction, so that customer will
     * be able to append multiple calls together and form an extremely large transaction. If customer does not specify line number
     * in the lines to be added, a new random Guid string will be generated for line number. If customer are not satisfied with
     * the line number for the transaction lines, they can turn on the renumber switch to have REST v2 automatically renumber all 
     * transaction lines for them, in this case, the line number becomes: "1", "2", "3", ...
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model information about the transaction and lines to be added
     * @return TransactionModel
     */
    public Future<TransactionModel> addLinesAsync(String include, AddTransactionLineModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/transactions/lines/add");
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Correct a previously created transaction
     * 
     * Replaces the current transaction uniquely identified by this URL with a new transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * When you adjust a committed transaction, the original transaction will be updated with the status code `Adjusted`, and
     * both revisions will be available for retrieval based on their code and ID numbers.
     * Only transactions in `Committed` status are reported by Avalara Managed Returns.
     * 
     * Transactions that have been previously reported to a tax authority by Avalara Managed Returns are considered `locked` and are 
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to adjust
     * @param model The adjustment you wish to make
     * @return TransactionModel
     */
    public TransactionModel adjustTransaction(String companyCode, String transactionCode, AdjustTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/adjust");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Correct a previously created transaction
     * 
     * Replaces the current transaction uniquely identified by this URL with a new transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * When you adjust a committed transaction, the original transaction will be updated with the status code `Adjusted`, and
     * both revisions will be available for retrieval based on their code and ID numbers.
     * Only transactions in `Committed` status are reported by Avalara Managed Returns.
     * 
     * Transactions that have been previously reported to a tax authority by Avalara Managed Returns are considered `locked` and are 
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to adjust
     * @param model The adjustment you wish to make
     * @return TransactionModel
     */
    public Future<TransactionModel> adjustTransactionAsync(String companyCode, String transactionCode, AdjustTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/adjust");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Get audit information about a transaction
     * 
     * Retrieve audit information about a transaction stored in AvaTax.
     *  
     * The 'AuditTransaction' endpoint retrieves audit information related to a specific transaction.  This audit 
     * information includes the following:
     * 
     * * The `CompanyId` of the company that created the transaction
     * * The server timestamp representing the exact server time when the transaction was created
     * * The server duration - how long it took to process this transaction
     * * Whether exact API call details were logged
     * * A reconstructed API call showing what the original CreateTransaction call looked like
     * 
     * This API can be used to examine information about a previously created transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The code identifying the company that owns this transaction
     * @param transactionCode The code identifying the transaction
     * @return AuditTransactionModel
     */
    public AuditTransactionModel auditTransaction(String companyCode, String transactionCode) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/audit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<AuditTransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AuditTransactionModel>(){})).call();
    }

    /**
     * Get audit information about a transaction
     * 
     * Retrieve audit information about a transaction stored in AvaTax.
     *  
     * The 'AuditTransaction' endpoint retrieves audit information related to a specific transaction.  This audit 
     * information includes the following:
     * 
     * * The `CompanyId` of the company that created the transaction
     * * The server timestamp representing the exact server time when the transaction was created
     * * The server duration - how long it took to process this transaction
     * * Whether exact API call details were logged
     * * A reconstructed API call showing what the original CreateTransaction call looked like
     * 
     * This API can be used to examine information about a previously created transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The code identifying the company that owns this transaction
     * @param transactionCode The code identifying the transaction
     * @return AuditTransactionModel
     */
    public Future<AuditTransactionModel> auditTransactionAsync(String companyCode, String transactionCode) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/audit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<AuditTransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AuditTransactionModel>(){}));
    }

    /**
     * Get audit information about a transaction
     * 
     * Retrieve audit information about a transaction stored in AvaTax.
     *  
     * The 'AuditTransaction' endpoint retrieves audit information related to a specific transaction.  This audit 
     * information includes the following:
     * 
     * * The `CompanyId` of the company that created the transaction
     * * The server timestamp representing the exact server time when the transaction was created
     * * The server duration - how long it took to process this transaction
     * * Whether exact API call details were logged
     * * A reconstructed API call showing what the original CreateTransaction call looked like
     * 
     * This API can be used to examine information about a previously created transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The code identifying the company that owns this transaction
     * @param transactionCode The code identifying the transaction
     * @param documentType The document type of the original transaction (See DocumentType::* for a list of allowable values)
     * @return AuditTransactionModel
     */
    public AuditTransactionModel auditTransactionWithType(String companyCode, String transactionCode, DocumentType documentType) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/types/{documentType}/audit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.applyField("documentType", documentType);
        return ((RestCall<AuditTransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AuditTransactionModel>(){})).call();
    }

    /**
     * Get audit information about a transaction
     * 
     * Retrieve audit information about a transaction stored in AvaTax.
     *  
     * The 'AuditTransaction' endpoint retrieves audit information related to a specific transaction.  This audit 
     * information includes the following:
     * 
     * * The `CompanyId` of the company that created the transaction
     * * The server timestamp representing the exact server time when the transaction was created
     * * The server duration - how long it took to process this transaction
     * * Whether exact API call details were logged
     * * A reconstructed API call showing what the original CreateTransaction call looked like
     * 
     * This API can be used to examine information about a previously created transaction.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The code identifying the company that owns this transaction
     * @param transactionCode The code identifying the transaction
     * @param documentType The document type of the original transaction (See DocumentType::* for a list of allowable values)
     * @return AuditTransactionModel
     */
    public Future<AuditTransactionModel> auditTransactionWithTypeAsync(String companyCode, String transactionCode, DocumentType documentType) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/types/{documentType}/audit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.applyField("documentType", documentType);
        return this.threadPool.submit((RestCall<AuditTransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<AuditTransactionModel>(){}));
    }

    /**
     * Lock a set of documents
     * 
     * This API is available by invitation only.
     * 
     * Lock a set of transactions uniquely identified by DocumentIds provided. This API allows locking multiple documents at once.
     * After this API call succeeds, documents will be locked and can't be voided.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param model bulk lock request
     * @return BulkLockTransactionResult
     */
    public BulkLockTransactionResult bulkLockTransaction(BulkLockTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/lock");
        return ((RestCall<BulkLockTransactionResult>)restCallFactory.createRestCall("Post", path, model, new TypeToken<BulkLockTransactionResult>(){})).call();
    }

    /**
     * Lock a set of documents
     * 
     * This API is available by invitation only.
     * 
     * Lock a set of transactions uniquely identified by DocumentIds provided. This API allows locking multiple documents at once.
     * After this API call succeeds, documents will be locked and can't be voided.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param model bulk lock request
     * @return BulkLockTransactionResult
     */
    public Future<BulkLockTransactionResult> bulkLockTransactionAsync(BulkLockTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/lock");
        return this.threadPool.submit((RestCall<BulkLockTransactionResult>)restCallFactory.createRestCall("Post", path, model, new TypeToken<BulkLockTransactionResult>(){}));
    }

    /**
     * Change a transaction's code
     * 
     * Renames a transaction uniquely identified by this URL by changing its code to a new code.
     * After this API call succeeds, the transaction will have a new URL matching its new code.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to change
     * @param model The code change request you wish to execute
     * @return TransactionModel
     */
    public TransactionModel changeTransactionCode(String companyCode, String transactionCode, ChangeTransactionCodeModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/changecode");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Change a transaction's code
     * 
     * Renames a transaction uniquely identified by this URL by changing its code to a new code.
     * After this API call succeeds, the transaction will have a new URL matching its new code.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to change
     * @param model The code change request you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> changeTransactionCodeAsync(String companyCode, String transactionCode, ChangeTransactionCodeModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/changecode");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Commit a transaction for reporting
     * 
     * Marks a transaction by changing its status to 'Committed'.
     * Transactions that are committed are available to be reported to a tax authority by Avalara Managed Returns.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to commit
     * @param model The commit request you wish to execute
     * @return TransactionModel
     */
    public TransactionModel commitTransaction(String companyCode, String transactionCode, CommitTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/commit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Commit a transaction for reporting
     * 
     * Marks a transaction by changing its status to 'Committed'.
     * Transactions that are committed are available to be reported to a tax authority by Avalara Managed Returns.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to commit
     * @param model The commit request you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> commitTransactionAsync(String companyCode, String transactionCode, CommitTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/commit");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Create a new transaction
     * 
     * Records a new transaction or adjust an existing in AvaTax.
     * 
     * The `CreateOrAdjustTransaction` endpoint is used to create a new transaction if the input transaction does not exist
     * or if there exists a transaction identified by code, the original transaction will be adjusted by using the meta data 
     * in the input transaction
     * 
     * If you don't specify type in the provided data, a new transaction with type of SalesOrder will be recorded by default.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model The transaction you wish to create
     * @return TransactionModel
     */
    public TransactionModel createOrAdjustTransaction(String include, CreateOrAdjustTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/createoradjust");
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Create a new transaction
     * 
     * Records a new transaction or adjust an existing in AvaTax.
     * 
     * The `CreateOrAdjustTransaction` endpoint is used to create a new transaction if the input transaction does not exist
     * or if there exists a transaction identified by code, the original transaction will be adjusted by using the meta data 
     * in the input transaction
     * 
     * If you don't specify type in the provided data, a new transaction with type of SalesOrder will be recorded by default.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model The transaction you wish to create
     * @return TransactionModel
     */
    public Future<TransactionModel> createOrAdjustTransactionAsync(String include, CreateOrAdjustTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/createoradjust");
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Create a new transaction
     * 
     * Records a new transaction in AvaTax.
     * 
     * The `CreateTransaction` endpoint uses the configuration values specified by your company to identify the correct tax rules
     * and rates to apply to all line items in this transaction, and reports the total tax calculated by AvaTax based on your
     * company's configuration and the data provided in this API call.
     * 
     * If you don't specify type in the provided data, a new transaction with type of SalesOrder will be recorded by default.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model The transaction you wish to create
     * @return TransactionModel
     */
    public TransactionModel createTransaction(String include, CreateTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/create");
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Create a new transaction
     * 
     * Records a new transaction in AvaTax.
     * 
     * The `CreateTransaction` endpoint uses the configuration values specified by your company to identify the correct tax rules
     * and rates to apply to all line items in this transaction, and reports the total tax calculated by AvaTax based on your
     * company's configuration and the data provided in this API call.
     * 
     * If you don't specify type in the provided data, a new transaction with type of SalesOrder will be recorded by default.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model The transaction you wish to create
     * @return TransactionModel
     */
    public Future<TransactionModel> createTransactionAsync(String include, CreateTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/create");
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Remove lines from an existing unlocked transaction
     * 
     * Remove lines to an existing unlocked transaction.
     * 
     * The `DeleteLines` API allows you to remove transaction lines from existing unlocked transaction, so that customer will
     * be able to delete transaction lines and adjust original transaction the way they like
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model information about the transaction and lines to be removed
     * @return TransactionModel
     */
    public TransactionModel deleteLines(String include, RemoveTransactionLineModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/transactions/lines/delete");
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Remove lines from an existing unlocked transaction
     * 
     * Remove lines to an existing unlocked transaction.
     * 
     * The `DeleteLines` API allows you to remove transaction lines from existing unlocked transaction, so that customer will
     * be able to delete transaction lines and adjust original transaction the way they like
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model information about the transaction and lines to be removed
     * @return TransactionModel
     */
    public Future<TransactionModel> deleteLinesAsync(String include, RemoveTransactionLineModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/transactions/lines/delete");
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Retrieve a single transaction by code
     * 
     * Get the current transaction identified by this URL.
     * If this transaction was adjusted, the return value of this API will be the current transaction with this code, and previous revisions of
     * the transaction will be attached to the 'history' data field.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public TransactionModel getTransactionByCode(String companyCode, String transactionCode, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Retrieve a single transaction by code
     * 
     * Get the current transaction identified by this URL.
     * If this transaction was adjusted, the return value of this API will be the current transaction with this code, and previous revisions of
     * the transaction will be attached to the 'history' data field.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public Future<TransactionModel> getTransactionByCodeAsync(String companyCode, String transactionCode, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Retrieve a single transaction by code
     * 
     * Get the current transaction identified by this URL.
     * If this transaction was adjusted, the return value of this API will be the current transaction with this code, and previous revisions of
     * the transaction will be attached to the 'history' data field.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to retrieve
     * @param documentType The transaction type to retrieve (See DocumentType::* for a list of allowable values)
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public TransactionModel getTransactionByCodeAndType(String companyCode, String transactionCode, DocumentType documentType, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/types/{documentType}");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.applyField("documentType", documentType);
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Retrieve a single transaction by code
     * 
     * Get the current transaction identified by this URL.
     * If this transaction was adjusted, the return value of this API will be the current transaction with this code, and previous revisions of
     * the transaction will be attached to the 'history' data field.
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to retrieve
     * @param documentType The transaction type to retrieve (See DocumentType::* for a list of allowable values)
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public Future<TransactionModel> getTransactionByCodeAndTypeAsync(String companyCode, String transactionCode, DocumentType documentType, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/types/{documentType}");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.applyField("documentType", documentType);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Retrieve a single transaction by ID
     * 
     * Get the unique transaction identified by this URL.
     * This endpoint retrieves the exact transaction identified by this ID number even if that transaction was later adjusted
     * by using the 'Adjust Transaction' endpoint.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param id The unique ID number of the transaction to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public TransactionModel getTransactionById(long id, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Retrieve a single transaction by ID
     * 
     * Get the unique transaction identified by this URL.
     * This endpoint retrieves the exact transaction identified by this ID number even if that transaction was later adjusted
     * by using the 'Adjust Transaction' endpoint.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param id The unique ID number of the transaction to retrieve
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return TransactionModel
     */
    public Future<TransactionModel> getTransactionByIdAsync(long id, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/transactions/{id}");
        path.applyField("id", id);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Retrieve all transactions
     * 
     * List all transactions attached to this company.
     * This endpoint is limited to returning 1,000 transactions at a time maximum.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listTransactionsByCompany(String companyCode, String include, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions");
        path.applyField("companyCode", companyCode);
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all transactions
     * 
     * List all transactions attached to this company.
     * This endpoint is limited to returning 1,000 transactions at a time maximum.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * Paginate your results using the `$top`, `$skip`, and `$orderby` parameters.
     * You may specify one or more of the following values in the `$include` parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listTransactionsByCompanyAsync(String companyCode, String include, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions");
        path.applyField("companyCode", companyCode);
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Lock a single transaction
     * 
     * Lock a transaction uniquely identified by this URL. 
     * 
     * This API is mainly used for connector developer to simulate what happens when Returns product locks a document.
     * After this API call succeeds, the document will be locked and can't be voided or adjusted.
     * 
     * This API is only available to customers in Sandbox with AvaTaxPro subscription.  On production servers, this API is available by invitation only.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to lock
     * @param model The lock request you wish to execute
     * @return TransactionModel
     */
    public TransactionModel lockTransaction(String companyCode, String transactionCode, LockTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/lock");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Lock a single transaction
     * 
     * Lock a transaction uniquely identified by this URL. 
     * 
     * This API is mainly used for connector developer to simulate what happens when Returns product locks a document.
     * After this API call succeeds, the document will be locked and can't be voided or adjusted.
     * 
     * This API is only available to customers in Sandbox with AvaTaxPro subscription.  On production servers, this API is available by invitation only.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to lock
     * @param model The lock request you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> lockTransactionAsync(String companyCode, String transactionCode, LockTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/lock");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Create a refund for a transaction
     * 
     * Create a refund for a transaction.
     * 
     * The `RefundTransaction` API allows you to quickly and easily create a `ReturnInvoice` representing a refund
     * for a previously created `SalesInvoice` transaction.  You can choose to create a full or partial refund, and
     * specify individual line items from the original sale for refund.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param companyCode The code of the company that made the original sale
     * @param transactionCode The transaction code of the original sale
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model Information about the refund to create
     * @return TransactionModel
     */
    public TransactionModel refundTransaction(String companyCode, String transactionCode, String include, RefundTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/refund");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.addQuery("$include", include);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Create a refund for a transaction
     * 
     * Create a refund for a transaction.
     * 
     * The `RefundTransaction` API allows you to quickly and easily create a `ReturnInvoice` representing a refund
     * for a previously created `SalesInvoice` transaction.  You can choose to create a full or partial refund, and
     * specify individual line items from the original sale for refund.
     * 
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * You may specify one or more of the following values in the '$include' parameter to fetch additional nested data, using commas to separate multiple values:
     *             
     * * Lines
     * * Details (implies lines)
     * * Summary (implies details)
     * * Addresses
     *             
     * 
     * @param companyCode The code of the company that made the original sale
     * @param transactionCode The transaction code of the original sale
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param model Information about the refund to create
     * @return TransactionModel
     */
    public Future<TransactionModel> refundTransactionAsync(String companyCode, String transactionCode, String include, RefundTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/refund");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Perform multiple actions on a transaction
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to settle
     * @param model The settle request containing the actions you wish to execute
     * @return TransactionModel
     */
    public TransactionModel settleTransaction(String companyCode, String transactionCode, SettleTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/settle");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Perform multiple actions on a transaction
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to settle
     * @param model The settle request containing the actions you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> settleTransactionAsync(String companyCode, String transactionCode, SettleTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/settle");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Verify a transaction
     * 
     * Verifies that the transaction uniquely identified by this URL matches certain expected values.
     * If the transaction does not match these expected values, this API will return an error code indicating which value did not match.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to settle
     * @param model The settle request you wish to execute
     * @return TransactionModel
     */
    public TransactionModel verifyTransaction(String companyCode, String transactionCode, VerifyTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/verify");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Verify a transaction
     * 
     * Verifies that the transaction uniquely identified by this URL matches certain expected values.
     * If the transaction does not match these expected values, this API will return an error code indicating which value did not match.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to settle
     * @param model The settle request you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> verifyTransactionAsync(String companyCode, String transactionCode, VerifyTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/verify");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Void a transaction
     * 
     * Voids the current transaction uniquely identified by this URL.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * When you void a transaction, that transaction's status is recorded as 'DocVoided'.
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to void
     * @param model The void request you wish to execute
     * @return TransactionModel
     */
    public TransactionModel voidTransaction(String companyCode, String transactionCode, VoidTransactionModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/void");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return ((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){})).call();
    }

    /**
     * Void a transaction
     * 
     * Voids the current transaction uniquely identified by this URL.
     * A transaction represents a unique potentially taxable action that your company has recorded, and transactions include actions like
     * sales, purchases, inventory transfer, and returns (also called refunds).
     * When you void a transaction, that transaction's status is recorded as 'DocVoided'.
     * 
     * @param companyCode The company code of the company that recorded this transaction
     * @param transactionCode The transaction code to void
     * @param model The void request you wish to execute
     * @return TransactionModel
     */
    public Future<TransactionModel> voidTransactionAsync(String companyCode, String transactionCode, VoidTransactionModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyCode}/transactions/{transactionCode}/void");
        path.applyField("companyCode", companyCode);
        path.applyField("transactionCode", transactionCode);
        return this.threadPool.submit((RestCall<TransactionModel>)restCallFactory.createRestCall("Post", path, model, new TypeToken<TransactionModel>(){}));
    }

    /**
     * Create a new UPC
     * 
     * Create one or more new UPC objects attached to this company.
     * 
     * @param companyId The ID of the company that owns this UPC.
     * @param model The UPC you wish to create.
     * @return ArrayList<UPCModel>
     */
    public ArrayList<UPCModel> createUPCs(int companyId, ArrayList<UPCModel> model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs");
        path.applyField("companyId", companyId);
        return ((RestCall<ArrayList<UPCModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<UPCModel>>(){})).call();
    }

    /**
     * Create a new UPC
     * 
     * Create one or more new UPC objects attached to this company.
     * 
     * @param companyId The ID of the company that owns this UPC.
     * @param model The UPC you wish to create.
     * @return ArrayList<UPCModel>
     */
    public Future<ArrayList<UPCModel>> createUPCsAsync(int companyId, ArrayList<UPCModel> model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs");
        path.applyField("companyId", companyId);
        return this.threadPool.submit((RestCall<ArrayList<UPCModel>>)restCallFactory.createRestCall("Post", path, model, new TypeToken<ArrayList<UPCModel>>(){}));
    }

    /**
     * Delete a single UPC
     * 
     * @param companyId The ID of the company that owns this UPC.
     * @param id The ID of the UPC you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public ArrayList<ErrorDetail> deleteUPC(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){})).call();
    }

    /**
     * Delete a single UPC
     * 
     * @param companyId The ID of the company that owns this UPC.
     * @param id The ID of the UPC you wish to delete.
     * @return ArrayList<ErrorDetail>
     */
    public Future<ArrayList<ErrorDetail>> deleteUPCAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<ArrayList<ErrorDetail>>)restCallFactory.createRestCall("Delete", path, null, new TypeToken<ArrayList<ErrorDetail>>(){}));
    }

    /**
     * Retrieve a single UPC
     * 
     * Get the UPC object identified by this URL.
     * 
     * @param companyId The ID of the company that owns this UPC
     * @param id The primary key of this UPC
     * @return UPCModel
     */
    public UPCModel getUPC(int companyId, int id) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<UPCModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UPCModel>(){})).call();
    }

    /**
     * Retrieve a single UPC
     * 
     * Get the UPC object identified by this URL.
     * 
     * @param companyId The ID of the company that owns this UPC
     * @param id The primary key of this UPC
     * @return UPCModel
     */
    public Future<UPCModel> getUPCAsync(int companyId, int id) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<UPCModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UPCModel>(){}));
    }

    /**
     * Retrieve UPCs for this company
     * 
     * List all UPC objects attached to this company.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these UPCs
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listUPCsByCompany(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve UPCs for this company
     * 
     * List all UPC objects attached to this company.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param companyId The ID of the company that owns these UPCs
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listUPCsByCompanyAsync(int companyId, String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs");
        path.applyField("companyId", companyId);
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all UPCs
     * 
     * Get multiple UPC objects across all companies.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryUPCs(String filter, String include, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/upcs");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all UPCs
     * 
     * Get multiple UPC objects across all companies.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryUPCsAsync(String filter, String include, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/upcs");
        path.addQuery("$filter", filter);
        path.addQuery("$include", include);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single UPC
     * 
     * Replace the existing UPC object at this URL with an updated object.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this UPC belongs to.
     * @param id The ID of the UPC you wish to update
     * @param model The UPC you wish to update.
     * @return UPCModel
     */
    public UPCModel updateUPC(int companyId, int id, UPCModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return ((RestCall<UPCModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<UPCModel>(){})).call();
    }

    /**
     * Update a single UPC
     * 
     * Replace the existing UPC object at this URL with an updated object.
     * A UPC represents a single UPC code in your catalog and matches this product to the tax code identified by this UPC.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param companyId The ID of the company that this UPC belongs to.
     * @param id The ID of the UPC you wish to update
     * @param model The UPC you wish to update.
     * @return UPCModel
     */
    public Future<UPCModel> updateUPCAsync(int companyId, int id, UPCModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/companies/{companyId}/upcs/{id}");
        path.applyField("companyId", companyId);
        path.applyField("id", id);
        return this.threadPool.submit((RestCall<UPCModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<UPCModel>(){}));
    }

    /**
     * Retrieve a single user
     * 
     * Get the user object identified by this URL.
     * 
     * @param id The ID of the user to retrieve.
     * @param accountId The accountID of the user you wish to get.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return UserModel
     */
    public UserModel getUser(int id, int accountId, String include) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        path.addQuery("$include", include);
        return ((RestCall<UserModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UserModel>(){})).call();
    }

    /**
     * Retrieve a single user
     * 
     * Get the user object identified by this URL.
     * 
     * @param id The ID of the user to retrieve.
     * @param accountId The accountID of the user you wish to get.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @return UserModel
     */
    public Future<UserModel> getUserAsync(int id, int accountId, String include) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        path.addQuery("$include", include);
        return this.threadPool.submit((RestCall<UserModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UserModel>(){}));
    }

    /**
     * Retrieve all entitlements for a single user
     * 
     * Return a list of all entitlements to which this user has rights to access.
     * Entitlements are a list of specified API calls the user is permitted to make, a list of identifier numbers for companies the user is 
     * allowed to use, and an access level identifier that indicates what types of access roles the user is allowed to use.
     * This API call is intended to provide a validation endpoint to determine, before making an API call, whether this call is likely to succeed.
     * For example, if user 567 within account 999 is attempting to create a new child company underneath company 12345, you could preview the user's
     * entitlements and predict whether this call would succeed:
     *             
     * * Retrieve entitlements by calling '/api/v2/accounts/999/users/567/entitlements' .  If the call fails, you do not have accurate 
     *     credentials for this user.
     * * If the 'accessLevel' field within entitlements is 'None', the call will fail.
     * * If the 'accessLevel' field within entitlements is 'SingleCompany' or 'SingleAccount', the call will fail if the companies
     *     table does not contain the ID number 12345.
     * * If the 'permissions' array within entitlements does not contain 'AccountSvc.CompanySave', the call will fail.
     *             
     * 
     * @param id The ID of the user to retrieve.
     * @param accountId The accountID of the user you wish to get.
     * @return UserEntitlementModel
     */
    public UserEntitlementModel getUserEntitlements(int id, int accountId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}/entitlements");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return ((RestCall<UserEntitlementModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UserEntitlementModel>(){})).call();
    }

    /**
     * Retrieve all entitlements for a single user
     * 
     * Return a list of all entitlements to which this user has rights to access.
     * Entitlements are a list of specified API calls the user is permitted to make, a list of identifier numbers for companies the user is 
     * allowed to use, and an access level identifier that indicates what types of access roles the user is allowed to use.
     * This API call is intended to provide a validation endpoint to determine, before making an API call, whether this call is likely to succeed.
     * For example, if user 567 within account 999 is attempting to create a new child company underneath company 12345, you could preview the user's
     * entitlements and predict whether this call would succeed:
     *             
     * * Retrieve entitlements by calling '/api/v2/accounts/999/users/567/entitlements' .  If the call fails, you do not have accurate 
     *     credentials for this user.
     * * If the 'accessLevel' field within entitlements is 'None', the call will fail.
     * * If the 'accessLevel' field within entitlements is 'SingleCompany' or 'SingleAccount', the call will fail if the companies
     *     table does not contain the ID number 12345.
     * * If the 'permissions' array within entitlements does not contain 'AccountSvc.CompanySave', the call will fail.
     *             
     * 
     * @param id The ID of the user to retrieve.
     * @param accountId The accountID of the user you wish to get.
     * @return UserEntitlementModel
     */
    public Future<UserEntitlementModel> getUserEntitlementsAsync(int id, int accountId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}/entitlements");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<UserEntitlementModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<UserEntitlementModel>(){}));
    }

    /**
     * Retrieve users for this account
     * 
     * List all user objects attached to this account.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The accountID of the user you wish to list.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listUsersByAccount(int accountId, String include, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users");
        path.applyField("accountId", accountId);
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve users for this account
     * 
     * List all user objects attached to this account.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param accountId The accountID of the user you wish to list.
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listUsersByAccountAsync(int accountId, String include, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users");
        path.applyField("accountId", accountId);
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Retrieve all users
     * 
     * Get multiple user objects across all accounts.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> queryUsers(String include, String filter, Integer top, Integer skip, String orderBy) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/users");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * Retrieve all users
     * 
     * Get multiple user objects across all accounts.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * 
     * Search for specific objects using the criteria in the `$filter` parameter; full documentation is available on [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * 
     * @param include A comma separated list of child objects to return underneath the primary object.
     * @param filter A filter statement to identify specific records to retrieve. For more information on filtering, see [Filtering in REST](http://developer.avalara.com/avatax/filtering-in-rest/) .
     * @param top If nonzero, return no more than this number of results. Used with $skip to provide pagination for large datasets.
     * @param skip If nonzero, skip this number of results before returning data. Used with $top to provide pagination for large datasets.
     * @param orderBy A comma separated list of sort statements in the format `(fieldname) [ASC|DESC]`, for example `id ASC`.
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> queryUsersAsync(String include, String filter, Integer top, Integer skip, String orderBy) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/users");
        path.addQuery("$include", include);
        path.addQuery("$filter", filter);
        path.addQuery("$top", top);
        path.addQuery("$skip", skip);
        path.addQuery("$orderBy", orderBy);
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Update a single user
     * 
     * Replace the existing user object at this URL with an updated object.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param id The ID of the user you wish to update.
     * @param accountId The accountID of the user you wish to update.
     * @param model The user object you wish to update.
     * @return UserModel
     */
    public UserModel updateUser(int id, int accountId, UserModel model) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return ((RestCall<UserModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<UserModel>(){})).call();
    }

    /**
     * Update a single user
     * 
     * Replace the existing user object at this URL with an updated object.
     * A user represents one person with access privileges to make API calls and work with a specific account.
     * All data from the existing object will be replaced with data in the object you PUT.  
     * 
     * @param id The ID of the user you wish to update.
     * @param accountId The accountID of the user you wish to update.
     * @param model The user object you wish to update.
     * @return UserModel
     */
    public Future<UserModel> updateUserAsync(int id, int accountId, UserModel model) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/accounts/{accountId}/users/{id}");
        path.applyField("id", id);
        path.applyField("accountId", accountId);
        return this.threadPool.submit((RestCall<UserModel>)restCallFactory.createRestCall("Put", path, model, new TypeToken<UserModel>(){}));
    }

    /**
     * Checks if the current user is subscribed to a specific service
     * 
     * Returns a subscription object for the current account, or 404 Not Found if this subscription is not enabled for this account.
     * This API call is intended to allow you to identify whether you have the necessary account configuration to access certain
     * 
     * @param serviceTypeId The service to check (See ServiceTypeId::* for a list of allowable values)
     * @return SubscriptionModel
     */
    public SubscriptionModel getMySubscription(ServiceTypeId serviceTypeId) throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/subscriptions/{serviceTypeId}");
        path.applyField("serviceTypeId", serviceTypeId);
        return ((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SubscriptionModel>(){})).call();
    }

    /**
     * Checks if the current user is subscribed to a specific service
     * 
     * Returns a subscription object for the current account, or 404 Not Found if this subscription is not enabled for this account.
     * This API call is intended to allow you to identify whether you have the necessary account configuration to access certain
     * 
     * @param serviceTypeId The service to check (See ServiceTypeId::* for a list of allowable values)
     * @return SubscriptionModel
     */
    public Future<SubscriptionModel> getMySubscriptionAsync(ServiceTypeId serviceTypeId) {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/subscriptions/{serviceTypeId}");
        path.applyField("serviceTypeId", serviceTypeId);
        return this.threadPool.submit((RestCall<SubscriptionModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<SubscriptionModel>(){}));
    }

    /**
     * List all services to which the current user is subscribed
     * 
     * Returns the list of all subscriptions enabled for the current account.
     * This API is intended to help you determine whether you have the necessary subscription to use certain API calls
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public FetchResult<HashMap<String, String>> listMySubscriptions() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/subscriptions");
        return ((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){})).call();
    }

    /**
     * List all services to which the current user is subscribed
     * 
     * Returns the list of all subscriptions enabled for the current account.
     * This API is intended to help you determine whether you have the necessary subscription to use certain API calls
     * 
     * @return FetchResult<HashMap<String, String>>
     */
    public Future<FetchResult<HashMap<String, String>>> listMySubscriptionsAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/subscriptions");
        return this.threadPool.submit((RestCall<FetchResult<HashMap<String, String>>>)restCallFactory.createRestCall("Get", path, null, new TypeToken<FetchResult<HashMap<String, String>>>(){}));
    }

    /**
     * Tests connectivity and version of the service
     * 
     * This API helps diagnose connectivity problems between your application and AvaTax; you may call this API even 
     * if you do not have verified connection credentials.
     * The results of this API call will help you determine whether your computer can contact AvaTax via the network,
     * whether your authentication credentials are recognized, and the roundtrip time it takes to communicate with
     * 
     * @return PingResultModel
     */
    public PingResultModel ping() throws Exception {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/ping");
        return ((RestCall<PingResultModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<PingResultModel>(){})).call();
    }

    /**
     * Tests connectivity and version of the service
     * 
     * This API helps diagnose connectivity problems between your application and AvaTax; you may call this API even 
     * if you do not have verified connection credentials.
     * The results of this API call will help you determine whether your computer can contact AvaTax via the network,
     * whether your authentication credentials are recognized, and the roundtrip time it takes to communicate with
     * 
     * @return PingResultModel
     */
    public Future<PingResultModel> pingAsync() {
        AvaTaxPath path = new AvaTaxPath("/api/v2/utilities/ping");
        return this.threadPool.submit((RestCall<PingResultModel>)restCallFactory.createRestCall("Get", path, null, new TypeToken<PingResultModel>(){}));
    }

//endregion

}
    