import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUserPatchTest extends BaseTest {
    @Test
    public void testUpdateUserPatch() throws JSONException {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", "Upeksha");
            requestJson.put("email", "upksh" + System.currentTimeMillis() + "@mailinator.com");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .body(requestJson.toString())
                .log().all()
                .when()
                .patch("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", equalTo(requestJson.getString("email")))
                .body("data.name", equalTo("Upeksha"));
    }
}

