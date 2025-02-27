import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public static final String token = "Bearer 5aa8220420419fd5890bb88a1767ed7cb1abc4412024bfff91513a40d6e19823";

    public int userId;

    @BeforeMethod
    public void setUp() {
        userId = createUser();
    }

    public int createUser() {
        String uniqueEmail = "user" + System.currentTimeMillis() + "@mailinator.com";

        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", "Gaveshani");
            requestJson.put("email", uniqueEmail);
            requestJson.put("gender", "female");
            requestJson.put("status", "active");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri("https://gorest.co.in")
                .body(requestJson.toString())
                .log().all()
                .when()
                .post("/public-api/users/")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("data.id");
    }

}
