#   Test Demo
## 0. For Postman testing  purpose  oauth2 was got around with commenting line 20 in WebSecurityConfig.java.
### If you want to go through oauth2 process, then comment line 19 and comment out  line 20.
## 1. run maven clean command
## 2. run maven install command
## 3. run docker-compose file in bash via:
```sh
run docker-compose up --build
```
## 4. Populate postgres database with test initial data simply for the convenience purpose running command in bash:
```sh
cat ./init.sql | docker exec -i postgres-demo psql -U postgres -d testdb
```
## 5. You can test the following ready endpoints via Postman:
### Get list of user's balances
#### localhost:8081/api/user/1/user_balances
#### GET-request
### Create payment transaction between user and service provider
#### localhost:8081/api/user/1/payment_transactions/doCreate
#### POST request with body:
```
{
    "userBalanceId":1,
    "serviceProviderId":1,
    "amount": 10
}
```
### Cancel certain payment transaction
#### localhost:8081/api/user/1/payment_transactions/54/doCancel
#### POST request with body:
```
{
    "userBalanceId":1,
    "serviceProviderId":1
}
```
### Get service provider's sum and count of successful payment transactions
#### localhost:8081/api/service_providers_transactions/2
#### GET request
