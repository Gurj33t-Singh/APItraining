package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		//need to write a code to get place_id 
		//execute only if place_id is null
		
		StepDefs s=new StepDefs();
		if(StepDefs.place_id==null) {
		s.add_place_payload("Singh", "Punjabi", "SAS Nagar Mohali");
		s.user_calls_something_API_using_something_http_request("AddPlaceAPI", "POST");
		s.Verify_place_id_maps_to_something_using_something("Singh", "GetPlaceAPI");
		}
	}
}
