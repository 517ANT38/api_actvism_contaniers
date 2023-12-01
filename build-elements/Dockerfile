FROM maven:3.8.4-openjdk-17 as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY build-docker/run.sh run.sh

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM eclipse-temurin:17-jre-alpine

VOLUME /tmp
ARG WORKSPACE=/workspace/app
ARG DEPENDENCY=/workspace/app/target/dependency
ARG APPUSER=demo
ARG APPGROUP=demo
ENV APPLICATIONCLASS=com.charity.activism.ActivismAndCharityApplication


RUN addgroup -S \${APPGROUP} && adduser -S \${APPUSER} -G \${APPGROUP}
USER \${APPUSER}

COPY --from=build --chmod=0755 \${WORKSPACE}/run.sh /run.sh
COPY --from=build \${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build \${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build \${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["/run.sh"]