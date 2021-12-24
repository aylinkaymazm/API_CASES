import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;




    public class RestAssuredCases {

        @Test
        public void singleUserTest() {
            when()
                    .get("https://reqres.in/api/users/2")
                    .then().statusCode(200).
                    body("data.email",equalTo("janet.weaver@reqres.in"))
                    .time(lessThan(1L));
        }

        @Test
        public void singleUserTests() {

            RestAssured.baseURI = "https://reqres.in";
            RequestSpecification httpRequest = given();
            Response response = httpRequest.get("/api/users/2");
            ResponseBody body = response.getBody();
            String bodyAsString = body.asString();
            System.out.println(bodyAsString);
            Assert.assertTrue(bodyAsString.contains("janet.weaver@reqres.in"),"STRING DID NOT FOUND");
        }

        @Test
        public void createdUserTest(){
            String postData = "{\n" +
                    " \"name\":\"morpheus\",\n " +
                    " \"job\": \"leader\"\n " +
                    "}";
            given().
                    contentType(ContentType.JSON).
                    body(postData).
                    when().
                    post("https://reqres.in/api/users").
                    then().
                    statusCode(201).
                    body("name",equalTo("morpheus"));
        }
    }

