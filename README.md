## Online Travel Management System
- An Collabrative Project Consisting Of the 3 Developer Depicting the implementation of the Trip Managment Platform like MakeMyTrip or Yatra.
- An developement of RESTful API for an application. This API performs all the fundamental CRUD operations of any with user validation at every step.

## Features
- Admin Features
  - Administrator Role of the entire application
  - Only registered admins with valid session token can add/update/delete customer from main database
  - Admin can access the details of different customers and trip bookings
- Customer Features:
  - Registering themselves with application, and logging in to get the valid session token
  - Viewing list of available buses, packages, hotels and booking a trip
  - Only logged in user can access his trip history, profile updation and other features

## Contributors
- Rahul Chamoli 
- Aman Maurya 
- Rounak Dhatterwal 

## Tech Stack
- Java
- Spring Framework
- Spring Security
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- MySQL
- SMTP Server

## Modules
- Login, Logout Module
- Packages Module
- User Module
- Booking Module
- TicketDetails Module
- Route Module
- Travels Module
- Bus Module
- Hotel Module
- Report Module
- Feedback Module

## Flow Chart
- The following Diagram depicts the flow of our application to simplify the flow.


![Trevel-Management-FlowChart](https://github.com/rahulchamoli12/Online_travel_management/assets/105871693/ed95fdb2-01ee-4273-a520-0c5a6025010e)

## ER Diagram
- The following Diagram depicts the flow of our Entity Relation Diagram to simplify the work flow.


![Online_trip_management_ER](https://github.com/rahulchamoli12/Online_travel_management/assets/79252872/f0a8e475-dbae-40bf-9c92-0cad03405953)

## Installation & Run
- Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database config.
```
    spring.datasource.url=jdbc:mysql://localhost:3306/tripManagementSystem;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

## API Root Endpoint
`http://localhost:8088/`

`http://localhost:8088/swagger-ui/`
