# SQL Solutions Documentation

## Question Selection Logic
- **Registration Number Last 2 Digits:**
  - Even → Question 2
  - Odd → Question 1

## Question 1 (Odd Registration Numbers)
**Problem:** Find all employees who earn more than the average salary of their department.

### Sample Tables:
```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10, 2),
    department_id INT
);
```

### Solution:
```sql
WITH dept_avg AS (
    SELECT department_id, AVG(salary) as avg_salary
    FROM employees
    GROUP BY department_id
)
SELECT e.employee_id, e.name, e.salary, e.department_id
FROM employees e
JOIN dept_avg da ON e.department_id = da.department_id
WHERE e.salary > da.avg_salary
ORDER BY e.department_id, e.salary DESC;
```

## Question 2 (Even Registration Numbers) - IMPLEMENTED
**Problem:** Find top 5 customers by total order amount in the last year.

### Sample Tables:
```sql
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_amount DECIMAL(10, 2),
    order_date DATE
);
```

### Solution:
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

## Key SQL Concepts Used:
- **JOINs:** Combining data from multiple tables
- **Aggregation:** SUM, COUNT functions
- **Date Functions:** DATE_SUB, CURDATE
- **Grouping:** GROUP BY clause
- **Sorting:** ORDER BY with DESC
- **Limiting Results:** LIMIT clause
- **Common Table Expressions (CTE):** WITH clause for complex queries
