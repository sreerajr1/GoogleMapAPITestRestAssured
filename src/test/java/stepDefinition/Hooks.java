package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		
		StepDefinition stepDefinition = new StepDefinition();
		if(StepDefinition.placeId==null) {
			stepDefinition.add_placr_payload("DelName", "DelLanguage", "Del Address");
			stepDefinition.user_calls_with_post_http_request("AddPlaceAPI", "Post");
			stepDefinition.verify_place_id_created_maps_to_using("DelName", "getPlaceAPI");
		}
		
	}

}
