ALTER TABLE payment_transactions
    add constraint check_positive_payment_transactions check (amount > 0);