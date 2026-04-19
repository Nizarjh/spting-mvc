# Branch `With_Hibernate` — Hibernate (SessionFactory)

This branch uses Hibernate directly through `SessionFactory` and configures a `LocalSessionFactoryBean` + `HibernateTransactionManager`.

## Configuration
DB properties are read from `src/main/resources/hibernate.properties` (key names like `hibernate.connection.url`, etc.).

## Transactions
DAO methods are annotated with `@Transactional`.

## Build & run
- `mvn clean package`
- Deploy the WAR to your servlet container.