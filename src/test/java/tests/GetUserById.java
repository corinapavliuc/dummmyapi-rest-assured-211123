package tests;

import dto.InvalidUser;
import dto.UserData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tests.BaseTest.getRequest;

public class GetUserById {
    @Test
    public void validUserDataTest(){
        //668b9d8e8a76161d21caa3c0
        //get user by id, log all from response
        String requestedId = "668bcd688a7616dbd1cab34a";
//        UserData user=given().baseUri("https://dummyapi.io/data/v1/")
//                .header("app-id","6380c63b2e6f5682c64dd368")
//                .when().log().all()
//                .get("/user/668bb17e8a761682c4caaba2")
//                .then().log().all().statusCode(200)
//                .extract().body().jsonPath().getObject("", UserData.class);
//        System.out.println(user.getId());

        Response response =getRequest("user/"+ requestedId,200);
        UserData user =response.body().jsonPath().getObject("",UserData.class);
        System.out.println(user.getId());

        //extract data from responde json to instrace of UserData class
        //Check that all fields are NOT emptyassertFalse(user.getId().isEmpty());
//        assertFalse(user.getEmail().isEmpty());
//        assertFalse(user.getFirstName().isEmpty());
//        assertFalse(user.getLastName().isEmpty());
//        assertFalse(user.getRegisterDate().isEmpty());
//        assertFalse(user.getUpdatedDate().isEmpty());
        //Check that id value from response is matching to id from endpoint
        //Check that registerDate is the same with updatedDate
    }
    @Test
    public void idFromResponseAndRequestTest() {
        String requestedId = "668b9e03de200ca4507b7227";

//        UserData user =
//                given().baseUri("https://dummyapi.io/data/v1")
//                        .header("app-id", "65faa96105388e5be212542e")
//                        .when().log().all()
//                        .get("/user/" + requestedId)
//                        .then().log().all().statusCode(200).extract().body().jsonPath().getObject("", UserData.class);
        Response response = getRequest("user/" + requestedId, 400);
        InvalidUser error  =  response.body().jsonPath().getObject("",InvalidUser.class);

        assertEquals("PARAMS_NOT_VALID", error.getError());

    }
    @Test
    public void invalidUserTest(){
        //Check that error message with text "PARAMS_NOT_VALID" is displayed
        String requestedId = "sfghjk";
        InvalidUser error =  given().baseUri("https://dummyapi.io/data/v1/")
                .header("app-id", "6380c63b2e6f5682c64dd368")
                .when().log().all()
                .get("/user/" + requestedId)
                .then().log().all().statusCode(400)
                .extract().body().jsonPath().getObject("",InvalidUser.class);
        assertEquals("PARAMS_NOT_VALID", error.getError());
    }
}
