FROM maven:3-openjdk-17 as build-stage

WORKDIR /usr/src/mymaven

COPY bin .

RUN mvn clean install compile package

FROM registry.access.redhat.com/ubi8/openjdk-11:1.11

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=build-stage --chown=185 /usr/src/mymaven/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build-stage --chown=185 /usr/src/mymaven/target/quarkus-app/*.jar /deployments/
COPY --from=build-stage --chown=185 /usr/src/mymaven/target/quarkus-app/app/ /deployments/app/
COPY --from=build-stage --chown=185 /usr/src/mymaven/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV AB_JOLOKIA_OFF=""
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"