package com.byteWorks.servByte.payStackPayment.entities;

import lombok.Data;

@Data
public class AccessData {

    private String authorization_url;
    private String access_code;
    private String reference;
}
