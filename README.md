# supporthub.
with lombok and flyway


Spring boot Tutorial:

Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

Add dependecies in pom.xml file
configure application.yml to set the app configs

create Entities


______________

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA generates basic CRUD automatically
}



For ProductRepository, these become:

productRepository.save(product);
productRepository.findById(1L);
productRepository.findAll();
productRepository.deleteById(2L);
productRepository.count();

_______________


Custom Query Methods



Spring Data can generate queries from method names. Example:

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByCategoryAndPriceLessThan(String category, double price);
}


These translate to SQL like:

SELECT * FROM product WHERE name = ?;
SELECT * FROM product WHERE price < ?;
SELECT * FROM product WHERE category = ? AND price < ?;



Custom Queries (JPQL) - Java Persistence Query Language
If the method name is too complex, use @Query:


@Param
Used in @Query to bind method parameters to query parameters.


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchByName(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM product WHERE price > :price", nativeQuery = true)
    List<Product> findExpensive(@Param("price") double price);
}


It’s the query language used in JPA (Java Persistence API) to query entities (your mapped classes) 
instead of directly querying database tables.

SQL → works on tables & columns.
JPQL → works on entities & their fields.

So, JPQL is object-oriented and database-agnostic, while SQL is tied to the database schema.


Transactions

All repository methods are already wrapped with @Transactional by Spring Data:

-> Write methods (save, delete) → transactional with commit.
-> Read methods (findBy...) → transactional with read-only.

So you don’t need to manually add @Transactional most of the time.
_______________



Annotations:

@SpringBootApplication → main entry point, enables auto-configuration.

@Entity → JPA entity.

@Id, @GeneratedValue → primary key.

@Column → configure column details.

@Repository → persistence layer (JpaRepository already adds this).

@Service → service layer.

@RestController → REST APIs.


@Data

Automatically generates:

Getters for all fields
Setters for all fields
toString() method
equals() and hashCode()

Required constructor (no-args by default)



@Builder

Allows you to create objects in a readable way without using long constructors.

Implements the builder pattern automatically.


@Builder
@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
}


Now you can create a Product like this:

Product p = Product.builder()
                   .id(1L)
                   .name("Laptop")
                   .price(899.0)
                   .build();




_____

@Autowired
It’s Spring’s way of doing Dependency Injection (DI).
Instead of you manually creating objects with new.
Spring automatically injects the required bean (class instance) wherever you need it

if we define at field level it needs to ve everywhere.


@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    @Autowired  
    public OrderService(ProductRepository productRepository,
                        PaymentService paymentService,
                        NotificationService notificationService) {
        this.productRepository = productRepository;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
}


_____

Spring automatically injects the required bean (class instance) wherever you need it

@RequestMapping, @GetMapping, @PostMapping → endpoint mappings.

@Transactional → transaction boundaries for DB operations.
It tells Spring that a method (or class) should run inside a database transaction.

A transaction means: either all database operations succeed , or all are rolled back  (to keep data consistent).

On a method → only that method runs in a transaction.
On a class → all public methods in that class are transactional.

Usually at the Service Layer, not at the Repository level.

Repository already handles single operations.
Service often involves multiple steps → so you want all-or-nothing behavior.


@Transactional = all-or-nothing guarantee for DB operations.

Rollback on failure, commit on success.
Place it at service layer methods that do multiple DB updates.



@Transactional
public void transferMoney(Long fromAccount, Long toAccount, double amount) {
    accountRepository.debit(fromAccount, amount);  // Step 1
    accountRepository.credit(toAccount, amount);  // Step 2
    // If Step 2 fails, Step 1 is rolled back automatically
}


__________________


MVC

Model–View–Controller (MVC) — it’s the core design pattern behind Spring Boot web apps (and many frameworks).

It’s a way to separate responsibilities in your application:

Model → The data (your business objects, entities, database layer).

View → The UI (what the user sees: HTML, JSON, etc.).

Controller → The logic that connects the two (handles requests, prepares responses).


_______________

DAO = Repository interface ✅

No extra DAO class is required unless you want custom complex queries using Hibernate Session or JDBC.

DAO = Data Access Object

It’s a layer that handles all database operations (CRUD) for an entity.
Keeps persistence logic separate from business logic.


__________




Logging Levels

Level	Use Case
trace	Very detailed, usually for debugging deep issues
debug	Debug info for developers
info	General runtime info, like “server started”
warn	Something unexpected but not fatal
error	Something broke, must fix



Logging:

Logs go to console, and the default level is INFO.


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void addProduct(Product product) {
        logger.info("Adding product: {}", product.getName());
        // your save logic
        logger.debug("Product details: {}", product);
    }
}




LOMBOK:


@Slf4j automatically creates log variable for you.
Works with info, debug, warn, error levels.


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

    public void addProduct(Product product) {
        log.info("Adding product: {}", product.getName());
        log.debug("Product details: {}", product);
    }
}


private static final Logger logger = LoggerFactory.getLogger(ProductService.class) - EXPLANATION


LoggerFactory.getLogger(ProductService.class)

LoggerFactory is from SLF4J — it creates a Logger instance.

getLogger(Class) tells the logger which class it belongs to.

Why class? So logs can show which class the message came from, making it easier to debug.



private static final

private → Only this class can access the logger.

static → Shared across all instances of the class (you don’t need a new logger per object).

final → You don’t want to reassign the logger; it stays constant.


Example output:
2025-09-13 14:30:12 INFO ProductService: Adding product: Laptop
2025-09-13 14:30:13 INFO ProductController: Fetching all products



____________

log4j2 proprties:

# Set global log level
logging.level.root=INFO

# Enable DEBUG logs globally
logging.level.root=DEBUG
