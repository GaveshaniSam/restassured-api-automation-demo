package test;

import org.testng.annotations.Test;

import static constant.TestConstant.FIRST_NAME;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUserDetailsTest extends BaseTest {
    @Test
    public void testGetUserDetails() {
        given(requestSpec)
                .header("Authorization", token)
                .log().all()
                .when()
                .get("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.name", equalTo(FIRST_NAME))
                .body("data.lastname", equalTo("sam"));
    }
}

