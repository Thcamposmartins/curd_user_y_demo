#docker build -t oracle-express:21.3.0 .
#docker run -d -p 1521:1521 --name oracle-container oracle-express:21.3.0

#OPÇÃO APENAS DO BANCO
#FROM container-registry.oracle.com/database/express:21.3.0-xe

#ENV ORACLE_PWD=123456

#VOLUME /opt/oracle/oradata

#EXPOSE 1521

#CMD ["bash", "-c", "source /home/oracle/.bashrc; /bin/sh -c 'exec $ORACLE_BASE/$RUN_FILE $ORACLE_HOME &> /dev/stdout'"]
#FROM openjdk:21-jdk

#ARG JAR_FILE=build/libs/*.jar
#COPY ./build/libs/yami_demo-0.0.1-SNAPSHOT-plain.jar app.jar

#ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]

FROM openjdk:21-jdk

ARG JAR_FILE=build/libs/yami_demo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]