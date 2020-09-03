# Microservice

There are two main microservices :
1. Currency exchange service - (localhost:8000/currency-exchange/from/USD/to/INR)
-> This provides the conversion rate from USD to INR
-> Gets called by Currency conversion service via Netflix zuul gateway

2. Currency conversion service (http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/1000)
-> Calls currency exchange service with quantity value and recieves the response of the converted currency.
-> There are two implementation of this service, with and without feign

Ports used for different services and tools:

Limits Service	                		http://localhost:8080/limits
Spring Cloud Config Server			http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev
Currency Converter Service - Direct Call	http://localhost:8100/currency-converter/from/USD/to/INR//usr/local/var/log/rabbitmq/
Currency Converter Service - Feign		http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000
Currency Exchange Service			http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR
Eureka						http://localhost:8761/
Zuul - Currency Exchange & Exchange Services	http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR 
 				                http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10
