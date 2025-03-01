package test;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static constant.TestConstant.GENDER;
import static constant.TestConstant.STATUS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseTest {
    @Test
    public void testUpdateUser() throws JSONException {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", "Upeksha");
            requestJson.put("email", "upksh" + System.currentTimeMillis() + "@mailinator.com");
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
                .put("/public-api/users/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", equalTo(requestJson.getString("email")))
                .body("data.name", equalTo("Upeksha"))
                .body("data.gender", equalTo(GENDER))
                .body("data.status", equalTo(STATUS))
                .body("data.id", equalTo(userId));
    }

    @Test
    public void testUpdateUserPatch() throws JSONException {
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", "Upeksha");
            requestJson.put("email", "upksh" + System.currentTimeMillis() + "@mailinator.com");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        given(requestSpec)
                .header("Authorization", token)
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
