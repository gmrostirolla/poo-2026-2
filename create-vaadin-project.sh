#!/usr/bin/env bash

set -euo pipefail

if [ "$#" -lt 2 ]; then
  echo "Uso: $0 <groupId> <artifactId>"
  echo "Exemplo: $0 com.exemplo vaadin-app"
  exit 1
fi

GROUP_ID="$1"
ARTIFACT_ID="$2"

JAVA_VERSION="25"
SPRING_BOOT_VERSION="4.1.0"
VAADIN_VERSION="25.2.1"

PACKAGE_DIR="${GROUP_ID//./\/}"
BASE_DIR="$ARTIFACT_ID"

SRC_MAIN_JAVA="$BASE_DIR/src/main/java/$PACKAGE_DIR"
SRC_TEST_JAVA="$BASE_DIR/src/test/java/$PACKAGE_DIR"
SRC_MAIN_RESOURCES="$BASE_DIR/src/main/resources"

echo "Criando projeto Vaadin + Spring Boot '$ARTIFACT_ID'..."
echo "Group ID:     $GROUP_ID"
echo "Java:        $JAVA_VERSION"
echo "Spring Boot: $SPRING_BOOT_VERSION"
echo "Vaadin:      $VAADIN_VERSION"

if [ -e "$BASE_DIR" ]; then
  echo "Erro: o diretório '$BASE_DIR' já existe."
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
  <packaging>jar</packaging>

  <name>$ARTIFACT_ID</name>
  <description>Projeto Vaadin com Spring Boot</description>

  <properties>
    <java.version>$JAVA_VERSION</java.version>
    <vaadin.version>$VAADIN_VERSION</vaadin.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>com.vaadin</groupId>
        <artifactId>vaadin-bom</artifactId>
        <version>\${vaadin.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>

    <dependency>
      <groupId>com.vaadin</groupId>
      <artifactId>vaadin-spring-boot-starter</artifactId>
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

    <dependency>
      <groupId>com.vaadin</groupId>
      <artifactId>vaadin-dev</artifactId>
      <optional>true</optional>
    </dependency>

  </dependencies>

  <build>
    <plugins>

      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>

      <plugin>
        <groupId>com.vaadin</groupId>
        <artifactId>vaadin-maven-plugin</artifactId>
        <version>\${vaadin.version}</version>
        <executions>
          <execution>
            <goals>
              <goal>prepare-frontend</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

    </plugins>
  </build>

  <profiles>

    <profile>
      <id>production</id>

      <dependencies>
        <dependency>
          <groupId>com.vaadin</groupId>
          <artifactId>vaadin-core</artifactId>
          <exclusions>
            <exclusion>
              <groupId>com.vaadin</groupId>
              <artifactId>vaadin-dev</artifactId>
            </exclusion>
          </exclusions>
        </dependency>
      </dependencies>

      <build>
        <plugins>
          <plugin>
            <groupId>com.vaadin</groupId>
            <artifactId>vaadin-maven-plugin</artifactId>
            <version>\${vaadin.version}</version>
            <executions>
              <execution>
                <goals>
                  <goal>build-frontend</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </build>

    </profile>

  </profiles>

</project>
EOF

cat > "$SRC_MAIN_JAVA/App.java" <<EOF
package $GROUP_ID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
EOF

cat > "$SRC_MAIN_JAVA/MainView.java" <<EOF
package $GROUP_ID;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 titulo = new H1("$ARTIFACT_ID");
        titulo.addClassName(LumoUtility.Margin.NONE);

        Button botao = new Button(
            "Clique aqui",
            evento -> Notification.show(
                "Olá, mundo!",
                3000,
                Notification.Position.TOP_CENTER
            )
        );

        add(titulo, botao);
    }
}
EOF

cat > "$SRC_TEST_JAVA/AppTest.java" <<EOF
package $GROUP_ID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void aplicacaoPodeSerInstanciada() {
        App app = new App();

        assertNotNull(app);
    }
}
EOF

cat > "$SRC_MAIN_RESOURCES/application.properties" <<EOF
spring.application.name=$ARTIFACT_ID

server.port=8080

vaadin.launch-browser=true
EOF

cat > "$BASE_DIR/README.md" <<EOF
# $ARTIFACT_ID

Projeto criado automaticamente com:

- Java $JAVA_VERSION
- Spring Boot $SPRING_BOOT_VERSION
- Vaadin $VAADIN_VERSION
- Maven

## Executar em modo de desenvolvimento

\`\`\`bash
mvn spring-boot:run
\`\`\`

Depois acesse:

\`\`\`text
http://localhost:8080
\`\`\`

## Executar os testes

\`\`\`bash
mvn test
\`\`\`

## Gerar pacote de desenvolvimento

\`\`\`bash
mvn clean package
\`\`\`

## Gerar pacote de produção

\`\`\`bash
mvn clean package -Pproduction
\`\`\`

O arquivo gerado estará em:

\`\`\`text
target/$ARTIFACT_ID-1.0-SNAPSHOT.jar
\`\`\`

## Executar o JAR

\`\`\`bash
java -jar target/$ARTIFACT_ID-1.0-SNAPSHOT.jar
\`\`\`
EOF

echo
echo "Projeto '$ARTIFACT_ID' criado com sucesso."
echo
echo "Para executar:"
echo "  cd '$ARTIFACT_ID'"
echo "  mvn spring-boot:run"
echo
echo "Acesse: http://localhost:8080"
