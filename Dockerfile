FROM openjdk:17
EXPOSE 8080
ADD target/todo-project-manager.jar todo-project-manager.jar
ENTRYPOINT ["java", "-jar", "/todo-project-manager.jar"]