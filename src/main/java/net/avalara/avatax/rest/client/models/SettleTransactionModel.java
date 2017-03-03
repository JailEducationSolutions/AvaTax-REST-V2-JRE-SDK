package net.avalara.avatax.rest.client.models;

import net.avalara.avatax.rest.client.enums.*;
import net.avalara.avatax.rest.client.serializer.JsonSerializer;

import java.lang.Override;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
 */
public class SettleTransactionModel {
    private VerifyTransactionModel verify;

    /**
     * Getter for verify - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public VerifyTransactionModel getVerify() {
        return this.verify;
    }

    /**
     * Setter for verify - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public void setVerify(VerifyTransactionModel verify) {
        this.verify = verify;
    }

    
    private ChangeTransactionCodeModel changeCode;

    /**
     * Getter for changeCode - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public ChangeTransactionCodeModel getChangeCode() {
        return this.changeCode;
    }

    /**
     * Setter for changeCode - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public void setChangeCode(ChangeTransactionCodeModel changeCode) {
        this.changeCode = changeCode;
    }

    
    private CommitTransactionModel commit;

    /**
     * Getter for commit - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public CommitTransactionModel getCommit() {
        return this.commit;
    }

    /**
     * Setter for commit - Settle this transaction with your ledger by executing one or many actions against that transaction.  
You may use this endpoint to verify the transaction, change the transaction's code, and commit the transaction for reporting purposes.
This endpoint may be used to execute any or all of these actions at once.
     */
    public void setCommit(CommitTransactionModel commit) {
        this.commit = commit;
    }

    


    /**
     * Returns a JSON string representation of SettleTransactionModel.
     */
    @Override
    public String toString() {
        return JsonSerializer.SerializeObject(this);
    }
}
    