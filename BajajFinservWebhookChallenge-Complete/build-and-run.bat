@echo off
echo 🚀 Building and Running Bajaj Finserv Webhook Challenge...

REM Check Java
java -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Java not found. Please install Java 17+
    exit /b 1
)

REM Check Maven
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Maven not found. Please install Maven 3.6+
    exit /b 1
)

REM Build
echo 📦 Building project...
mvn clean compile package -DskipTests

if %errorlevel% == 0 (
    echo ✅ Build successful!
    echo 🏃‍♂️ Running application...
    java -jar target/webhook-challenger-0.0.1-SNAPSHOT.jar
) else (
    echo ❌ Build failed!
    exit /b 1
)
