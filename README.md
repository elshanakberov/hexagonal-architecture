# Hexagonal Architecture

### Developing a microservices using hexagonal architecture.

### Flow Diagram

[![Flow Diagram](docs/images/diagram.jpg)](docs/images/diagram.jpg)

### Setup

First thing first you should have docker installed on your machine.

In order to setup infra run following command. Then start spring application as usual


```sh
./infra-setup.sh infra up 
```

In order to tear down infra run following command
```sh
./infra-setup.sh infra down
```

### Testing

After successful deployment run following CURL commands

```sh
curl -X GET http://localhost:8090/api/v1/balances?accountId=1
```

```sh
curl -X POST --header "Content-Type: application/json" -d '{
  "accountId": 1,
  "amount": 10,
  "type": "DEPOSIT"
}' http://localhost:8090/api/v1/balances
```


```sh
curl -X POST --header "Content-Type: application/json" -d '{
  "accountId": 1,
  "restaurantId": 10
}' http://localhost:8091/api/v1/orders
```









