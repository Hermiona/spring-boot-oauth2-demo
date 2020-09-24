insert into users(username, password, fullname)
values('customUser', '111', 'Custom User Surname');

insert into service_providers(name, account_details)
values ('aknet', '789456'),
       ('megaline', '124789');

insert into user_balances(user_id, balance)
values (1, 100),
       (1, 200),
       (1,300);

insert into payment_transactions(user_balance_id, service_provider_id, amount, transaction_timestamp, status)
values (1, 1, 5, '2020-09-22 21:28:00.050078', 'PENDING'),
       (1, 1, 20, '2020-09-22 21:29:00.050078','PENDING'),
       (1, 2, 10, '2020-09-22 21:29:05.050078','PENDING'),
       (2, 2, 10, '2020-09-22 21:27:05.050078','PENDING');



