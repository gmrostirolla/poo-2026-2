#!/usr/bin/env bash

set -euo pipefail

if [ "$#" -lt 2 ]; then
    echo "Uso: $0 <groupId> <artifactId>"
    echo "Exemplo: $0 com.exemplo api-pessoas"
    exit 1
fi

GROUP_ID="$1"
ARTIFACT_ID="$2"

JAVA_VERSION="25"
SPRING_BOOT_VERSION="4.1.0"

PACKAGE_DIR="${GROUP_ID//./\/}"
BASE_DIR="$ARTIFACT_ID"

SRC_MAIN_JAVA="$BASE_DIR/src/main/java/$PACKAGE_DIR"
SRC_TEST_JAVA="$BASE_DIR/src/test/java/$PACKAGE_DIR"
SRC_MAIN_RESOURCES="$BASE_DIR/src/main/resources"

echo "Criando projeto Spring Boot..."

if [ -e "$BASE_DIR" ]; then
    echo "Erro: diretório '$BASE_DIR' já existe."
    exit 1
fi

mkdir -p "$SRC_MAIN_JAVA"
mkdir -p "$SRC_TEST_JAVA"
mkdir -p "$SRC_MAIN_RESOURCES"

cat > "$BASE_DIR/pom.xml" <<EOF
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
            http://maven.apache.org/POM/4.0.0
            https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>$SPRING_BOOT_VERSION</version>
        <relativePath/>
    </parent>

    <groupId>$GROUP_ID</groupId>
    <artifactId>$ARTIFACT_ID</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>$JAVA_VERSION</java.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>

        <plugins>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

        </plugins>

    </build>

</project>
EOF

cat > "$SRC_MAIN_JAVA/App.java" <<EOF
package $GROUP_ID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

}
EOF

cat > "$SRC_MAIN_JAVA/HelloController.java" <<EOF
package $GROUP_ID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

}
EOF

cat > "$SRC_TEST_JAVA/AppTest.java" <<EOF
package $GROUP_ID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testApp() {
        assertNotNull(new App());
    }

}
EOF

cat > "$SRC_MAIN_RESOURCES/application.properties" <<EOF
spring.application.name=$ARTIFACT_ID

server.port=8080
EOF

cat > "$BASE_DIR/README.md" <<EOF
# $ARTIFACT_ID

Projeto Spring Boot criado automaticamente.

## Requisitos

- Java $JAVA_VERSION
- Maven

## Executar

\`\`\`bash
mvn spring-boot:run
\`\`\`

Acesse:

http://localhost:8080

## Testes

\`\`\`bash
mvn test
\`\`\`

## Empacotar

\`\`\`bash
mvn clean package
\`\`\`

## Executar o JAR

\`\`\`bash
java -jar target/$ARTIFACT_ID-1.0-SNAPSHOT.jar
\`\`\`
EOF

echo
echo "Projeto criado com sucesso."
echo
echo "Para executar:"
echo "  cd $ARTIFACT_ID"
echo "  mvn spring-boot:run"
echo
echo "Abra:"
echo "  http://localhost:8080"
