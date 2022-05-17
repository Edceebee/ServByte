package com.byteWorks.servByte.payStackPayment.service;

import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionRequestDTO;
import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionResponseDTO;

public interface InitializeTransactionService {
    InitializeTransactionResponseDTO initializeTransaction (InitializeTransactionRequestDTO initializeTransactionRequestDTO
    );
}
