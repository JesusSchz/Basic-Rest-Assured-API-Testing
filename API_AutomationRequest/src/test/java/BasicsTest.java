//import TestNG

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
public class BasicsTest {
    int id;
    int total;
    int totalPages;

    @Test
    public void getUsersRequest() {
        System.out.println("Hello and welcome from get!");
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test
    void postUserRequest() {
        System.out.println("Hello and welcome from Post!");

        HashMap<String, String> data= new HashMap<>();
        data.put("name","othernew");
        data.put("job","sysadmin");

        id=given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
        //.then()
        //        .statusCode(201)
        //        .log().all();
        System.out.println(id);
    }

    @Test
    void putUserRequest() {
        System.out.println("Hello and welcome from Update!");

        HashMap<String, String> data= new HashMap<>();
        data.put("name","lunar");
        data.put("job","lawerered");

        given()
                .contentType(JSON)
                .body(data)
        .when()
                .put("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(200)
                .log().all();
        System.out.println(id);
    }


    @Test
    void deleteUserRequest() {
        System.out.println("Hello and welcome from Delete!");

        given()

        .when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
        System.out.println(id);
    }
}
