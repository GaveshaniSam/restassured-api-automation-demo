import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseTest{
    @Test
    public void testUpdateUser() throws JSONException {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", "Upeksha");
            requestJson.put("email", "upksh" + System.currentTimeMillis() + "@mailinator.com");
            requestJson.put("gender", "female");
            requestJson.put("status", "active");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .body(requestJson.toString())
                .log().all()
                .when()
                .put("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", equalTo(requestJson.getString("email")))
                .body("data.name", equalTo("Upeksha"))
                .body("data.gender", equalTo("female"))
                .body("data.status", equalTo("active"))
                .body("data.id", equalTo(userId));
    }
}
