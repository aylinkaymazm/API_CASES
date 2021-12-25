package GetRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;


public class ApiAutomationTest {

    @Test
    public void quantityControlTest() {

        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        RequestSpecification httpRequest = given();
        int dataSize = 2;
        ValidatableResponse response = given().when().get("/companies?_quantity=" + dataSize).then();
        int data = response.extract().jsonPath().getList("data").size();
        Assert.assertEquals(dataSize, data);
        System.out.println(response.extract().asPrettyString());

    }

    @Test
    public void textControlTest() {
        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/texts?_quantity=1&_characters=500");
        JSONObject obj = new JSONObject(response.then().extract().body().asString());
        JSONArray arr = obj.getJSONArray("data");
        JSONObject element;
        for (int i = 0; i < arr.length(); i++) {
            element = arr.getJSONObject(i);
            String title = element.getString("title");
            String author = element.getString("author");
            String genre = element.getString("genre");
            String content = element.getString("content");
            Assert.assertNotEquals(title, null);
            Assert.assertNotEquals(author, null);
            Assert.assertNotEquals(genre, null);
            Assert.assertNotEquals(content, null);
        }
        System.out.println(response.getBody().asPrettyString());

    }

    @Test
    public void characterLengthCheck() {
        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/texts?_quantity=1&_characters=500");
        JSONObject obj = new JSONObject(response.then().extract().body().asString());
        JSONArray arr = obj.getJSONArray("data");
        JSONObject element = arr.getJSONObject(500);
        String content = element.getString("content");
        System.out.println(content);
      //  System.out.println("March Hare. The Hatter looked at Alice, and she very seldom followed it), and sometimes shorter, until she made out that one of the Shark, But, when the White Rabbit, with a table set out under a tree in the morning, just time to begin with; and being so many tea-things are put out here?' she asked. 'Yes, that's it,' said Alice, rather alarmed at the place of the right-hand bit to try the experiment?' 'HE might bite,' Alice cautiously replied, not feeling at all for any lesson-books!' And so.".length());
        Assert.assertEquals(content.trim().length(),0);
//        for (int i = 0; i < arr.length(); i++) {
//            element = arr.getJSONObject(i);
//            String title = element.getString("title");
//            String author = element.getString("author");
//            String genre = element.getString("genre");
//            String content = element.getString("content");
//            Assert.assertNotEquals(title, null);
//            Assert.assertNotEquals(author, null);
//            Assert.assertNotEquals(genre, null);
//            Assert.assertNotEquals(content, null);
//        }
        //System.out.println(response.getBody().asPrettyString());

    }


    @Test
    public void checkFakerApiCustom() {
        RestAssured.baseURI = "https://fakerapi.it/api/v1";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("/custom?_quantity=1&name=name&lmd=dateTime&phoneNumber=phone&description=text");
        System.out.println(response.getBody().asPrettyString());
    }
}

