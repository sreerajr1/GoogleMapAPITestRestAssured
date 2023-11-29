package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resourses.ApiResoures;
import resourses.TestData;
import resourses.Utils;

public class StepDefinition  extends Utils {

	RequestSpecification baseReq;
	
	RequestSpecification req;
	
	Response response;
	
	TestData data = new TestData();
	
	static String placeId;
	
	
	@Given("Add Place Payload with {string}, {string} and {string}")
	public void add_placr_payload(String name, String language, String address) throws IOException {

		
		req = given().spec(requestSpecificationBuild()).body(data.addPlacePayloadBuild(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource ,String method) {
		
		ApiResoures apiResources = ApiResoures.valueOf(resource);
		
		if(method.equalsIgnoreCase("Post"))
			response = req.when().post(apiResources.getResource());
		else if(method.equalsIgnoreCase("Get"))
			response = req.when().get(apiResources.getResource());
		//.then().assertThat().spec(respnonseSpecificationBuild()).extract().response();
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    Assert.assertEquals(200, response.getStatusCode());
	   
	}
	@Then("{string}  in response is {string}")
	public void in_response_is(String keyValue, String actualValue) {
	    Assert.assertEquals(actualValue,getJsonPath(response,keyValue));
	    
	    
	}
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId = getJsonPath(response,"place_id");
		req = given().spec(requestSpecificationBuild()).queryParam("place_id", placeId);
		user_calls_with_post_http_request(resource ,"GET");
		String actualName = getJsonPath(response,"name");
		Assert.assertEquals(actualName, expectedName);
	}
	@Given("DeletePlace payload")
	public void deletePlace_payload() throws IOException{
		req = given().spec(requestSpecificationBuild()).body(data.deletePlacePlayloanduild(placeId));
	}
}
