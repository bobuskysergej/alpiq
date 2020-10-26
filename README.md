## Products application

This is an application that exposes endpoints to retrieve suggestions for cities. The collections of existing cities is loaded
upon the start from a data file.


## Tests
To run the tests navigate to the root folder and run:  `mvn clean test` 


## Launching the app
To launch the app run: `mvn spring-boot:run`

The application will start locally on the port 8080. 

Once the application has booted up it's ready to process incoming http requests. To see what endpoints it exposes 
navigate to: `http://localhost:8080//swagger-ui.html`

