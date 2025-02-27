import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {
    @Test
    public void testDeleteUser() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .log().all()
                .when()
                .delete("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200);
    }
}
