# Superhero Sightings Web Application (Sightings Feature)

This README provides an in-depth overview of my individual contribution to the Superhero Sightings Web Application project. As part of the Hero Education and Relationship Organization (HERO) initiative, I designed and developed the Sightings feature within the application, allowing users to record, view, edit, and delete superhero/supervillain sightings.

## Feature Overview

The Sightings feature enhances the Superhero Sightings Web Application with the ability to manage superhero/supervillain sighting records. Key functionalities include:

1. **Record Sightings:**
   - Users can log superhero/supervillain sightings, capturing relevant information such as the superhero/supervillain, location, and date/time.

2. **View and Manage Sightings:**
   - Sightings can be viewed, edited, and deleted through a user-friendly interface.

3. **Newsfeed Display:**
   - A dedicated section on the home page displays the latest 10 sightings in the database.

## Components Developed

1. **Database Schema Extension:**
   - Integrated sighting-related tables and columns into the existing database schema, ensuring seamless data storage and retrieval.

2. **Sighting DAO Implementation:**
   - Developed a robust Data Access Object (DAO) layer to handle sighting-related database operations, adhering to the MVC design pattern.
   - Implemented CRUD operations for sightings, ensuring data consistency and reliability.

3. **Unit Testing:**
   - Created comprehensive unit tests to validate the functionality of the Sighting DAO methods.
   - Ensured thorough coverage of create, read, update, and delete operations, including handling of edge cases.

4. **Sighting Views and Controllers:**
   - Designed and implemented user interfaces (views) to facilitate the creation, viewing, editing, and deletion of sightings.
   - Developed corresponding controllers to handle user interactions and manage data flow.


## How to Access and Run

To experience the Sightings feature in action:

1. **Database Setup:**
   - Execute the provided database creation script to include the necessary tables and relationships, including the Sightings table.

2. **Spring Boot Application:**
   - Import the Spring Boot project into your preferred Java IDE.
   - Configure database connection details in the application properties file.
   - Run the Spring Boot application.

3. **Accessing the Sightings Feature:**
   - Open a web browser and navigate to the application URL (default: http://localhost:8082).
   - Explore the Sightings feature by logging, viewing, editing, and deleting superhero/supervillain sightings.

