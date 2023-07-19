# People Queries Spring Boot Application

This is a Java Spring Boot application. The application allows users to upload CSV files of Persons data as Multipart files saves the data to postgres database and query the data based on specific criteria and retrieve filtered results.

## Requirements

To run this application, you need the following installed on your system:

- Java JDK 17 or higher
- Maven

## Getting Started

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/yennetiuday/people_queries.git
   ```

2. Navigate to the project directory:

   ```bash
   cd csv-reader-spring-boot
   ```

3. Build the project using Maven:

   ```bash
   mvn clean package
   ```

4. Run the Spring Boot application:

   ```bash
   java -jar target/people_queries.jar
   ```

## Uploading CSV File

You can upload a CSV file from Postman by making a `POST` request to the `api/v1/people/upload` endpoint with the CSV file as a `form-data` parameter with the key `file`.

## API Endpoints

The application provides the following API endpoints for querying the data:

- **GET /api/v1/people/company/{keyword}**: Retrieve every person who has the specified keyword in their company name.

- **GET /api/v1/people/county/{county}**: Retrieve every person who lives in the specified county.

- **GET /api/v1/people/housenumber/{digits}**: Retrieve every person whose house number is exactly the specified number of digits.

- **GET /api/v1/people/longurl/{length}**: Retrieve every person whose website URL is longer than the specified length (including the protocol and subdomain).

- **GET /api/v1/people/postcodearea**: Retrieve every person who lives in a postcode area with a single-digit value.

- **GET /api/v1/people/phonecomparison**: Retrieve every person whose first phone number is numerically larger than their second phone number.

## Data Model

The application uses a `Person` entity to represent an individual with various attributes such as first name, last name, company, address, etc. The `Person` entity is mapped to a database table using Spring Data JPA.

## Database Configuration

The application uses an embedded H2 database by default, which means the data is stored in-memory and will be lost when the application is stopped. You can modify the database settings in the `application.properties` file if you want to use a different database.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

---
