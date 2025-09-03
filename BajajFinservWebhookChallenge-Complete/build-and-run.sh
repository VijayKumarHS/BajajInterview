#!/bin/bash
echo "🚀 Building and Running Bajaj Finserv Webhook Challenge..."

# Check Java
if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Please install Java 17+"
    exit 1
fi

# Check Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven not found. Please install Maven 3.6+"
    exit 1
fi

# Build
echo "📦 Building project..."
mvn clean compile package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo "🏃‍♂️ Running application..."
    java -jar target/webhook-challenger-0.0.1-SNAPSHOT.jar
else
    echo "❌ Build failed!"
    exit 1
fi
