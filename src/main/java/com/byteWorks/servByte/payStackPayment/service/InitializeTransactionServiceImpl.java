package com.byteWorks.servByte.payStackPayment.service;

import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionRequestDTO;
import com.byteWorks.servByte.payStackPayment.dtos.InitializeTransactionResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InitializeTransactionServiceImpl implements InitializeTransactionService{
    RestTemplate restTemplate = new RestTemplate();

    /**
     * method used to send request to make payment
     * @param initializeTransactionRequestDTO are details used to send request
     * @return an initializeTransactionDto
     */
    @Override
    public InitializeTransactionResponseDTO initializeTransaction (InitializeTransactionRequestDTO initializeTransactionRequestDTO) {
        String url = "https://api.paystack.co/transaction/initialize";
        HttpHeaders headers = new HttpHeaders();
        String key = "sk_test_b2ffbedf2d7b2cf10229235415ba2dac551c684e";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + key);
        HttpEntity<InitializeTransactionRequestDTO> entity = new HttpEntity<InitializeTransactionRequestDTO> (initializeTransactionRequestDTO, headers);

        ResponseEntity<InitializeTransactionResponseDTO> response = restTemplate.postForEntity(url, entity, InitializeTransactionResponseDTO.class);
        return response.getBody();
    }
}
