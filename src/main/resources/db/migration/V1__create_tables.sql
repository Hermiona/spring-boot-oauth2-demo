create table users(
    id bigserial not null
    constraint pk_users
    primary key,
    username varchar(255) not null
    constraint uk_username unique,
    password varchar(255) not null,
    fullname varchar(400) not null
    );
create table user_balances(
    id bigserial not null
    constraint pk_user_balances
    primary key,
    user_id bigint not null
    constraint fk_user_balances_users references users,
    balance int not null default 0
);
create table service_providers(
    id bigserial not null
    constraint pk_service_providers
    primary key,
    name varchar(255) not null,
    account_details varchar(255) not null
);
create table payment_transactions(
    id bigserial not null
    constraint pk_payment_transactions
    primary key,
    user_balance_id bigint not null
    constraint fk_payment_transactions_user_balances references user_balances,
    service_provider_id bigint not null
    constraint fk_payment_transactions_service_providers references service_providers,
    amount int not null,
    transaction_timestamp timestamp not null,
    status varchar(255) not null
);