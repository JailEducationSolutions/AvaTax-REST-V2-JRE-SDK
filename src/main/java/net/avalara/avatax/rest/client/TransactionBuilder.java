package net.avalara.avatax.rest.client;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Future;

public class TransactionBuilder {
    private CreateTransactionModel model;
    private int lineNumber;
    private AvaTaxClient client;

    public TransactionBuilder(AvaTaxClient client, String companyCode, DocumentType docType, String customerCode) {
        this.model = new CreateTransactionModel();
        model.setCompanyCode(companyCode);
        model.setCustomerCode(customerCode);
        model.setDate(new Date());
        model.setType(docType);
        model.setLines(new ArrayList<LineItemModel>());
        this.lineNumber = 1;
        this.client = client;
    }

    public TransactionBuilder withCommit() {
        this.model.setCommit(true);
        return this;
    }

    public TransactionBuilder withDiagnostics() {
        this.model.setDebugLevel(TaxDebugLevel.Diagnostic);
        return this;
    }

    public TransactionBuilder withDiscountAmount(BigDecimal discountAmount) {
        this.model.setDiscount(discountAmount);
        return this;
    }

    public TransactionBuilder withItemDiscount(Boolean discounted) {
        getMostRecentLine("WithItemDiscount").setDiscounted(discounted);
        return this;
    }

    public TransactionBuilder withTransactionCode(String code) {
        this.model.setCode(code);
        return this;
    }

    public TransactionBuilder withType(DocumentType type) {
        this.model.setType(type);
        return this;
    }

    public TransactionBuilder withParameter(String name, String value) {
        HashMap<String, String> params = this.model.getParameters();

        if (params == null) {
            params = new HashMap<>();
            this.model.setParameters(params);
        }

        params.put(name, value);
        return this;
    }

    public TransactionBuilder withLineParameter(String name, String value) {
        LineItemModel line = getMostRecentLine("WithLineParameter");
        HashMap<String, String> params = line.getParameters();

        if (params == null) {
            params = new HashMap<>();
            line.setParameters(params);
        }

        params.put(name, value);
        return this;
    }

    public TransactionBuilder withDate(Date date) {
        model.setDate(date);
        return this;
    }

    public TransactionBuilder withCustomerUsageType(String customerUsageType) {
        model.setCustomerUsageType(customerUsageType);
        return this;
    }

    public TransactionBuilder withExemptionNo(String exemptionNo) {
        model.setExemptionNo(exemptionNo);
        return this;
    }

    public TransactionBuilder withCode(String code) {
        model.setCode(code);
        return this;
    }

    public TransactionBuilder withAddress(TransactionAddressType type, String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        HashMap<TransactionAddressType, AddressInfo> addresses = this.model.getAddresses();

        if (addresses == null) {
            addresses = new HashMap<>();
            this.model.setAddresses(addresses);
        }

        AddressInfo info = new AddressInfo();
        info.setLine1(line1);
        info.setLine2(line2);
        info.setLine3(line3);
        info.setCity(city);
        info.setRegion(region);
        info.setPostalCode(postalCode);
        info.setCountry(country);

        addresses.put(type, info);
        return this;
    }

    public TransactionBuilder withLatLong(TransactionAddressType type, BigDecimal latitude, BigDecimal longitude) {
        HashMap<TransactionAddressType, AddressInfo> addresses = this.model.getAddresses();

        if (addresses == null) {
            addresses = new HashMap<>();
            this.model.setAddresses(addresses);
        }

        AddressInfo info = new AddressInfo();
        info.setLatitude(latitude);
        info.setLongitude(longitude);

        addresses.put(type, info);
        return this;
    }

    public TransactionBuilder withLineAddress(TransactionAddressType type, String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        LineItemModel line = getMostRecentLine("WithLineAddress");
        HashMap<TransactionAddressType, AddressInfo> addresses = line.getAddresses();

        if (addresses == null) {
            addresses = new HashMap<>();
            line.setAddresses(addresses);
        }

        AddressInfo info = new AddressInfo();
        info.setLine1(line1);
        info.setLine2(line2);
        info.setLine3(line3);
        info.setCity(city);
        info.setRegion(region);
        info.setPostalCode(postalCode);
        info.setCountry(country);

        addresses.put(type, info);
        return this;
    }

    public TransactionBuilder withTaxOverride(TaxOverrideType type, String reason, BigDecimal taxAmount, Date taxDate) {
        if (taxAmount == null) {
            taxAmount = new BigDecimal(0);
        }

        TaxOverrideModel override = new TaxOverrideModel();
        override.setType(type);
        override.setReason(reason);
        override.setTaxAmount(taxAmount);
        override.setTaxDate(taxDate);

        this.model.setTaxOverride(override);
        return this;
    }

    public TransactionBuilder withLineTaxOverride(TaxOverrideType type, String reason, BigDecimal taxAmount, Date taxDate) throws AvaTaxClientException {
        if (type == TaxOverrideType.TaxDate && taxDate == null) {
            throw new AvaTaxClientException("TaxDate cannot be null with a TaxDate TaxOverrideType.");
        }

        LineItemModel line = getMostRecentLine("WithLineAddress");

        TaxOverrideModel override = new TaxOverrideModel();
        override.setType(type);
        override.setReason(reason);
        override.setTaxAmount(taxAmount);
        override.setTaxDate(taxDate);

        line.setTaxOverride(override);
        return this;
    }

    public TransactionBuilder withLine(BigDecimal amount, BigDecimal quantity, String taxCode) {
        if (quantity == null) {
            quantity = BigDecimal.ONE;
        }

        LineItemModel line = new LineItemModel();
        line.setAmount(amount);
        line.setQuantity(quantity);
        line.setNumber(((Integer)this.lineNumber).toString());
        line.setTaxCode(taxCode);

        this.model.getLines().add(line);
        this.lineNumber++;
        return this;
    }

    public TransactionBuilder withSeparateAddressLine(BigDecimal amount, TransactionAddressType type, String line1, String line2, String line3, String city, String region, String postalCode, String country) {
        LineItemModel line = new LineItemModel();
        line.setAmount(amount);
        line.setQuantity(BigDecimal.ONE);
        line.setNumber(((Integer)this.lineNumber).toString());

        HashMap<TransactionAddressType, AddressInfo> addresses = new HashMap<>();
        AddressInfo info = new AddressInfo();
        info.setLine1(line1);
        info.setLine2(line2);
        info.setLine3(line3);
        info.setCity(city);
        info.setRegion(region);
        info.setPostalCode(postalCode);
        info.setCountry(country);

        addresses.put(type, info);
        line.setAddresses(addresses);

        this.model.getLines().add(line);
        this.lineNumber++;
        return this;
    }

    public TransactionBuilder withExemptLine(BigDecimal amount, String exemptionCode) {
        LineItemModel line = new LineItemModel();
        line.setAmount(amount);
        line.setQuantity(BigDecimal.ONE);
        line.setNumber(((Integer)this.lineNumber).toString());
        line.setExemptionCode(exemptionCode);

        this.model.getLines().add(line);
        this.lineNumber++;
        return this;
    }

    private LineItemModel getMostRecentLine(String methodName) {
        if (this.model.getLines().size() <= 0) {
            throw new IllegalStateException("This transaction does not have any lines.");
        }

        return this.model.getLines().get(this.model.getLines().size() - 1);
    }

    public CreateTransactionModel getIntermediaryTransactionModel() {
        return this.model;
    }

    public Future<TransactionModel> CreateAsync() {
        return this.client.createTransactionAsync(this.model);
    }

    public TransactionModel Create() throws Exception {
        return this.client.createTransaction(this.model);
    }

    public AdjustTransactionModel CreateAdjustmentRequest(String description, AdjustmentReason reason) {
        AdjustTransactionModel adjust = new AdjustTransactionModel();
        adjust.setAdjustmentDescription(description);
        adjust.setAdjustmentReason(reason);
        adjust.setNewTransaction(this.model);
        return adjust;
    }
}
