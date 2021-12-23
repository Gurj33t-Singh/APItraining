package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.AddPlace;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefs extends Utils{
	
	RequestSpecification ReqSpec;
	//ResponseSpecification ResSpec;
	Response response; //raw response
	TestDataBuild data=new TestDataBuild(); //payload
	static String place_id;
	
	@Given("Add place payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
		//add place					
		ReqSpec=given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("User calls {string} using {string} http request")
    public Response user_calls_something_API_using_something_http_request(String APIresource, String method) {
    	
    	APIresources API=APIresources.valueOf(APIresource);
    	
    	if(method.equalsIgnoreCase("POST"))
    		response=ReqSpec.when().log().all().post(API.getResource()).then().extract().response();
    	else if(method.equalsIgnoreCase("GET"))
        	response=ReqSpec.when().log().all().get(API.getResource()).then().extract().response();
    	else if(method.equalsIgnoreCase("DELETE"))
        	response=ReqSpec.when().log().all().delete(API.getResource()).then().extract().response();
    	else if(method.equalsIgnoreCase("PUT"))
    		response=ReqSpec.when().log().all().put(API.getResource()).then().extract().response();
   
    return response;
    }

    @Then("^The API responds with status code 200$")
    public void the_api_responds_with_status_code_200() {
        assertEquals(response.getStatusCode(), 200);
    }

    @And("{string} in response body is {string}")
    public void something_in_response_body_is_something(String key, String ExpectedValue) {
        assertEquals(getJsonVal(response, key).toString(), ExpectedValue);        
        System.out.println(response.asString());
    }
    
    @And("Verify place_id maps to {string} using {string}")
    public void Verify_place_id_maps_to_something_using_something(String Expectedname, String method) throws IOException {

    	place_id=getJsonVal(response, "place_id").toString();
    	ReqSpec=given().spec(requestSpecification()).queryParam("place_id", place_id);    	
    	Response getResponse=user_calls_something_API_using_something_http_request(method, "GET");
    	assertEquals(Expectedname, getJsonVal(getResponse, "name").toString());
    	
    }
    
    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
        ReqSpec=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
