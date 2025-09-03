# API Endpoints Documentation

## 1. Webhook Generation Endpoint
**URL:** `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
**Method:** POST
**Content-Type:** application/json

### Request Body:
```json
{
    "name": "John Doe",
    "regNo": "REG12346", 
    "email": "john@example.com"
}
```

### Response:
```json
{
    "webhook": "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA",
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## 2. Solution Submission Endpoint
**URL:** `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
**Method:** POST
**Content-Type:** application/json
**Authorization:** Bearer {accessToken}

### Request Body:
```json
{
    "finalQuery": "SELECT c.customer_id, c.customer_name, SUM(o.order_amount) as total_amount FROM customers c JOIN orders o ON c.customer_id = o.customer_id WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) GROUP BY c.customer_id, c.customer_name ORDER BY total_amount DESC LIMIT 5"
}
```

### Response:
```json
{
    "status": "success",
    "message": "Solution submitted successfully"
}
```
