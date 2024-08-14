FROM openjdk:21
EXPOSE 8081
ADD /target/ITsupportApp.jar iTSupport.jar
ENTRYPOINT ["java","-jar","/iTSupport.jar"]