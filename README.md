# Branch `project2` — Spring Data JPA

This branch uses Spring Data JPA repositories (`@EnableJpaRepositories`) and JPA transactions (`JpaTransactionManager`).

## Configuration
DB properties are read from `src/main/resources/hibernate.properties`.

## Build & run
- `mvn clean package`
- Deploy the WAR to your servlet container.