package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	Response response;
	ResponseSpecification resspec;
	RequestSpecification res;
	static String place_id;




TestDataBuild data=new TestDataBuild();


	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
	}


	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
	 
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec=new ResponseSpecBuilder()
				.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("post"))
		 response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("get"))
			 response=res.when().get(resourceAPI.getResource());
				
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	 assertEquals( response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
	  
	 //  System.out.println(js.get(KeyValue).toString());
	  // System.out.println(ExpectedValue);
	   assertEquals(getJsonPath(response, KeyValue), ExpectedValue);
	   System.out.println("hello");
	   System.out.println("world");
	   System.out.println("Postjira1");
	   System.out.println("Postjira2");
	   System.out.println("Postjira3");
	   System.out.println("Postjira4");
	}

	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id",place_id );
		user_calls_with_http_request(resource, "GET");
		String actualname=getJsonPath(response, "name");
		assertEquals(actualname, expectedName);
		   System.out.println("Postjira5");
		   System.out.println("Postjira6");
		   System.out.println("Postjira7");
	}

	

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}


}
