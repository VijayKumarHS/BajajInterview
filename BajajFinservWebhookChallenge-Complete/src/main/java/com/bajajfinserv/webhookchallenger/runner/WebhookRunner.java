package com.bajajfinserv.webhookchallenger.runner;

import com.bajajfinserv.webhookchallenger.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebhookRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebhookRunner.class);

    @Autowired
    private WebhookService webhookService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("🎯 BAJAJ FINSERV WEBHOOK CHALLENGE STARTED");
        logger.info("=" * 50);

        // Execute the complete webhook challenge flow
        webhookService.executeWebhookFlow();

        logger.info("=" * 50);  
        logger.info("🏁 CHALLENGE EXECUTION COMPLETED");
    }
}
