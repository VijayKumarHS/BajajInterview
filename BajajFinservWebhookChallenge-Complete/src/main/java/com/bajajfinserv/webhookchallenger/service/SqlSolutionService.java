package com.bajajfinserv.webhookchallenger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqlSolutionService {

    private static final Logger logger = LoggerFactory.getLogger(SqlSolutionService.class);

    public String solveSqlProblem(String regNo) {
        logger.info("🔍 Analyzing registration number: {}", regNo);

        // Extract last two digits to determine question type
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastDigits = Integer.parseInt(lastTwoDigits);

        logger.info("📊 Last two digits: {} - {}", lastTwoDigits, (lastDigits % 2 == 0 ? "EVEN" : "ODD"));

        if (lastDigits % 2 == 0) {
            // Even - Question 2
            logger.info("📋 Solving Question 2 (Even registration number)");
            return solveQuestion2();
        } else {
            // Odd - Question 1
            logger.info("📋 Solving Question 1 (Odd registration number)");
            return solveQuestion1();
        }
    }

    private String solveQuestion1() {
        // Question 1: Find employees with salary greater than department average
        String query = """
                WITH dept_avg AS (
                    SELECT department_id, AVG(salary) as avg_salary
                    FROM employees
                    GROUP BY department_id
                )
                SELECT e.employee_id, e.name, e.salary, e.department_id
                FROM employees e
                JOIN dept_avg da ON e.department_id = da.department_id
                WHERE e.salary > da.avg_salary
                ORDER BY e.department_id, e.salary DESC
                """;

        logger.info("✅ Question 1 solution prepared");
        return query.trim();
    }

    private String solveQuestion2() {
        // Question 2: Find top 5 customers by total order amount in last year
        String query = """
                SELECT c.customer_id, c.customer_name, 
                       SUM(o.order_amount) as total_amount,
                       COUNT(o.order_id) as total_orders
                FROM customers c
                JOIN orders o ON c.customer_id = o.customer_id
                WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
                GROUP BY c.customer_id, c.customer_name
                ORDER BY total_amount DESC
                LIMIT 5
                """;

        logger.info("✅ Question 2 solution prepared");
        return query.trim();
    }
}
