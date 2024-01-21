package com.skiply.fees_collection.services;

import com.skiply.fees_collection.dtos.*;
import com.skiply.fees_collection.entities.*;
import com.skiply.fees_collection.mappers.*;
import com.skiply.fees_collection.clients.StudentServiceClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionManagementServiceImpl implements TransactionManagementService {
    private final TransactionService transactionService;
    private final FeesPaymentService feesPaymentService;
    private final CardService cardService;
    private final FeesService feesService;
    private final SchoolService schoolService;
    private final TransactionValidationService transactionValidationService;

    private final TransactionMapper transactionMapper;
    private final FeesPaymentMapper feesPaymentMapper;
    private final CardMapper cardMapper;
    private final SchoolMapper schoolMapper;
    private final FeesMapper feesMapper;

    private final StudentServiceClient studentServiceClient;

    @Override
    @Transactional
    public TransactionDetailsDto createTransaction(TransactionCreationDto transactionDto) {
        transactionValidationService.validateTransactionCreationDto(transactionDto);

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

    @Override
    public TransactionDetailsDto getTransactionDetailsById(String id) {
        Optional<Transaction> transactionOptional = transactionService.getById(id);
        if (transactionOptional.isEmpty()) {
            // Handle the case where the transaction is not found. You could throw an exception or return null.
            throw new EntityNotFoundException("Transaction not found with ID: " + id);
        }

        Transaction transaction = transactionOptional.get();
        return this.buildTransactionDetailsDto(transaction);
    }

    @Override
    public List<TransactionDetailsDto> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAll();
        List<TransactionDetailsDto> dtoList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDetailsDto transactionDetailsDto = buildTransactionDetailsDto(transaction);
            dtoList.add(transactionDetailsDto);
        }

        return dtoList;
    }

    private TransactionDetailsDto buildTransactionDetailsDto(Transaction transaction) {
        TransactionDetailsDto transactionDetailsDto = transactionMapper.toTransactionDetailsDto(transaction);

        Optional<StudentDto> studentOptional = studentServiceClient.getStudentDetailsById(transaction.getStudentId());
        studentOptional.ifPresent(transactionDetailsDto::setStudentDetails);

        Optional<Card> cardOptional = cardService.getCardById(transaction.getPaymentModeId());
        cardOptional.ifPresent(card -> transactionDetailsDto.setCardDetails(cardMapper.toCardDto(card)));

        Optional<School> schoolOptional = schoolService.getSchoolById(transaction.getSchoolId());
        schoolOptional.ifPresent(school -> transactionDetailsDto.setSchoolDetails(schoolMapper.toSchoolDto(school)));

        List<FeesPayment> feesPaymentList = feesPaymentService.getByTransactionId(transaction.getId());
        List<FeesPaymentDto> feesPaymentDtos = this.mapFeesPaymentsToDtos(feesPaymentList);
        transactionDetailsDto.setFeesPayments(feesPaymentDtos);

        return transactionDetailsDto;
    }

//    public List<Transaction> getTransactionsWithFilters(String studentId, String paymentModeId) {
//        return transactionService.findAll(buildSpecification(studentId, paymentModeId));
//    }
//
//    private Specification<Transaction> buildSpecification(Long studentId, Long paymentModeId) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (studentId != null) {
//                predicates.add(criteriaBuilder.equal(root.get("studentId"), studentId));
//            }
//
//            if (paymentModeId != null) {
//                predicates.add(criteriaBuilder.equal(root.get("paymentModeId"), paymentModeId));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }

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
}


