package com.byteWorks.servByte.payStackPayment.controller;

import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionRequestDTO;
import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionResponseDTO;
import com.byteWorks.servByte.payStackPayment.service.InitializeTransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
public class InitializeTransactionController {
    @Autowired
    InitializeTransactionService initializeTransactionService;

    /**
     * Endpoint used to send request to make payment
     * @param initializeTransactionRequestDTO are details used to send request
     * @return an initializeTransactionDto
     */
    @PostMapping(path = "/initializetransaction")
    public InitializeTransactionResponseDTO initializeTransaction(@RequestBody InitializeTransactionRequestDTO initializeTransactionRequestDTO) {
        InitializeTransactionResponseDTO  initializeTransaction = initializeTransactionService.initializeTransaction(initializeTransactionRequestDTO);
        return initializeTransaction;
    }

}
