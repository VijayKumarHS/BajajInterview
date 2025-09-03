package com.bajajfinserv.webhookchallenger.service;

import com.bajajfinserv.webhookchallenger.dto.SolutionRequest;
import com.bajajfinserv.webhookchallenger.dto.WebhookRequest;
import com.bajajfinserv.webhookchallenger.dto.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String WEBHOOK_GENERATION_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SqlSolutionService sqlSolutionService;

    public void executeWebhookFlow() {
        try {
            logger.info("🚀 Starting webhook flow execution...");

            // Step 1: Generate webhook
            WebhookResponse webhookResponse = generateWebhook();

            if (webhookResponse != null && webhookResponse.getWebhook() != null) {
                logger.info("✅ Webhook generated successfully");
                logger.info("📡 Webhook URL: {}", webhookResponse.getWebhook());

                // Step 2: Solve SQL problem (even regNo = Question 2)
                String sqlSolution = sqlSolutionService.solveSqlProblem("REG12346");
                logger.info("🧠 SQL Solution prepared");

                // Step 3: Submit solution
                submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlSolution);
                logger.info("🎯 Challenge completed successfully!");
            } else {
                logger.error("❌ Failed to generate webhook");
            }

        } catch (Exception e) {
            logger.error("💥 Error in webhook flow execution: ", e);
        }
    }

    private WebhookResponse generateWebhook() {
        try {
            logger.info("📝 Generating webhook with registration details...");
            WebhookRequest request = new WebhookRequest("John Doe", "REG12346", "john@example.com");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                    WEBHOOK_GENERATION_URL, entity, WebhookResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                logger.info("✅ Webhook generation successful");
                return response.getBody();
            } else {
                logger.error("❌ Webhook generation failed with status: {}", response.getStatusCode());
            }

        } catch (Exception e) {
            logger.error("💥 Error generating webhook: ", e);
        }
        return null;
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        try {
            logger.info("📤 Submitting solution to webhook...");
            SolutionRequest request = new SolutionRequest(sqlQuery);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("✅ Solution submitted successfully!");
                logger.info("📋 Response: {}", response.getBody());
            } else {
                logger.error("❌ Solution submission failed with status: {}", response.getStatusCode());
            }

        } catch (Exception e) {
            logger.error("💥 Error submitting solution: ", e);
        }
    }
}
