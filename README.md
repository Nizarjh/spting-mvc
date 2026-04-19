# spring-mvc

Spring MVC (non-Boot) training project. The same application is implemented in multiple branches to compare different data access approaches.

## Branches
- `main` — Spring MVC + `JdbcTemplate`
- `With_Hibernate` — Spring MVC + Hibernate (SessionFactory)
- `project2` — Spring MVC + Spring Data JPA

See the branch-specific notes in readme

## Tech stack
- Java 17
- Maven (WAR packaging)
- Spring Framework 7
- Thymeleaf views
- PostgreSQL (via JDBC driver)

## How to run (high level)
1. Create a PostgreSQL database.
2. Apply SQL from `post.session.sql` (if applicable).
3. Configure DB properties (see the branch README).
4. Build: `mvn clean package`
5. Deploy the generated WAR to a Jakarta Servlet 6 compatible container.