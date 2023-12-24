# Getting Started

# Application Setup - Creating Java SpringBoot Native Image
The work is to demonstrate to create native image to run in the docker container using SpringAOT + GraalVM Native + SpringBoot 2.x. The build process will use the apporach to create SpringAOT JAR file to Native Image executable.

## Environment
The appstack selected here is to support the CRNK Framework that is compliant of SpringBoot 2 version only.
The app will work only with this version (2.7.18 will not work out of the box)
- Windows - WSL2 Ubuntu
- Java 17
- Spring Boot 2.7.7
- Spring AOT 0.12.2
- Docker Image
  - Maven : maven:3.9.4-eclipse-temurin-8-alpine
  - jdk17, native image: container-registry.oracle.com/graalvm/native-image:17
  - OS (arm64): container-registry.oracle.com/os/oraclelinux:9-slim

The above appstack is working with CrnkBoot2 (SpringBoot2 compliant auto configuration component).
Output of native image size is 55mb.

The Spring-AOT libraries are included to support the Spring Native Build Maven plugin that helps to generate the native related source data to build.
The GraalVM Native Maven plugin will use the Spring executable Jar to Native image.

To manage the reflection related and other initialization required for the build, follow the below steps
##### Step 1: Generate the AOT files
```
mvn clean spring-aot:generate .
```
##### Step 2: Copy AOT files
The native configuration files are be generated in the below location:
 - native-image.properties : Copy the entry to pom.xml for the native profile as the build argument to native image
 - proxy-config.json: update the config with any new auto-config to be added
 - reflect-config.json: update the config for all reflection using components. mostly json related components will go here.
 - resource-config.json: most of them are already covered. add any new config resource files

Move the generated files to the below location. Create necessary folders as required.
```
cp target/generated-runtime-sources/spring-aot/src/main/resources/META-INF/native-image/org.springframework.aot/spring-aot /src/main/resources/META-INF/native-image/
```
##### Step 3: Build the application
A containerized application to build the native image and run the executable directly.
```
docker build -t crnk-api-service:1.0 --progress=plain --no-cache .
```
##### Step 4: Run the application
CPU/Memory is allocated (CPU - 200M, Memory-200)
```
docker run -it -d -p 8080:8080 --cpu-shares=20000 --memory=200M docker.io/library/crnk-api-service:1.0
```

### Troubleshooting
The native application may not run as expected if migrating from regular to native. 
There are options available to debug the app to identify the issue behind "ClassNotFound" and other objects not available at runtime.
Options: 
- Use GBD
- Use Visual Source Extenions
  
Without docker, the below commands can be executed to build the native image to run the app locally.
```
mvn -ntp -Pnative clean package
```
Run the app
```
./crnk-api-service
```
The VS Code Lauch config is located at 
```
crnk-api-service/.vscode/launch.json
```


## Docker Image Details
- [GraalVM, JDK](https://container-registry.oracle.com)
- [For Maven](https://hub.docker.com/layers/library/maven/3.9.4-eclipse-temurin-8-alpine/images/sha256-b416a20af00bce4a75db7ca2658b8204deb662b0c5db7930b807491697a0b733?context=explore)
> For the sake of simple seutp, this image is used. it has both jdk11 and Maven 3.6. Installing maven directly will have to install required download manager and other support libraries.
> For the GraalVM native image 17, oracle-linux 9 version is supported.

## Visual Source Code
The application is developed in the Windows machine having set WSL2 and installed the Visual Source Code to work on the project code directly.
GraalVM Native Image tools VS Code extensions are used to debug the application

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.6/maven-plugin/reference/html/#build-image)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/docs/3.1.6/reference/html/native-image.html#native-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.6/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/docs/3.1.6/maven-plugin/reference/htmlsingle/#aot)

## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 demo:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/demo
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```

