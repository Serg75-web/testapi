package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class MobileBankApiTestV4 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                // Включаем логирование ответа
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
                .body("[0].id", equalTo(1))
                .body("", hasSize(3))
                .body("[0].name", equalTo("Текущий счёт"))
                .body("[0].currency", equalTo("RUB"));
    }
}
