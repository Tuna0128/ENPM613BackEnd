# ENPM613BackEnd

## Environment

Java 17
springboot 2.7.5
maven 4.0.0 (3.8.6也可以）

### Database

PostgreSQL 14
Database name: bookaholic
password: 123456
Create the database before running the application

`mvn install`

## Folder Structure

- entity
  
  - Define the class mapping by database tables

- repository
  
  - Define the querying method. 
  
  - Most of the querying here leverage JPA framework. You can also use SQL here by adding the annotation.

- service
  
  - Define the interface for other components to invoke database query. 
  
  - We may add validations here at database level.

- controller
  
  - Define the APIs

- model
  
  - Define the data structures that will send to or received from frontend

## Testing

To Post a book

Post to this URL: `localhost:8080/teacher/postbook`, with the following JSON payload

```json
{
    "isbn": "123112",
    "title": "test book",
    "pages"： 666
}
```

These payload fields must be the lowercase of member variables of class `BookPayload`. The missing variables will be treated as `null`.
