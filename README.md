🧩 Microservices Demo Project — Java Spring Boot + React + Docker

This repository is a demo project with microservices architecture built using Java Spring Boot, React, Spring Cloud Gateway, PostgreSQL, and a message broker (Kafka or RabbitMQ).
It is created for learning and practice — to develop and test API, UI, integration, and contract tests.

🏗️ Project Architecture
microservices-demo/
├── gateway/            # Spring Cloud Gateway – entry point for backend
├── users-service/      # User management, authentication, JWT tokens
├── market-service/     # Products, cart, and orders
├── delivery-service/   # Delivery logic and message broker integration (Kafka or RabbitMQ)
├── ui/                 # React frontend
├── docker/             # Docker and docker-compose configuration
└── README.md

⚙️ Technologies Used

Java 17+

Spring Boot 3

Spring Cloud Gateway

PostgreSQL

Kafka / RabbitMQ

React (Vite or CRA)

Docker & Docker Compose

JWT Authentication

🚀 How to Run the Project
1. Clone the repository
git clone https://github.com/your-username/microservices-demo.git
cd microservices-demo

2. Build and start services with Docker

Make sure you have Docker and Docker Compose installed.

docker-compose up --build


This will start:

PostgreSQL database

Users service

Market service

Delivery service

Gateway

React UI

Each service will have its own database in the same PostgreSQL container.

🌐 Services Overview
Service	Description	Port
Gateway	API Gateway (entry point)	8080
Users Service	Handles users, auth, JWT	8081
Market Service	Products, orders, cart	8082
Delivery Service	Delivery + Message Broker	8083
UI (React)	Frontend	3000
PostgreSQL	Database	5432
🧪 Testing

This project is designed to practice:

✅ API testing (Postman, RestAssured, etc.)

✅ UI testing (Playwright, Cypress, Selenium)

✅ Integration testing between services

✅ Contract testing (Spring Cloud Contract or Pact)

📚 Purpose

This project is for learning and practicing automation testing with a realistic microservices environment.
It can be used for:

Personal study and test automation practice

Small pet projects

Demos and interviews
