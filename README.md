# SupportHub

With Lombok and Flyway

---

## Spring Boot Tutorial

- Spring Boot makes it easy to create stand-alone, production-grade Spring-based applications that you can "just run".
- Add dependencies in `pom.xml` and configure `application.yml` to set the app configs.
- Create Entities:

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA generates basic CRUD automatically
}
Basic CRUD with ProductRepository
java
Copy code
productRepository.save(product);
productRepository.findById(1L);
productRepository.findAll();
productRepository.deleteById(2L);
productRepository.count();
Custom Query Methods
Spring Data can generate queries from method names.

java
Copy code
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByCategoryAndPriceLessThan(String category, double price);
}
Translates to SQL:

sql
Copy code
SELECT * FROM product WHERE name = ?;
SELECT * FROM product WHERE price < ?;
SELECT * FROM product WHERE category = ? AND price < ?;
Custom Queries (JPQL)
If the method name is too complex, use @Query.

@Param is used in @Query to bind method parameters to query parameters.

java
Copy code
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchByName(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM product WHERE price > :price", nativeQuery = true)
    List<Product> findExpensive(@Param("price") double price);
}
SQL works on tables & columns.

JPQL works on entities & their fields.

JPQL is object-oriented and database-agnostic, while SQL is tied to the database schema.

Transactions
All repository methods are already wrapped with @Transactional by Spring Data:

Write methods (save, delete) → transactional with commit

Read methods (findBy...) → transactional with read-only

Most of the time, you don’t need to manually add @Transactional.

Common Annotations
@SpringBootApplication → main entry point, enables auto-configuration

@Entity → JPA entity

@Id, @GeneratedValue → primary key

@Column → configure column details

@Repository → persistence layer (JpaRepository already adds this)

@Service → service layer

@RestController → REST APIs

@Data → generates getters, setters, toString(), equals(), hashCode()

@Builder → enables builder pattern

java
Copy code
@Builder
@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
}
Usage:

java
Copy code
Product p = Product.builder()
                   .id(1L)
                   .name("Laptop")
                   .price(899.0)
                   .build();
@Autowired
Spring’s way of doing Dependency Injection (DI)

Injects required bean wherever you need it

java
Copy code
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
Request Mappings
@RequestMapping, @GetMapping, @PostMapping → endpoint mappings

@Transactional
Defines transaction boundaries for DB operations

Ensures all-or-nothing behavior

java
Copy code
@Transactional
public void transferMoney(Long fromAccount, Long toAccount, double amount) {
    accountRepository.debit(fromAccount, amount); // Step 1
    accountRepository.credit(toAccount, amount);  // Step 2
    // If Step 2 fails, Step 1 is rolled back automatically
}
MVC (Model–View–Controller)
Model → data (entities, business objects, database layer)

View → UI (HTML, JSON, etc.)

Controller → logic that connects model and view

DAO
Repository interface handles CRUD

DAO = Data Access Object, separates persistence logic from business logic

Usually, no extra DAO class is needed with Spring Data JPA

Logging Levels
Level	Use Case
TRACE	Very detailed, usually for deep debugging
DEBUG	Debug info for developers
INFO	General runtime info, like “server started”
WARN	Something unexpected but not fatal
ERROR	Something broke, must fix

Logging Example (SLF4J)
java
Copy code
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void addProduct(Product product) {
        logger.info("Adding product: {}", product.getName());
        logger.debug("Product details: {}", product);
    }
}
Lombok shortcut with @Slf4j:

java
Copy code
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

    public void addProduct(Product product) {
        log.info("Adding product: {}", product.getName());
        log.debug("Product details: {}", product);
    }
}
LoggerFactory.getLogger(ProductService.class) creates a logger instance for the class

private static final → shared, constant, class-level

Logging Configuration (log4j2 / Spring Boot)
properties
Copy code
# Set global log level
logging.level.root=INFO

# Enable DEBUG logs globally
logging.level.root=DEBUG
Example Entity with Annotations
java
Copy code
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "price", schema = "service_prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_price_generator")
    @GenericGenerator(
        name = "sequence_price_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "service_prices.price_id_seq"),
            @Parameter(name = "initial_value", value = "1000"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    private Long id;

    @Column(name = "book_id", nullable = false)
    private long bookId;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal price;
}
