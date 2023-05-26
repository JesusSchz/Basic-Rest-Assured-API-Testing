import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import pojos.User;

import java.util.HashMap;

public class BasicsTestWithPojos {

    //POST request body using hashMap
    @Test
    void testPostUsingHashMap(){
        System.out.println("Hello and welcom from POST Request with HashMap");
        HashMap data = new HashMap();
        data.put("name","Scot");
        data.put("job","Audit");
        /*
        String courseArr[]={"java","c++"};
        data.put("courses",courseArr);
        */

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(data)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scot"))
                .log().all();
    }

    //POST request body using JSON
    @Test
    void testPostUsingJsonLibrary(){
        System.out.println("Hello and welcom from POST Request with JSON");
        JSONObject jsonData = new JSONObject();
        jsonData.put("name","Scot");
        jsonData.put("job","Audit");
        /*
        String courseArr[]={"java","c++"};
        data.put("courses",courseArr);
        */

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonData.toString())
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Scot"))
                .log().all();
    }

    //POST request body using POJO
    @Test
    void testPostUsingPOJO(){
        System.out.println("Hello and welcom from POST Request with POJO");

        User pojoRequest= new User();
        pojoRequest.setName("Alex");
        pojoRequest.setJob("None");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pojoRequest)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Alex"))
                .log().all();
    }
}
