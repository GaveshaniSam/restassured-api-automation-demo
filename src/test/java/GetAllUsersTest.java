import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsersTest {
    @Test
    public void testGetAllUsers() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .baseUri("https://gorest.co.in")
                .log().all()
                .when()
                .get("/public-api/users")
                .then()
                .log().all()
                .statusCode(200);

    }
}
