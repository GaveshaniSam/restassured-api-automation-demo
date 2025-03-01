package test;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static constant.TestConstant.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest extends BaseTest {
    public static final String token = "Bearer 5aa8220420419fd5890bb88a1767ed7cb1abc4412024bfff91513a40d6e19823";

    @Test
    public void testCreateUser() {
        String uniqueEmail = "user" + System.currentTimeMillis() + "@mailinator.com";

        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("email", uniqueEmail);
            requestJson.put("name", FIRST_NAME);
            requestJson.put("gender", GENDER);
            requestJson.put("status", STATUS);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        given(requestSpec)
                .header("Authorization", token)
                .body(requestJson.toString())
                .log().all()
                .when()
                .post("/public-api/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(FIRST_NAME))
                .body("data.email", equalTo(uniqueEmail))
                .body("data.gender", equalTo(GENDER))
                .body("data.status", equalTo(STATUS));
    }
}
