package test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

public class GetAllUsersTest extends BaseTest {
    @Test
    public void testGetAllUsers() {
        given(requestSpec)
                .log().all()
                .when()
                .get("/public-api/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("data", not(empty()));
    }
}
