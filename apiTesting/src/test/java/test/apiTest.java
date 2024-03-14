package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class apiTest {

    String RestAssuredURL = "https://simple-tool-rental-api.glitch.me";
    String apiServerStatusPath = "/status";
    String toolsPath = "/tools";
    String toolIdPath = "/4643";
    String ordersPath = "/orders";
    String orderId = "IxwX33ud-lQ5OJ7JhoxUH";
    String apiClientPath = "/api-clients";
    String authToken = "081c8ad825c7386dfd6ca45153d59e7ddcfd264c8a78d5ab8e234dcc92fa1022";
    String newOrderId;
    String newAuthToken;

    Random random = new Random();
    String username = "fizickolice" + random.nextInt(999999);
    String password = "Getitnow2023!";
    String email2 = username+"@mailinator.com";

    @Before
    public void setUpTest() {

    }

    @Test
    public void _01_getApiServerStatus()
    {
        Response resp = RestAssured.get(RestAssuredURL+apiServerStatusPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        Assert.assertEquals(code,200);
        Assert.assertEquals(message,"HTTP/1.1 200 OK");

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
    }

    @Test
    public void _02_getAllTools()
    {
        Response resp = RestAssured.get(RestAssuredURL+toolsPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        JsonPath jsonPath = new JsonPath(body);
        int count = jsonPath.getList("$").size();

        Assert.assertEquals(code,200);
        Assert.assertTrue(count >= 20);

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Orders count is "+count);
        System.out.println("Body is "+body);
    }

    @Test
    public void _03_getSingleTool()
    {
        Response resp = RestAssured.get(RestAssuredURL+toolsPath+toolIdPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        resp.then().assertThat().statusCode(200);
        resp.then().assertThat().body("id", equalTo(4643));
        resp.then().assertThat().body("category", equalTo("plumbing"));
        resp.then().assertThat().body("name", equalTo("PEX Clamp Tool"));
        resp.then().assertThat().body("manufacturer", equalTo("JWGJW"));
        // resp.then().assertThat().body("price", equalTo(3.45));
        resp.then().assertThat().body("current-stock", equalTo(14));
        resp.then().assertThat().body("inStock", equalTo(true));

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
    }
/*
    @Test
    public void _04_registerApiClient()
    {
      String clientData = "{\"clientName\":\"Postman\",\"clientEmail\":\"fizickolice9993@example.com\"}";
        Response resp = RestAssured
             .given()
             .contentType(ContentType.JSON)
              .body(clientData)
              .post(RestAssuredURL+apiClientPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        JsonPath jsonPathEvaluator = resp.jsonPath();
        newAuthToken = jsonPathEvaluator.get("accessToken");
        System.setProperty("accessToken",newAuthToken);

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
        System.out.println("New Auth Token is "+newAuthToken);
    }
*/
    @Test
    public void _05_createOrder()
    {
        String orderData = "{\"toolId\":\"4643\",\"customerName\":\"Test customer\"}";
        Response resp = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(orderData)
                .post(RestAssuredURL+ordersPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        Assert.assertEquals(code,201);

        JsonPath jsonPathEvaluator = resp.jsonPath();
        newOrderId = jsonPathEvaluator.get("orderId");

        System.setProperty("orderId",newOrderId);

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
        System.out.println("New OrderId is "+newOrderId);

    }

    @Test
    public void _06_getAllOrders()
    {
        Response resp = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .get(RestAssuredURL+ordersPath);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();

        JsonPath jsonPath = new JsonPath(body);
        int count = jsonPath.getList("$").size();
        System.out.println("Orders count is "+count);
        Assert.assertEquals(code,200);
        Assert.assertTrue(count >= 1);

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Orders count is "+count);
        System.out.println("Body is "+body);

    }

    @Test
    public void _07_getSingleOrder()
    {
        newOrderId = System.getProperty("orderId");
        System.out.println("New OrderId is "+newOrderId);
        Response resp = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .get(RestAssuredURL+ordersPath+"/"+newOrderId);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();
        /*
        JsonPath jsonPath = new JsonPath(body);
        int count = jsonPath.getList("$").size();
        Assert.assertTrue(count >= 1);
        */
        resp.then().assertThat().statusCode(200);
        resp.then().assertThat().body("id", equalTo(newOrderId));
        resp.then().assertThat().body("toolId", equalTo(4643));
        resp.then().assertThat().body("customerName", equalTo("Test customer"));
        resp.then().assertThat().body("quantity", equalTo(1));
        resp.then().assertThat().body("comment", equalTo(""));
        resp.then().log().body();

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        //System.out.println("Orders count is "+count);
        System.out.println("Body is "+body);
    }

    @Test
    public void _08_updateSingleOrder()
    {
        String orderData = "{\"customerName\":\"Test customer updated\",\"comment\":\"Updated comment\"}";
        newOrderId = System.getProperty("orderId");
        System.out.println("New OrderId is "+newOrderId);
         RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(orderData)
                .patch(RestAssuredURL+ordersPath+"/"+newOrderId)
                .then().statusCode(204)
                .log().body()
                .extract().body();
    }

    @Test
    public void _09_validateUpdatedOrder()
    {
        newOrderId = System.getProperty("orderId");
        System.out.println("New OrderId is "+newOrderId);
        Response resp = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .get(RestAssuredURL+ordersPath+"/"+newOrderId);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();
        resp.then().assertThat().statusCode(200);
        resp.then().assertThat().body("id", equalTo(newOrderId));
        resp.then().assertThat().body("customerName", equalTo("Test customer updated"));
        resp.then().assertThat().body("comment", equalTo("Updated comment"));
        resp.then().log().body();

        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
    }

    @Test
    public void _10_deleteSingleOrder()
    {
        newOrderId = System.getProperty("orderId");
        System.out.println("New OrderId is "+newOrderId);
        RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .delete(RestAssuredURL+ordersPath+"/"+newOrderId)
                .then().statusCode(204)
                .log().body();
    }

    @Test
    public void _11_validateIfOrderIsDeleted()
    {
        newOrderId = System.getProperty("orderId");
        System.out.println("New OrderId is "+newOrderId);
        Response resp = RestAssured
                .given()
                .header("Authorization", "Bearer " + authToken)
                .get(RestAssuredURL+ordersPath+"/"+newOrderId);

        int code = resp.getStatusCode();
        String message = resp.getStatusLine();
        String body = resp.asString();
        resp.then().assertThat().statusCode(404);


        System.out.println("Status code is "+code);
        System.out.println("Status message is "+message);
        System.out.println("Body is "+body);
    }
}
