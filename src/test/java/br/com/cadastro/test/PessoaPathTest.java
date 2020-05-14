package br.com.cadastro.test;

import br.com.cadastro.persistence.model.Pessoa;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class PessoaPathTest extends FuntionalTest {
    @Test
    public void basicPingTest(){
        given().when().get("pessoa/oi").then().statusCode(200);
    }

    @Test
    public void invalidNameShouldReturnBadRequest(){
        given().when().get("pessoa/buscar/teste").then().statusCode(400);
    }

    @Test
    public void verifyName(){
        given().when().get("pessoa/buscar/Igor").then().body(containsString("Igor"));
        given().when().get("pessoa/buscar/Igor").then().body("nome",equalTo("Igor")).statusCode(200);
    }

    @Test
    public void verifySave(){
        Pessoa p = new Pessoa("Teste");
        given().contentType("application/json").body(p).when().post("pessoa/salvar").then().statusCode(200);
    }
}
