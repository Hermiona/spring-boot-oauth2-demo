alter table payment_transactions
add column parent_id bigint
    constraint fk_parent_payment_transactions
    references payment_transactions;