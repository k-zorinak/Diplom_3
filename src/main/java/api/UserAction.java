package api;

import io.restassured.response.Response;
import api.UserCard;

import static io.restassured.RestAssured.given;

public class UserAction extends BaseApi {

    public static void postRequestCreateUser(UserCard obj) {
        given(requestSpecification())
                .body(obj)
                .when()
                .post(PathApi.CREATE_USER);
    }
}
