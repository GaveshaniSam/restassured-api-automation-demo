import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUserDetails extends BaseTest {
    @Test
    public void testGetUserDetails() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .log().all()
                .when()
                .get("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.name", equalTo("Gaveshani"));
    }
}

