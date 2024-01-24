INSERT INTO cards (id, card_number, card_type, network, card_holder_name, currency_code, card_expiry_date, created_at, updated_at, is_deleted, deleted_at)
VALUES
    ('CID1', '1234567890123456', 'CREDIT_CARD', 'VISA', 'John Doe', 'USD', '2024-12-31 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('CID2', '9876543210987654', 'CREDIT_CARD', 'MASTERCARD', 'Jane Smith', 'AED', '2023-09-30 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('CID3', '1111222233334444', 'CREDIT_CARD', 'AMEX', 'Bob Johnson', 'AED', '2025-06-30 00:00:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL);

INSERT INTO fees (id, school_id, grade, type, frequency, amount, currency_code, valid_from, valid_till, is_expired, created_at, updated_at, is_deleted, deleted_at)
VALUES
    ('FID1', 'SID1', '1', 'TUITION_FEE', 'MONTHLY', 500.0, 'AED', '2023-01-01 00:00:00', '2023-12-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID2', 'SID2', '2', 'BOOK_FEE', 'ANNUAL', 150.0, 'AED', '2023-01-01 00:00:00', '2023-12-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID3', 'SID3', '3', 'MISC_FEE', 'QUARTERLY', 75.0, 'AED', '2023-01-01 00:00:00', '2023-12-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID4', 'SID4', '4', 'TUITION_FEE', 'ANNUAL', 15000.0, 'AED', '2023-09-01 00:00:00', '2024-08-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID5', 'SID5', '5', 'BOOK_FEE', 'ANNUAL', 500.0, 'AED', '2023-09-01 00:00:00', '2024-08-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID6', 'SID1', '1', 'TUITION_FEE', 'QUARTERLY', 4500.0, 'AED', '2023-09-01 00:00:00', '2023-12-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID7', 'SID2', '2', 'MISC_FEE', 'MONTHLY', 100.0, 'AED', '2023-09-01 00:00:00', '2023-12-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID8', 'SID3', '8', 'TUITION_FEE', 'ANNUAL', 16000.0, 'AED', '2023-09-01 00:00:00', '2024-08-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('FID9', 'SID4', '9', 'BOOK_FEE', 'ANNUAL', 550.0, 'AED', '2023-09-01 00:00:00', '2024-08-31 00:00:00', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL);

INSERT INTO schools (school_id, school_name, created_at, updated_at, is_deleted, deleted_at)
VALUES
    ('SID1', 'Dubai International School', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('SID2', 'Dubai International Academy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('SID3', 'Dubai American School', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('SID4', 'Dubai British School', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL),
    ('SID5', 'Dubai Modern School', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL);

