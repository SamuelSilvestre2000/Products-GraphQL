# Usar uma imagem base oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Argumento para especificar o diretório do build
ARG JAR_FILE=target/*.jar

# Copiar o arquivo jar para o contêiner
COPY ${JAR_FILE} app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
