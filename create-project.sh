#!/usr/bin/env bash

set -euo pipefail

if [ "$#" -lt 2 ]; then
  echo "Uso: $0 <groupId> <artifactId>"
  echo "Exemplo: $0 com.exemplo meu-projeto"
  exit 1
fi

GROUP_ID="$1"
ARTIFACT_ID="$2"

echo "Criando projeto Maven '$ARTIFACT_ID' com groupId '$GROUP_ID' e Java 25..."

BASE_DIR="$ARTIFACT_ID"
PACKAGE_DIR="$(echo "$GROUP_ID" | tr '.' '/')"

SRC_MAIN_JAVA="$BASE_DIR/src/main/java/$PACKAGE_DIR"
SRC_TEST_JAVA="$BASE_DIR/src/test/java/$PACKAGE_DIR"

if [ -e "$BASE_DIR" ]; then
  echo "Erro: o diretório '$BASE_DIR' já existe."
  exit 1
fi

mkdir -p "$SRC_MAIN_JAVA"
mkdir -p "$SRC_TEST_JAVA"

cat > "$BASE_DIR/pom.xml" <<EOF
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">

  <modelVersion>4.0.0</modelVersion>

  <groupId>$GROUP_ID</groupId>
  <artifactId>$ARTIFACT_ID</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.release>25</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <!-- JUnit Jupiter para testes unitários -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Compilação com Java 25 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <release>25</release>
        </configuration>
      </plugin>

      <!-- Execução dos testes JUnit -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.1.2</version>
        <configuration>
          <useModulePath>false</useModulePath>
        </configuration>
      </plugin>

      <!-- Execução da classe principal -->
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.1.0</version>
      </plugin>
    </plugins>
  </build>

</project>
EOF

cat > "$SRC_MAIN_JAVA/App.java" <<EOF
package $GROUP_ID;

public class App {

    public static void main(String[] args) {
        System.out.println(
            "Hello, world! Projeto $ARTIFACT_ID criado com sucesso."
        );
    }
}
EOF

cat > "$SRC_TEST_JAVA/AppTest.java" <<EOF
package $GROUP_ID;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testApp() {
        assertTrue(true, "Teste básico de funcionamento.");
    }
}
EOF

cat > "$BASE_DIR/README.md" <<EOF
# $ARTIFACT_ID

Projeto Maven criado automaticamente com Java 25 e JUnit 5.

## Requisitos

- Java 25
- Maven

## Como compilar

\`\`\`bash
mvn compile
\`\`\`

## Como executar

\`\`\`bash
mvn exec:java -Dexec.mainClass="$GROUP_ID.App"
\`\`\`

## Como executar os testes

\`\`\`bash
mvn test
\`\`\`

## Como gerar o pacote

\`\`\`bash
mvn package
\`\`\`
EOF

echo
echo "Projeto '$ARTIFACT_ID' criado com sucesso."
echo "Java: 25"
echo
echo "Para testar:"
echo "  cd '$ARTIFACT_ID'"
echo "  mvn test"
echo "  mvn exec:java -Dexec.mainClass=\"$GROUP_ID.App\""
