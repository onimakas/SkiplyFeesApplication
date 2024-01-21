package com.skiply.fees_collection.services;

import com.skiply.fees_collection.entities.PaymentMode;
import com.skiply.fees_collection.clients.StudentServiceClient;
import com.skiply.fees_collection.dtos.FeesPaymentCreationDto;
import com.skiply.fees_collection.dtos.StudentDto;
import com.skiply.fees_collection.dtos.TransactionCreationDto;
import com.skiply.fees_collection.entities.Fees;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionValidationServiceImpl implements TransactionValidationService {
    private final SchoolService schoolService;
    private final CardService cardService;
    private final FeesService feesService;
    private final StudentServiceClient studentServiceClient;

    // 1. Total amount of transaction should be equal to summation of individual fees payment multiplied by their quantity.
    // 2. Check if the currency code of transaction and fees is same or not.
    // 3. Check if the student is in the same grade or not as the transaction
    // 4. Check if the fees is expired or not.
    // 5.
    @Override
    public void validateTransactionCreationDto(TransactionCreationDto transactionDto) {
        // Uncomment once data is seeded.
//        validateSchoolExists(transactionDto);
//        validatePaymentMode(transactionDto);
//        validateFeesPayments(transactionDto);
        validateStudentDetails(transactionDto);
    }

    private void validateSchoolExists(TransactionCreationDto transactionDto) {
        schoolService.getSchoolById(transactionDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid School ID"));
    }

    private void validatePaymentMode(TransactionCreationDto transactionDto) {
        if (transactionDto.getPaymentMode() == PaymentMode.CREDIT_CARD) {
            cardService.getCardById(transactionDto.getPaymentModeId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Card ID"));
        }
    }

    private void validateFeesPayments(TransactionCreationDto transactionDto) {
        double totalFeesAmount = 0;
        for (FeesPaymentCreationDto feesDto : transactionDto.getFeesPayments()) {
            Fees fees = validateIndividualFees(feesDto, transactionDto);
            totalFeesAmount += feesDto.getAmount() * feesDto.getQuantity();
        }

        if (totalFeesAmount != transactionDto.getAmount()) {
            throw new IllegalArgumentException("Total amount does not match the sum of individual fees payments");
        }
    }

    private Fees validateIndividualFees(FeesPaymentCreationDto feesDto, TransactionCreationDto transactionDto) {
        Fees fees = feesService.getFeesById(feesDto.getFeesId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Fees ID in fees payment: " + feesDto.getFeesId()));

        if (!fees.getCurrencyCode().equals(transactionDto.getCurrencyCode())) {
            throw new IllegalArgumentException("Currency code mismatch between transaction and fees: " + feesDto.getFeesId());
        }

        if (fees.isExpired()) {
            throw new IllegalArgumentException("Fees is expired: " + feesDto.getFeesId());
        }

        return fees;
    }

    private void validateStudentDetails(TransactionCreationDto transactionDto) {
        Optional<StudentDto> studentOptional = studentServiceClient.getStudentDetailsById(transactionDto.getStudentId());
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid Student ID");
        }

        StudentDto studentDto = studentOptional.get();
        if (!studentDto.getStudentGrade().equals(transactionDto.getGrade())) {
            throw new IllegalArgumentException("Student grade mismatch for transaction: " + transactionDto.getStudentId());
        }
    }
}