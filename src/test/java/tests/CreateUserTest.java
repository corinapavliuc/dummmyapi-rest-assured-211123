package tests;

import com.github.javafaker.Faker;
import dto.CreateUserRequest;
import dto.UserDataFull;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.BaseTest.postRequest;

public class CreateUserTest {
    Faker faker = new Faker();

    @Test
    public void successCreateUserRequiredFields() {
        String userEmail = faker.internet().emailAddress();
        //first name , last name with faker
        String userFirstName = faker.name().firstName();
        String userLastName = faker.name().lastName();
        CreateUserRequest requestBody = new CreateUserRequest("John", "Black", "asex@gmail.com");
        Response response = postRequest("/user/create", 200, requestBody);
        //Check that first name form response equals to name from request

        String firstNameFromResp = response.body().jsonPath().getString("firstName");
        assertEquals("John", firstNameFromResp);
        String lastNameFromResp = response.body().jsonPath().getString("lastName");
        String emailFromResp = response.body().jsonPath().getString("email");
        assertEquals(userFirstName, firstNameFromResp);
        assertEquals(userLastName, lastNameFromResp);
        assertEquals(userEmail, emailFromResp);
    }

        @Test
        public void successCreateUserAdditionalFields () {
//gender
//tittle
//phone
//fn
//ln
//email
            String userEmail = faker.internet().emailAddress();
            String userFirstName = faker.name().firstName();
            String userLastName  = faker.name().lastName();
            String gender = "male";
            String tittle = "mr";
            String phone ="+49123456";

           CreateUserRequest requestBody = new CreateUserRequest (userFirstName, userLastName, userEmail,gen
Response response = postRequest( "/user/create", 200, requestBody);

//Check that first name form response equals to name from request
String firstNameFromResp =response.body().jsonPath().getString( "firstName");
 String lastNameFromResp = response.body().jsonPath().getString( "LastName");
 String emailFromResp = response.body().jsonPath().getString( "email");
 String genderFromResp = response.body().jsonPath().getString(  "gender");
 String tittleFromResp = response.body().jsonPath().getString( "tittle");
String phoneFromResp = response.body().jsonPath().getString( "phone");
// assertEquals(userFirstName, firstNameFromResp);
UserDataFull userData = response.body().jsonPath().getObject("", UserDataFull.class);
            assertEquals(userFirstName, firstNameFromResp);
            assertEquals(userLastName, lastNameFromResp);
            assertEquals(userEmail, emailFromResp);
            assertEquals(gender, genderFromResp);
            assertEquals(gender, tittleFromResp);
            assertEquals(gender, phoneFromResp);
        }
    @Test

    public void withoutEmail(){
        String userFirstName = faker.name().firstName();
        String userLastName = faker.name().lastName();
        //first name, last name
        //status code is 400
        //Path `email` is required.
        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName(userFirstName)
                .lastName(userLastName)
                .build();
        Response response = postRequest("/user/create", 400, requestBody);



    }
    }
