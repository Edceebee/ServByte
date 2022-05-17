package com.byteWorks.servByte.payStackPayment.dtos;


import com.byteWorks.servByte.payStackPayment.enums.Channels;
import com.byteWorks.servByte.payStackPayment.enums.PaystackBearer;
import lombok.Data;

@Data
public class InitializeTransactionRequestDTO {
    private String amount;
    private String email;
    private String reference;
    private String callback_url;
    private Integer invoice_limit;
    private Channels[] channels;
    private String subaccount;
    private Integer transaction_charge;
    // @Enumerated(EnumType.STRING)
    private PaystackBearer paystackBearer = PaystackBearer.ACCOUNT;

}
