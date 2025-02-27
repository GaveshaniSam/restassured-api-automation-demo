import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTokenTest {
    @Test
    public void testGetToken() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .baseUri("https://5fcc640551f70e00161f24b2.mockapi.io")
                .log().all()
                .when()
                .get("/api/v1/token/")
                .then()
                .log().all()
                .statusCode(200);
    }
}
