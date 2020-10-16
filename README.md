# Coffee and ELK

Spring Boot REST API with Elasticsearch.

#### Referencies

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

https://www.elastic.co/guide/en/kibana/current/install.html

https://start.spring.io/

https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html

https://www.udemy.com/course/practical-java-spring-boot-rest-api-with-elasticsearch/



####  Dependencies

1. [Gradle Project](https://gradle.org/)
2. [Oracle JDK-11.0.8](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
3. [Spring Boot 2.3.4](https://start.spring.io/)
4. [Elasticsearch 7.6.2](https://www.elastic.co/pt/downloads/past-releases/elasticsearch-7-6-2)
5. [Kibana 7.6.2](https://www.elastic.co/pt/downloads/past-releases/kibana-7-6-2)
6. [Eclipse IDE](https://www.eclipse.org/downloads/)
7. [Postman](https://www.postman.com/)


#### Why Java Spring Boot?

1. Easy dependency injection;
2. Automation of configuration with annotations;
3. Spring IOC (Inversion of Control) manages dependencies;
4. We can use @Annotations right before the method to be called (@RequestMapping, @GetMapping, @PostMapping) etc.
5. Spring Boot DevTools monitors src/main/java, src/main/resources and src/main/tests to automatically restart applications;
6. Spring use logback framework - src/main/resources/logback-spring.xml; Appender - where to output logs, Logger: what to log; We can send our logs to files (FileAppender) and console (ConsoleAppender);
7. There are 5 levels of logs: log.trace(), log.debug(), log.info(), log.warn() & log.error()

      **Not sent to appender**: log.trace(), log.debug() & log.info()
      
      **Sent to appender**: log.warn() & log.error()
      
8. Spring Boot already has **Jackson** to serialize/marshall Plain Old Java Objects (POJO) to JSON; POJO are objects from classes that has no business logic - only method for setters, getters, toString, equals, hashCode; Gradle already handles Jackson dependency; For converting POJO into JSON, Jackson will call GETTERS; For converting JSON to POJO, Jackson will call SETTERS;
9. To convert JSON to POJO, the class must be public; The class must have no-arguments constructor that are automatically created by Java with ALT+SHIFT+S; You can't define any other constructor for the serialization with Jackson to work;
10. **O POJO sempre deve ter um no-argument constructor para que possa ser realizado o unmarshalling no método HTTP POST**
11. @JsonInclude may stablish someconditions for the field to be exposed in the response API; **Show attribute conditionally**. Example: show attribute only if is not empty or not null.      
12. @JsonIgnore hide attribute in JSON, non conditionally;
13. @JsonIgnoreProperties is another way of ignoring properties; You can type name of attributes you want to ignore on marshalling; This is a class level annotation, so it should be written before the class.
14. @JsonUnwrapped: generate child objects attributes as its parents. @JsonUnwrapped makes attributes from nested fields become attriutes of the parent class; It is simples with example with "engine" and "car"; Engine attributes will no longer be under the umbrella of engine object and it will be shown regularly in the response API; After @JsonUnwrapped, "fuelType" and "horsePower" is part of car object in JSON;
15. In application.yml, you can set jackson.property-naming-strategy to SNAKE_CASE and all the fields in the response will be in snake_case format;

#### Why Gradle?

1. Gradle is a dependency & libraries manager; Compiles computer source code into binary code;
2. Create .jar packages to deploy in production - compile, configure metadata and build package;
3. Plug-ins are added in build.gradle; In our case we have plug-ins Ofor java, spring and eclipse (IDE used to develop);
4. Automatized atualization when importing new dependencies;
5. All Spring Boot libraries will have the same version determined in build.gradle;

#### Application Deployment

1. Create executable .jar: make a distributable binary file that can be easily run with java -jar;
2. From Eclipse: Windows -> Show View -> Other .... Select 'Gradle Task'. Go to build and than select 'bootJar'
3. The executable jar will be in <path>/build/libs
4. Spring Boot Configurations for deployment: application.properties or application.yml (depends on your preference for readability). By default, runs a Apache Tomcat server   on port 8080. We can change the port in application.yml with server.port:8001, per example; See more: https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

#### Spring Data Elasticsearch

1. Spring provides automatic handler for pagination;
2. Add 'org.springframework.boot:spring-boot-starter-data-elasticsearch' on build.gradle; Refresh Gradle Project;
3. The class created as "ElasticsearchConfiguration" needs to have a @Configuration annotation from Spring Boot Framework; The class needs to extend "AbstractElasticSearchConfiguration";
4. The most basic configuration is Elasticsearch Host and Port;
5. By default, Elasticsearch will map java date as Elasticsearch long millisecond;
6. Using @Field, @Repository annotations for Elasticseach SDK in Java;
7. ElasticsearchRepository should be extended by a class;
8. In our example, CarElasticRepository.java will do the saving into Elasticsearch;
9. Spring Data Elasticsearch will generate and execute proper API call to Elasticsearch;
10. Spring Data interfaces have basic operations like save, delete, update, find all data etc. without the need to write statements and bind to variables;
11. @Document annotation should be in the POJO that will be stored in Elasticsearch -> @Document(indexName = "<index-in-ES>");
12. You can add methods to the Repository interface that can automatically run queries based on the name of the method - e.g findByBrandAndColor. You just need to make sure the method is written in the right way; This is called as **Spring Query Creation**. Spring has many keywords to accomplish this. Click [here](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation) for more information.
13. Query methods are methods that are declared in the repository interface and usually used to read data. We don't need to define method body, Spring Data will work without it;

Code block to generate connection with Elasticsearch.

~~~java

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

	@Override
	public RestHighLevelClient elasticsearchClient() {
		final var clientConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200").build();
		return RestClients.create(clientConfiguration).rest();
	}	

}

~~~

Interface to perform CRUD operations in Elasticsearch.

~~~java

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<<POJO>, <Type of the identifier of the document, usually String>> {	
	

}

~~~


We put up 03 nodes of Elasticsearch. Remember they are running in this URLs:

Elasticsearch 7.6.2 Node 01: http://localhost:9200

Elasticsearch 7.6.2 Node 02: http://localhost:9201

Elasticsearch 7.6.2 Node 03: http://localhost:9202

Kibana 7.6.2: http://localhost:5601

In [Kibana Console Tool](http://localhost:5601/app/kibana#/dev_tools/console), you can check the allocation of documents in primary and replica shards using this HTTP requests.

~~~http

GET /_cluster/health

GET /_cat/shards?v

GET /_cat/allocation?v

GET /_cat/nodes?v

GET /_cat/indices?v

~~~

You have other other queries in [Postman Collection](https://web.postman.co/collections/13038513-c9864601-f6fc-4c69-8ce1-db84ae70f9e0?version=latest&workspace=31ccf69f-8901-4b23-83f1-0114705f100e) and you can [import the json file](https://github.com/LucasLimaPinho/coffeeandelk/blob/master/CoffeAndElk.postman_collection.json) to your environment.

Elasticsearch can be queried with **path parameters**. Here is a code block using Spring Boot framework:

~~~java

@GetMapping(value = "/cars/{brand}/{color}")
	public List<Car> findCarByPath(@PathVariable("brand") String brand, @PathVariable("color") String color){
		
	return carElasticRepository.findByBrandAndColor(brand, color);
	
	}
}

~~~

Elasticsearch can be queried with **query parameters**. Here is a code block using Spring Boot framework:

~~~java

@GetMapping(value = "/cars")
	public List<Car> findCarByParams(@RequestParam String brand, @RequestParam String color){
		
	return carElasticRepository.findByBrandAndColor(brand, color);
		
	}
}

~~~

#### Exception Handling and Customizing HTTP Response

1. ResponseEntity.class in Spring Boot framework help us provide the correct HTTP status code for the client; That includes Status Code, Headers & Body; ResponseEntity<Type of Return Class>;
2. In our case, the ResponseEntity.Body can be of type "List<Car>" or a new class "ErrorResponse". We will use generic <Object>;
3. ResponseEntity has a lot of constructors, but we can basically use the one with this arguments: "body", "http header" & "http status code"; In case we have a good request from the client, per example, we can return ResponseBody.ok().headers(<HttpHeaders>).body(List<Car>);
	
Code Block for HTTP Response Customization with Pagination:

~~~java

@GetMapping(value = "/cars/{brand}/{color}")
	public ResponseEntity<Object> findCarByPath(@PathVariable("brand") String brand,
		@PathVariable("color") String color, @RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size) {

		var headers = new HttpHeaders();
		headers.add(HttpHeaders.SERVER, "Spring");
		headers.add("X-Custom-Header", "Custom Response Header");
		
		if (StringUtils.isNumeric(color)) {
			
			var errorResponse = new ErrorResponse("Invalid color : " + color, LocalDateTime.now());
			
			return new ResponseEntity<Object>(errorResponse, headers, HttpStatus.BAD_REQUEST);
		}		

		var pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "price"));
		
		var cars = carElasticRepository.findByBrandAndColor(brand, color, pageable).getContent();
		
		return ResponseEntity.ok().headers(headers).body(cars);
	}

~~~
 
 
 4. Spring provides good mechanism to handle exceptions in the RESTAPIController with annotation @ExceptionHandler; Utilizing @ExceptionHandler annotation we have a method to deal with all the type of exceptions thrown in the RESTAPIController - if we provide Exception.class as parameter for the annotation, it will be literally all exceptions since Exception.class is parent of all other exceptions class;
 
 Code Block - method for Exception handling:
 
 ~~~java
 
 @ExceptionHandler(value = IllegalArgumentException.class)
private ResponseEntity<ErrorResponse> handleInvalidColorException(IllegalArgumentException e) {

	var message = "Exception: " + e.getMessage();
	LOG.warn(message);
	var errorResponse = new ErrorResponse(message, LocalDateTime.now());
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

}

~~~
