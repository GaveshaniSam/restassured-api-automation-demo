package test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserTest extends BaseTest {
    @Test
    public void testDeleteUser() {
        given(requestSpec)
                .header("Authorization", token)
                .log().all()
                .when()
                .delete("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(204));
    }
}
