package com.byteWorks.servByte.payStackPayment.dtos;

import com.byteWorks.servByte.payStackPayment.entities.AccessData;
import lombok.Data;

@Data
public class InitializeTransactionResponseDTO   {
    private String status;
    private String message;
    private AccessData data;
}
