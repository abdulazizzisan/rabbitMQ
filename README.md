# Spring Boot RabbitMQ Integration

A demonstration project showcasing RabbitMQ integration with Spring Boot, including both string and JSON message publishing and consuming patterns.

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)

## 🎯 About

This project demonstrates a complete RabbitMQ integration with Spring Boot, implementing producer-consumer patterns for both simple string messages and JSON objects. It provides REST APIs to publish messages and automatically consumes them using RabbitMQ listeners.

## ✨ Features

- **String Message Publishing**: Send simple text messages through RabbitMQ
- **JSON Message Publishing**: Send complex JSON objects (User entities) through RabbitMQ
- **Automatic Message Consumption**: Listeners automatically consume messages from queues
- **Topic Exchange**: Uses RabbitMQ Topic Exchange for message routing
- **RESTful APIs**: Exposes endpoints for message publishing
- **Structured Configuration**: Centralized RabbitMQ configuration
- **Logging**: SLF4J logging for message tracking

## 🛠️ Technologies

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring AMQP** (Advanced Message Queuing Protocol)
- **RabbitMQ**
- **Lombok**
- **Gradle**

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 21** or higher
- **Gradle** (or use the included Gradle wrapper)
- **RabbitMQ Server** (running on localhost:5672)

### Installing RabbitMQ

**macOS (using Homebrew):**
```bash
brew install rabbitmq
brew services start rabbitmq
```

**Docker:**
```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```

Access RabbitMQ Management Console at `http://localhost:15672` (default credentials: guest/guest)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd spring-boot-rabbitmq
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

   Or run the JAR file:
   ```bash
   java -jar build/libs/rabbitmq-0.0.1-SNAPSHOT.jar
   ```

The application will start on `http://localhost:8080`

## ⚙️ Configuration

The RabbitMQ configuration is defined in `src/main/resources/application.properties`:

```properties
# RabbitMQ Connection
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Queue and Exchange Configuration
rabbitmq.queue.name=my_first_queue
rabbitmq.queue.json.name=my_json_queue
rabbitmq.exchange.name=my_first_exchange
rabbitmq.routing.key=my_first_routing_key
rabbitmq.key.routing.json=my_json_routing_key
```

## 📖 Usage

### Publishing String Messages

Send a GET request with a message parameter:

```bash
curl "http://localhost:8080/api/v1/publish?message=Hello%20RabbitMQ"
```

**Response:**
```json
{
  "message": "Message sent to RabbitMQ Successfully"
}
```

### Publishing JSON Messages

Send a POST request with a User JSON object:

```bash
curl -X POST http://localhost:8080/api/v1/json-message/send \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
  }'
```

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## 🔌 API Endpoints

| Method | Endpoint                       | Description                  | Request Body        |
|--------|--------------------------------|------------------------------|---------------------|
| GET    | `/api/v1/publish`              | Publish a string message     | Query param: message |
| POST   | `/api/v1/json-message/send`    | Publish a JSON message       | User JSON object    |

### Example User JSON Schema:
```json
{
  "id": 1,
  "name": "string",
  "email": "string"
}
```

## 📂 Project Structure

```
src/main/java/dev/zisan/rabbitmq/
├── RabbitmqApplication.java          # Main Spring Boot application
├── config/
│   └── RabbitMQConfig.java           # RabbitMQ configuration (queues, exchanges, bindings)
├── controller/
│   ├── MessageController.java        # REST controller for string messages
│   └── JosnMessageController.java    # REST controller for JSON messages
├── publisher/
│   ├── RabbitMQProducer.java         # Producer for string messages
│   └── RabbitMQJsonProducer.java     # Producer for JSON messages
├── consumer/
│   ├── RabbitMQConsumer.java         # Consumer for string messages
│   └── RabbitMQJsonConsumer.java     # Consumer for JSON messages
└── dto/
    └── User.java                     # User data transfer object
```

## 🔄 How It Works

### Message Flow

1. **Producer Flow:**
   - Client sends HTTP request to REST controller
   - Controller invokes Producer service
   - Producer publishes message to RabbitMQ Exchange with routing key
   - Exchange routes message to appropriate Queue based on binding

2. **Consumer Flow:**
   - Consumer listens to specific queue using `@RabbitListener`
   - When message arrives, consumer automatically processes it
   - Message is logged and processed

### Architecture Components

- **Queue**: Message buffer that stores messages
- **Exchange**: Routes messages to queues based on routing keys
- **Binding**: Links exchange to queue with routing key
- **Producer**: Publishes messages to exchange
- **Consumer**: Consumes messages from queue

### Message Types

1. **String Messages**:
   - Queue: `my_first_queue`
   - Routing Key: `my_first_routing_key`
   - Producer: `RabbitMQProducer`
   - Consumer: `RabbitMQConsumer`

2. **JSON Messages**:
   - Queue: `my_json_queue`
   - Routing Key: `my_json_routing_key`
   - Producer: `RabbitMQJsonProducer`
   - Consumer: `RabbitMQJsonConsumer`
   - Uses Jackson for JSON serialization/deserialization

## 📝 Notes

- All messages are logged using SLF4J
- The application uses Lombok to reduce boilerplate code
- Jackson2JsonMessageConverter is used for JSON message conversion
- Spring Boot auto-configures ConnectionFactory and RabbitTemplate

## 🤝 Contributing

Feel free to fork this project and submit pull requests for any improvements.

## 📄 License

This project is open-source and available for educational purposes.

---

**Happy Messaging! 🐰**

