import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {
    public static final String token = "Bearer 5aa8220420419fd5890bb88a1767ed7cb1abc4412024bfff91513a40d6e19823";

    @Test
    public void testCreateUser() {
        String uniqueEmail = "user" + System.currentTimeMillis() + "@mailinator.com";

        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("email", uniqueEmail);
            requestJson.put("name", "Gaveshani");
            requestJson.put("gender", "female");
            requestJson.put("status", "Active");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .body(requestJson.toString())
                .log().all()
                .when()
                .post("/public-api/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", notNullValue())
                .body("data.name", equalTo("Gaveshani"))
                .body("data.email", equalTo(uniqueEmail))
                .body("data.gender",equalTo ("female"))
                .body("data.status",equalTo ("active"));
    }
}
