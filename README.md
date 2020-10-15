# Spring Boot REST API with Elasticsearch

#### Referencies

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

https://www.elastic.co/guide/en/kibana/current/install.html

https://start.spring.io/

https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html



####  Dependencies

1. Gradle Project
2. Oracle JDK-11.0.8
3. Spring Boot 2.3.4


#### Why Java Spring Boot?

1. Easy dependency injection;
2. Automation of configuration with annotations;
3. Spring IOC (Inversion of Control) manages dependencies;
4. We can use @Annotations right before the method to be called (@RequestMapping, @GetMapping, @PostMapping) etc.
5. Spring Boot DevTools monitors src/main/java, src/main/resources and src/main/tests to automatically restart applications;
6. Spring use logback framework - src/main/resources/logback-spring.xml; Appender - where to output logs, Logger: what to log; We can send our logs to files (FileAppender) and console (ConsoleAppender);
7. There are 5 levels of logs: log.trace(), log.debug(), log.info(), log.warn() & log.error()

      **Not sent to appender**: log.trace(), log.debug() & longo & info
      **Sent to appender**: log.warn() & log.error()
     

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





