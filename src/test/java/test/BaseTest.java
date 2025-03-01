package test;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import static constant.TestConstant.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BaseTest {
    public String token;
    public int userId;
    public RequestSpecification requestSpec;

    @BeforeMethod
    public void setUp() {
        requestSpec =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .baseUri(BASE_URL);

        token = "Bearer " + given()
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .baseUri("https://5fcc640551f70e00161f24b2.mockapi.io")
                .log().all()
                .when()
                .get("/api/v1/token/")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .get("token");

        userId = createUser();
    }

    public int createUser() {
        String uniqueEmail = "user" + System.currentTimeMillis() + "@mailinator.com";

        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("name", FIRST_NAME);
            requestJson.put("email", uniqueEmail);
            requestJson.put("gender", GENDER);
            requestJson.put("status", STATUS);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return given(requestSpec)
                .header("Authorization", token)
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
