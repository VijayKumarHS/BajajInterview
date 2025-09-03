# Submission Guide

## Pre-Submission Checklist
- [x] Spring Boot application created
- [x] CommandLineRunner implemented for startup execution
- [x] RestTemplate configured for HTTP requests
- [x] JWT authentication implemented
- [x] SQL solution for even registration numbers
- [x] Complete error handling and logging
- [x] JAR file generated and tested

## GitHub Repository Setup

### 1. Create Repository
```bash
git init
git add .
git commit -m "Initial commit: Bajaj Finserv Webhook Challenge"
git branch -M main
git remote add origin https://github.com/USERNAME/BajajFinservWebhookChallenge.git
git push -u origin main
```

### 2. Create Release with JAR
1. Build the project: `mvn clean package`
2. Go to GitHub repository → Releases → Create a new release
3. Tag version: `v1.0`
4. Release title: `Bajaj Finserv Webhook Challenge v1.0`
5. Upload `target/webhook-challenger-0.0.1-SNAPSHOT.jar`
6. Publish release

### 3. Get Raw Download Link
Format: `https://github.com/USERNAME/REPO/releases/download/v1.0/webhook-challenger-0.0.1-SNAPSHOT.jar`

## Final Submission Format

### GitHub Repository URL:
```
https://github.com/your-username/BajajFinservWebhookChallenge.git
```

### JAR Download Link:
```
https://github.com/your-username/BajajFinservWebhookChallenge/releases/download/v1.0/webhook-challenger-0.0.1-SNAPSHOT.jar
```

## Verification Steps
1. Clone the repository
2. Run `./build-and-run.sh` or `build-and-run.bat`
3. Verify application starts and executes webhook flow
4. Check logs for successful webhook generation and solution submission
5. Download JAR from release and verify it runs independently

## Submission Portal
Submit at: https://forms.office.com/r/BJ76UXXKqe

Include both:
- GitHub repository URL (.git)
- Direct JAR download link
