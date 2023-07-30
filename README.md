# Roles API

## Summary

This project is a Java SpringBoot microservice that spins up a REST API, called Roles API, to improve upon the
capabilities of the Users API and the Teams API.

# How to run

## Docker

**Requirements**

- docker
- docker-compose

The solution can be executed with Docker and docker-compose. Since the build process needs to download all Maven
dependencies it can take some time to start.

```shell
docker-compose up --build
```

This will execute tests, build the jar, build the docker image and start the container. The first execution will take
longer since all maven dependencies will be downloaded. But next executions will run significantly faster as
dependencies are downloaded in a separate layer, thus cached. The `--build` triggers the Docker image build if there is
any change in the source code.

## Docker

**Requirements**

- docker
- docker-compose

The solution can be executed with Docker and docker-compose. Since the build process needs to download all Maven
dependencies it can take some time to start.

```shell
docker-compose -f docker-compose.dev.yml up -d
```

This will create a container that will be your devlopment enviroment. To access this container, run:

```shell
docker exec -it roles_api_dev bash
```

Inside this container, you can perform all Maven and Java commands that is gonna be needed. Along that, this docker-compose have
a Sonarqube enviroment, that will can you to check code smells, possibly bugs and other stats from the Sonarqube analysis. To
access the Sonarqube, just opne your browser in `http://localhost:9000` and follow the documentation steps.

## Build from source

**Requirements**

- maven
- java 11

To `build`, `test`, `package`, `report` and `run` execute the command:

```shell
mvn clean package verify spring-boot:run
```

To check the coverage open `target/site/jacoco/index.html`


# How to develop

## Spotless

To check for any code style issue

```shell
mvn spotless:check
```

To apply the fixes for code style issues

```shell
mvn spotless:apply
```
