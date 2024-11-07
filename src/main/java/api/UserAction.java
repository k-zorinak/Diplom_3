package api;

import io.restassured.response.Response;
import resources.UserCard;

import static io.restassured.RestAssured.given;

public class UserAction extends BaseApi {
    public UserAction() {
    }

    public static void postRequestCreateUser(UserCard obj) {
        given(requestSpecification())
                .body(obj)
                .when()
                .post(PathApi.CREATE_USER);
    }
}
