# genrique-crud-spring-boot
create a post and react and comment with genrique way and elemniting the biloparte code 
first step you need to create a generique repository and add @NoRepository to tell spring boot to not create a bean for this repository
create an genrique interface for the service
create an genrique abstract class for the service that implement the pervious interface and do not add @Service annoted
create an genrique abstract controller and do not add @Controller annotation
create your corrspending beans and make them extends the genrique class and interfaces @add the corrspending annotation(@Repository,@Service,@Controller,@RestController)
