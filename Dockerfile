
# Stage 1: Build application
FROM  maven:3.9.4-eclipse-temurin-8-alpine AS builder

# RUN mvn -version

WORKDIR /buildmvn
COPY . /buildmvn

RUN cp -r /usr/share/maven /buildmvn/maven

# Stage: 2
FROM container-registry.oracle.com/graalvm/native-image:17 AS nativejdk

WORKDIR /buildjdk
COPY . /buildjdk

RUN mkdir /usr/share/maven

COPY --from=builder /buildmvn/maven /usr/share/maven

# RUN ls -lta /usr/share/maven/
# RUN ls -lta /usr/share/maven/bin/

# RUN java -version
# RUN echo $PATH

ENV M2_HOME /usr/share/maven
RUN export PATH=$M2_HOME/bin:$PATH

# RUN $M2_HOME/bin/mvn -version

# RUN ls -lta

RUN $M2_HOME/bin/mvn -B -U -Djarmode=layertools -ntp -Pnative clean package

# Stage: 3
FROM container-registry.oracle.com/os/oraclelinux:9-slim AS baseimg

COPY --from=nativejdk /buildjdk/target/crnk-api-service app

EXPOSE 8080

ENTRYPOINT ["/app"]
