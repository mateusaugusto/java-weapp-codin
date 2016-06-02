# How to compile
gradle build

# How to run
gradle bootRun

# How to run the automatted test suite
gradle test

## Better way to run and test -> Download The Spring Tool Suite or Gradle plugin

How to test the application

## Better way to test is using Swagger: Swagger:  is a project used to describe and document RESTful APIs.
http://localhost:8085/swagger-ui.html

OR

1: list product catalog -----------  http://localhost:8085/product
2:- read a single product by its id  ------- http://localhost:8085/product/1 
# Where 1 is the ID product

3:place an order ---   http://localhost:8085/order/
Sample JSON Add request for Order:

{
"order" : "New 1",
"products" : [
                 {"id" : "3"}, 
                 {"id" : "1"}
             ]
}

4:modify an order ---   http://localhost:8085/order/1
# Where 1 is order ID
Sample JSON Add request for Order:

{
"order" : "New Modify",
"products" : [
                 {"id" : "3"}, 
                 {"id" : "1"}
             ]
}

5:- list placed orders --- http://localhost:8085/order/ 
6:- read an existing order by its id  ---   http://localhost:8085/order/8
# Where 8 id id order

#### COMMENTS ###

As you can see I developed an restfull web service using Spring Boot, I've been working with spring boot a while,
so I feel more confident using it in the short run.


################



