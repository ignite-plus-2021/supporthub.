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





