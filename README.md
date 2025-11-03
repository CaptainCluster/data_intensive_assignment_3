# Data-Intensive Systems Assignment 3


Set up
---

1. Create .env file in the `Assignment3` directory

2. Create three databases (db1, db2, db3)

3. Run Flyway migrations with `gradlew`. Look into `build.gradle`. Example command: `./gradlew migrate1`

4. Now you can run the application. Jooq will automatically generate classes in a separate directory based on the tables.

.env values
---
Here are example variables

>DB_URL_1=jdbc:postgresql://localhost:5432/db1
>
>DB_URL_2=jdbc:postgresql://localhost:5432/db2
>
>DB_URL_3=jdbc:postgresql://localhost:5432/db3
>
>DB_USERNAME=postgres
>
>DB_PASSWORD=password
>

Dependencies
---
1. Flyway - A database migration/version control tool. Manages db table creation in this project.
2. Jooq - Creates Java classes from the tables created with Flyway
3. Slf4j - The tool I used for printing. (Project Lombok)
4. Spring Boot - Used for easy usage of Java beans (services, repos, etc) 
