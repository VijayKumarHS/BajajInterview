# Bajaj Finserv Health Webhook Challenge

## Project Overview
This Spring Boot application solves the Bajaj Finserv Health JAVA Qualifier challenge. It automatically generates webhooks, solves SQL problems based on registration number (even/odd), and submits solutions using JWT authentication.

## Features
- ✅ Automatic webhook generation on startup
- ✅ SQL problem solving (Question 2 for even registration numbers)
- ✅ JWT token authentication
- ✅ RestTemplate HTTP client implementation
- ✅ CommandLineRunner for startup execution
- ✅ Comprehensive error handling and logging

## Quick Start
1. Ensure Java 17+ is installed
2. Run: `./build-and-run.sh` (Linux/Mac) or `build-and-run.bat` (Windows)
3. Or manually: `mvn clean package && java -jar target/webhook-challenger-0.0.1-SNAPSHOT.jar`

## Project Structure
```
BajajFinservWebhookChallenge/
├── src/main/java/com/bajajfinserv/webhookchallenger/
│   ├── WebhookChallengerApplication.java
│   ├── config/WebhookConfig.java
│   ├── dto/[Request/Response DTOs]
│   ├── service/[Business Logic Services]
│   └── runner/WebhookRunner.java
├── src/main/resources/application.properties
├── pom.xml
├── build-and-run.sh/.bat
└── Documentation/

```

## SQL Solution (Even Registration Numbers - Question 2)
```sql
SELECT c.customer_id, c.customer_name, 
       SUM(o.order_amount) as total_amount,
       COUNT(o.order_id) as total_orders
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY c.customer_id, c.customer_name
ORDER BY total_amount DESC
LIMIT 5;
```

Built for Bajaj Finserv Health JAVA Qualifier Challenge 2025
