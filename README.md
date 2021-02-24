# Driver service

The web application will be useful for companies operating in the
transport sector. It allows you to manage drivers and vehicles, link and
scroll through the lists of entities with the possibility of dynamic
changes.

## Project Structure
The project has an N-tier structure and consists of the database layer, the DAO layer for interaction with the database, the service layer which contains the business logic, and the presentation layer.  
The DAO layer is built with the JDBC API.  
Servlets are used to receive and respond to client requests.  
Filters control access to the store's functionality.  
The presentation layer is implemented with JSP and Bootstrap.  
This project also includes custom-made annotations and an injector, which utilizes Reflection API. 
 
## Technologies Used

 - Java 11
 - Maven 3.1.1
 - Maven Checkstyle Plugin
 - Javax Servlet API 3.1.0
 - JSTL 1.2
 - JSP
 - Apache Tomcat
 - MySQL RDBMS
 - Bootstrap

## Running the Project

To run the project on your local machine, clone this project into your local folder and open the project in an IDE. 

Configure Tomcat Server and set up the MySQL DS and MySQL Workbench on your machine. 

Replicate the database from the project by copying the script from init_db.sql into the MySQL Workbench query editor window. 

Insert your own MySQL username and login in dbProperties in the ConnectionUtil class. 

Your MySQL server must be up and running when you launch the project.

When you launch the website for the first time, click on "Inject data" to add the user and admin data to the DB so that the store works properly.

To log in as a User on the website without registration, you can log in as `imp` with the password `password1234`. 

To log in as an Admin on the website, you should log in as `admin` with the password `1234`. 
