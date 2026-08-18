# Payment Management Microservice

Java 21 + Spring Boot + MySQL payment microservice.

## Database

```sql
CREATE DATABASE payment_db;
```

The service runs on port `8081`.

Configure credentials through environment variables:

```text
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

PowerShell:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
mvn spring-boot:run
```

## APIs

### Create
`POST /api/payments`

```json
{
  "orderId": 1,
  "amount": 75000.00,
  "paymentMethod": "UPI",
  "status": "INITIATED",
  "transactionReference": "TXN-10001"
}
```

### Get all
`GET /api/payments`

### Get by ID
`GET /api/payments/1`

### Get by order
`GET /api/payments/order/1`

### Full update
`PUT /api/payments/1`

```json
{
  "orderId": 1,
  "amount": 75000.00,
  "paymentMethod": "CARD",
  "status": "SUCCESS",
  "transactionReference": "TXN-10001"
}
```

### Partial update
`PATCH /api/payments/1`

```json
{
  "status": "SUCCESS"
}
```

### Delete
`DELETE /api/payments/1`

Returns `204 No Content`.

## Architecture

Client -> PaymentController -> PaymentService -> PaymentRepository -> JPA/Hibernate -> MySQL

`orderId` is stored as a `Long`, not a JPA relationship, so this service remains independently deployable from the Order Service.
