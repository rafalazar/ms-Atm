FROM openjdk:8
VOLUME /tmp
EXPOSE 8106
ADD target/service-Atm-0.0.1-SNAPSHOT.jar msatm.jar
ENTRYPOINT ["java","-jar","msatm.jar"]