# Tit for Tat Trading Bot Project

## Overview

This project constitutes a trading bot designed to implement a Tit for Tat trading strategy
within 5-minute intervals for BTC-USDT pairs. Utilized a Bybit demo trading account so that this project serves as an  experimental area for  trading algorithms and practicing software engineering principles. I explicitially chose cryptocurrency so that it can trade 24/7. You can access bot
from  https://trading.alppotuk.dev/ and see how well it is doing!

### Components
- **Backend**: Java Spring Boot
- **Trading Scripts**: Python
- **Database**: MySQL
- **Deployment**: Docker, AWS EC2

### Project Structure
```
project/
│
├── docker-compose.yml        
│
├── spring-backend/            # Spring Boot Backend    
│   ├── src/
│   │   └── main/java/         # Java source code
│   └── Dockerfile
│
└── trading-scripts/            # Python Trading Scripts
    ├── main.py
    ├── utils.py
    ├── .env                    # bybit api key and secret
    └── Dockerfile

```

## Tit for Tat Trading Strategy

### What is Tit for Tat?

The Tit for Tat (TFT) trading strategy is inspired by game theory principles. In short, it involves repeating the same
action if the previous transaction was profitable and switching to the opposite action if not. This simple but
reactive approach makes the strategy potentially adaptable to rapidly changing market conditions.


## Technical Components

### Backend (Java Spring Boot)
- REST API for trade management
- Order and trade logging
- Web interface for balance and PnL (Profit and Loss) history

### Trading Scripts (Python)
- Bybit API integration using PyBit
- Trade execution logic
- HTTP communication with Spring Boot backend

### Database (MySQL)
- Stores trade orders
- Provides data for PnL charts

## Deployment

### Infrastructure
- Hosted on AWS EC2 Free Tier
- Dockerized application
- Accessible via custom subdomain

### Docker Composition
The `docker-compose.yml` manages:
- Backend service
- Trading scripts
- MySQL database

## Web Interface

Accessible at https://trading.alppotuk.dev/. The interface provides:
- Real-time balance information
- PnL history chart
- Trade performance metrics

## Setup and Local Development

### Prerequisites
- Docker
- Docker Compose
- Bybit API Credentials

### Steps
1. Clone the repository
2. Configure a .env file under python-scripts folder including Bybit API credentials
3. Run `docker-compose up --build`
4. Access the web interface on port 8080

## Learning Objectives

- Microservices architecture
- Docker containerization
- API integration
- Trading strategy implementation
- Full-stack web application development




*Developed as a personal project to explore trading technologies and software engineering principles.*