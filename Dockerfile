FROM openjdk:17-oracle as build
MAINTAINER mspoc.com
COPY target/shoppingcart-0.0.1-SNAPSHOT.jar shoppingcart-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/shoppingcart-0.0.1-SNAPSHOT.jar"]
