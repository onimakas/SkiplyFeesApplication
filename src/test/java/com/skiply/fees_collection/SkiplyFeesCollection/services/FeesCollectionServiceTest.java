//package com.skiply.fees_collection.SkiplyFeesCollection.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.skiply.fees_collection.services.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.skiply.fees_collection.clients.StudentServiceClient;
//import com.skiply.fees_collection.dtos.*;
//import com.skiply.fees_collection.entities.*;
//import com.skiply.fees_collection.mappers.*;
//import com.skiply.fees_collection.repositories.*;
//
//class FeesCollectionServiceTest {
//    @InjectMocks
//    private FeesCollectionServiceImpl feesCollectionService;
//
//    @Mock
//    private TransactionService transactionService;
//
//    @Mock
//    private FeesPaymentService feesPaymentService;
//
//    @Mock
//    private CardService cardService;
//
//    @Mock
//    private FeesService feesService;
//
//    @Mock
//    private SchoolService schoolService;
//
//    @Mock
//    private FeesCollectionValidationService feesCollectionValidationService;
//
//    @Mock
//    private TransactionMapper transactionMapper;
//
//    @Mock
//    private FeesPaymentMapper feesPaymentMapper;
//
//    @Mock
//    private CardMapper cardMapper;
//
//    @Mock
//    private SchoolMapper schoolMapper;
//
//    @Mock
//    private FeesMapper feesMapper;
//
//    @Mock
//    private StudentServiceClient studentServiceClient;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCollectFees() {
//        // Prepare a FeesCollectionDto
//        FeesCollectionDto transactionDto = new FeesCollectionDto();
//        // Populate transactionDto with required data
//
//        // Mock validations
//        doNothing().when(feesCollectionValidationService).validateFeesCollectionRequest(transactionDto);
//
//        // Mock mappings
//        Transaction transaction = new Transaction();
//        when(transactionMapper.toTransaction(transactionDto)).thenReturn(transaction);
//
//        // Mock transactionService.save()
//        when(transactionService.save(transaction)).thenReturn(transaction);
//
//        // Mock feesPaymentService.save()
//        FeesPaymentCreationDto feesDto = new FeesPaymentCreationDto();
//        transactionDto.setFeesPayments(new ArrayList<>()); // Initialize the list
//
//        // Populate feesDto with required data
//        FeesPayment feesPayment = new FeesPayment();
//        when(feesPaymentMapper.toFeesPayment(feesDto)).thenReturn(feesPayment);
//        when(feesPaymentService.save(feesPayment)).thenReturn(feesPayment);
//
//        // Perform the action and assert the result
//        FeesReceiptDto result = feesCollectionService.collectFees(transactionDto);
//
//        // Assertions
//        assertNotNull(result);
//    }
//
////    @Test
////    void testGetFeesReceiptById() {
////        // Prepare a Transaction
////        String transactionId = "12345";
////        Transaction transaction = new Transaction();
////        transaction.setId(transactionId);
////
////        // Mock transactionService.getById()
////        when(transactionService.getById(transactionId)).thenReturn(Optional.of(transaction));
////
////        // Mock buildTransactionDetailsDto()
////
////        // Perform the action and assert the result
////        FeesReceiptDto result = feesCollectionService.getFeesReceiptById(transactionId);
////
////        // Assertions
////        assertNotNull(result);
////        // Add more assertions as needed based on your business logic
////    }
////
////    @Test
////    void testGetAllFeesReceipts() {
////        // Prepare a list of transactions
////        List<Transaction> transactions = new ArrayList<>();
////        transactions.add(new Transaction());
////        transactions.add(new Transaction());
////
////        // Mock transactionService.getAll()
////        when(transactionService.getAll()).thenReturn(transactions);
////
////        // Mock buildTransactionDetailsDto() for each transaction
////        FeesReceiptDto feesReceiptDto1 = new FeesReceiptDto();
////        FeesReceiptDto feesReceiptDto2 = new FeesReceiptDto();
////        when(feesCollectionService.buildTransactionDetailsDto(transactions.get(0))).thenReturn(feesReceiptDto1);
////        when(feesCollectionService.buildTransactionDetailsDto(transactions.get(1))).thenReturn(feesReceiptDto2);
////
////        // Perform the action and assert the result
////        List<FeesReceiptDto> result = feesCollectionService.getAllFeesReceipts();
////
////        // Assertions
////        assertNotNull(result);
////        assertEquals(2, result.size());
////        // Add more assertions as needed based on your business logic
////    }
//
//    // Add more test cases as needed to cover different scenarios
//
//}
