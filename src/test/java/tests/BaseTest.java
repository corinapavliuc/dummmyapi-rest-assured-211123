package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class BaseTest {
     final static String BASE_URI = "https://dummyapi.io/data/v1";

     final static String APP_ID_VALUE ="6380c63b2e6f5682c64dd368";

     static RequestSpecification  specification = new RequestSpecBuilder()
             .setBaseUri(BASE_URI)
             .addHeader("app-id","APP_ID_VALUE")
             .setContentType(ContentType.JSON)
             .build();

     public static Response getRequest(String endpoint,Integer expectedStatusCode){
         Response response= given()
                 .spec(specification)
                 .when()
                 .log().all()
                 .get(endpoint)
                 .then()
                 .log().all()
                 .statusCode(expectedStatusCode)
                 .extract().response();
         return response;

     }
    public static Response postRequest(String endpoint, Integer expectedStatusCode, Object body){

        Response response = given()

                .spec(specification)

                .body(body)

                .when()

                .log().all()

                .post(endpoint)

                .then()

                .log().all()

                .statusCode(expectedStatusCode)

                .extract().response();

        return response;

    }
     }

