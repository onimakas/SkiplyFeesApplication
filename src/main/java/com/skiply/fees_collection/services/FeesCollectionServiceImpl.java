package com.skiply.fees_collection.services;

import com.skiply.fees_collection.dtos.*;
import com.skiply.fees_collection.entities.*;
import com.skiply.fees_collection.exceptions.*;
import com.skiply.fees_collection.mappers.*;
import com.skiply.fees_collection.clients.StudentServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class that handles the collection of fees and retrieval of fees receipts.
 * This class provides methods for collecting fees, retrieving fees receipts by ID or all receipts,
 * and validating fee collection requests.
 */
@Service
@AllArgsConstructor
public class FeesCollectionServiceImpl implements FeesCollectionService {
    private final TransactionService transactionService;
    private final FeesPaymentService feesPaymentService;
    private final CardService cardService;
    private final FeesService feesService;
    private final SchoolService schoolService;

    private final TransactionMapper transactionMapper;
    private final FeesPaymentMapper feesPaymentMapper;
    private final CardMapper cardMapper;
    private final SchoolMapper schoolMapper;
    private final FeesMapper feesMapper;

    private final StudentServiceClient studentServiceClient;

    /**
     * Collects fees by creating a new transaction and saving individual fees payment objects in the database.
     * Validates the fees collection request before processing.
     *
     * @param transactionDto The DTO containing the fees collection details.
     * @return The fees receipt DTO.
     * @throws SchoolNotFoundException        if the school ID provided in the request is invalid.
     * @throws InvalidCardIdException          if the card ID provided in the request is invalid.
     * @throws FeesNotFoundException           if the fees ID provided in the request is invalid.
     * @throws MismatchTransactionAmountException if the total amount in the request does not match the sum of individual fees payments.
     * @throws CurrencyCodeMismatchException   if there is a currency code mismatch between the transaction and the fees.
     * @throws FeesExpiryException             if the fees has expired.
     * @throws InvalidStudentIdException       if the student ID provided in the request is invalid.
     * @throws StudentGradeMismatchException   if there is a mismatch between the student grade in the request and the actual student grade.
     */
    @Override
    @Transactional
    public FeesReceiptDto collectFees(FeesCollectionDto transactionDto) {
        validateFeesCollectionRequest(transactionDto);

        Transaction transaction = transactionMapper.toTransaction(transactionDto);
        transaction = transactionService.save(transaction);

        // Save individual fees payment objects in DB.
        for (FeesPaymentCreationDto feesDto : transactionDto.getFeesPayments()) {
            FeesPayment feesPayment = feesPaymentMapper.toFeesPayment(feesDto);
            feesPayment.setTransactionId(transaction.getId());
            feesPaymentService.save(feesPayment);
        }

        return this.buildTransactionDetailsDto(transaction);
    }

    /**
     * Retrieves a fees receipt by its ID.
     *
     * @param id The ID of the fees receipt.
     * @return The fees receipt DTO.
     * @throws TransactionNotFoundException if the fees receipt with the given ID is not found.
     */
    @Override
    public FeesReceiptDto getFeesReceiptById(String id) {
        Optional<Transaction> transactionOptional = transactionService.getById(id);
        if (transactionOptional.isEmpty()) {
            // Handle the case where the transaction is not found. You could throw an exception or return null.
            throw new TransactionNotFoundException("Transaction not found with ID: " + id);
        }

        Transaction transaction = transactionOptional.get();
        return this.buildTransactionDetailsDto(transaction);
    }

    /**
     * Retrieves all fees receipts.
     *
     * @return A list of fees receipt DTOs.
     */
    @Override
    public List<FeesReceiptDto> getAllFeesReceipts() {
        List<Transaction> transactions = transactionService.getAll();
        List<FeesReceiptDto> dtoList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            FeesReceiptDto feesReceiptDto = buildTransactionDetailsDto(transaction);
            dtoList.add(feesReceiptDto);
        }

        return dtoList;
    }

    /**
     * Builds a fees receipt DTO from a transaction entity.
     *
     * @param transaction The transaction entity.
     * @return The fees receipt DTO.
     */
    private FeesReceiptDto buildTransactionDetailsDto(Transaction transaction) {
        FeesReceiptDto feesReceiptDto = transactionMapper.toFeesReceiptDto(transaction);

        Optional<StudentDto> studentOptional = studentServiceClient.getStudentDetailsById(transaction.getStudentId());
        studentOptional.ifPresent(feesReceiptDto::setStudentDetails);

        Optional<Card> cardOptional = cardService.getCardById(transaction.getPaymentModeId());
        cardOptional.ifPresent(card -> feesReceiptDto.setCardDetails(cardMapper.toCardDto(card)));

        Optional<School> schoolOptional = schoolService.getSchoolById(transaction.getSchoolId());
        schoolOptional.ifPresent(school -> feesReceiptDto.setSchoolDetails(schoolMapper.toSchoolDto(school)));

        List<FeesPayment> feesPaymentList = feesPaymentService.getByTransactionId(transaction.getId());
        List<FeesPaymentDto> feesPaymentDtos = this.mapFeesPaymentsToDtos(feesPaymentList);
        feesReceiptDto.setFeesPayments(feesPaymentDtos);

        return feesReceiptDto;
    }

    /**
     * Converts a list of {@link FeesPayment} entities to a list of {@link FeesPaymentDto}.
     * For each {@link FeesPayment} in the list, this method fetches the corresponding {@link Fees} details,
     * maps them to FeesDto, and sets these details in the resulting {@link FeesPaymentDto}.
     *
     * @param feesPaymentList A list of {@link FeesPayment} entities to be converted.
     * @return A list of {@link FeesPaymentDto} containing the mapped details from both
     * {@link FeesPayment} and {@link Fees} entities.
     */
    private List<FeesPaymentDto> mapFeesPaymentsToDtos(List<FeesPayment> feesPaymentList) {
        // Map each FeesPayment to DTO, including details from Fees entity
        return feesPaymentList.stream()
                .map(feesPayment -> {
                    FeesPaymentDto dto = feesPaymentMapper.toFeesPaymentDto(feesPayment);
                    Optional<Fees> feesOptional = feesService.getFeesById(feesPayment.getFeesId());
                    feesOptional.ifPresent(fees -> dto.setFeesDetails(feesMapper.toFeesDto(fees)));
                    return dto;
                })
                .toList();
    }

    /**
     * Validates the fees collection request.
     *
     * @param feesCollectionDto The fees collection request DTO.
     * @throws SchoolNotFoundException        if the school ID provided in the request is invalid.
     * @throws InvalidCardIdException          if the card ID provided in the request is invalid.
     * @throws FeesNotFoundException           if the fees ID provided in the request is invalid.
     * @throws MismatchTransactionAmountException if the total amount in the request does not match the sum of individual fees payments.
     * @throws CurrencyCodeMismatchException   if there is a currency code mismatch between the transaction and the fees.
     * @throws FeesExpiryException             if the fees has expired.
     * @throws InvalidStudentIdException       if the student ID provided in the request is invalid.
     * @throws StudentGradeMismatchException   if there is a mismatch between the student*/
    private void validateFeesCollectionRequest(FeesCollectionDto feesCollectionDto) {
        validateSchoolExists(feesCollectionDto);
        validatePaymentMode(feesCollectionDto);
        validateFeesPayments(feesCollectionDto);
        validateStudentDetails(feesCollectionDto);
    }

    private void validateSchoolExists(FeesCollectionDto transactionDto) {
        schoolService.getSchoolById(transactionDto.getSchoolId())
                .orElseThrow(() -> new SchoolNotFoundException("Invalid School ID" + transactionDto.getSchoolId()));
    }

    private void validatePaymentMode(FeesCollectionDto transactionDto) {
        if (transactionDto.getPaymentMode() == PaymentMode.CREDIT_CARD) {
            cardService.getCardById(transactionDto.getPaymentModeId())
                    .orElseThrow(() -> new InvalidCardIdException("Invalid Card ID" + transactionDto.getPaymentModeId()));
        }
    }

    private void validateFeesPayments(FeesCollectionDto transactionDto) {
        double totalFeesAmount = 0;
        for (FeesPaymentCreationDto feesDto : transactionDto.getFeesPayments()) {
            Fees fees = validateIndividualFees(feesDto, transactionDto);
            totalFeesAmount += feesDto.getAmount() * feesDto.getQuantity();
        }

        if (totalFeesAmount != transactionDto.getAmount()) {
            throw new MismatchTransactionAmountException("Total amount does not match the sum of individual fees payments");
        }
    }

    private Fees validateIndividualFees(FeesPaymentCreationDto feesDto, FeesCollectionDto transactionDto) {
        Fees fees = feesService.getFeesById(feesDto.getFeesId())
                .orElseThrow(() -> new FeesNotFoundException("Invalid Fees ID in fees payment: " + feesDto.getFeesId()));

        if (!fees.getCurrencyCode().equals(transactionDto.getCurrencyCode())) {
            throw new CurrencyCodeMismatchException("Currency code mismatch between transaction and fees: " + feesDto.getFeesId());
        }

        if (fees.isExpired()) {
            throw new FeesExpiryException("Fees is expired: " + feesDto.getFeesId());
        }

        return fees;
    }

    private void validateStudentDetails(FeesCollectionDto transactionDto) {
        Optional<StudentDto> studentOptional = studentServiceClient.getStudentDetailsById(transactionDto.getStudentId());
        if (studentOptional.isEmpty()) {
            throw new InvalidStudentIdException("Invalid Student ID");
        }

        StudentDto studentDto = studentOptional.get();
        if (!studentDto.getStudentGrade().equals(transactionDto.getGrade())) {
            throw new StudentGradeMismatchException("Student grade mismatch for transaction: " + transactionDto.getStudentId());
        }
    }
}
