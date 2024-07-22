package tests;

import dto.UserDataFull;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tests.BaseTest.getRequest;

public class GetUserListTest {
    @Test
    public void getUserList() {
        //Request /user
        //getRequest("/user",200);
        //parse List of users
        List<UserDataFull> users = getRequest("/user", 200)
                .body().jsonPath().getList("data", UserDataFull.class);
        System.out.println(users.get(0).getId());
        //Check that 20 users are in list
        assertEquals(20, users.size());
        //Check that users quantity from list match with limit

        //kolicestvo iozara sovpadaet s kolicestvom vinz otveta
        Integer limitValue = response.body().jsonPath().getInt("limit");
        Integer pageValue = response.body().jsonPath().getInt("page");
        System.out.println(limitValue + " , " + pageValue);

        assertEquals(limitValue, users.size());

        //Check that id pf each user is not empty
        for (UserDataFull user : users) {
            assertFalse(user.getId().isEmpty());
        }

            //Check that all pictures in jpg format
            for (UserDataFull user : users) {
                assertTrue(user.getPicture().endsWith(".jpg"));
            }

    }
    @Test
    public void getUserListWithSpecLimit(){
        // Limit = 5
        int limit = 5;

        //check that 5 jsons in users list

        Response response = getRequest("/user?limit=" + limit, 200);
        List<UserDataFull> users = response
                .body().jsonPath().getList("data", UserDataFull.class);


        // check that 5 jsons in users list
        assertEquals(limit, users.size());
    }
    @Test//5->5, 23->23, 2->5, 78->50, -1->5
//ParametrizedTest
    public void getUserListLimitInvalidLess(){
        //limit < 5
        //Check that limit ==5
        int limit =4;
        int expectedLimit = 5;

        Response response = getRequest("/user?limit=" + limit, 200);
        List<UserDataFull> users = response
                .body().jsonPath().getList("data", UserDataFull.class);

        assertEquals(expectedLimit, users.size());

    }
    @Test
    public void getUserListLimitInvalidMore(){
        //limit > 50
        //Check that limit ==50
        int limit =51;
        int expectedLimit = 50;

        Response response = getRequest("/user?limit=" + limit, 200);
        List<UserDataFull> users = response
                .body().jsonPath().getList("data", UserDataFull.class);

        assertEquals(expectedLimit, users.size());


    }
    @ParameterizedTest

    @MethodSource("validData")

    public void getUserListLimitInvalidLess(int limit, int expectedLimit) {

        //5->5, 23->23, 2->5, 78->50, -1->5

        //ParametrizedTest

        //assertEquals(expectedLimit, limit);

        Response response = getRequest("/user?limit=" + limit, 200);

        List<UserDataFull> users = response

                .body().jsonPath().getList("data", UserDataFull.class);

        assertEquals(expectedLimit, users.size());

    }

    static Stream<Arguments> validData() {

        return Stream.of(

                arguments(5, 5),

                arguments(23, 23),

                arguments(2, 5),

                arguments(78, 50),

                arguments(-1, 5));

    }


}
