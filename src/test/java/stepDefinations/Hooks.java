package stepDefinations;

import java.io.IOException;
import java.util.Stack;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination m=new StepDefination();
		if(StepDefination.place_id==null)
		{
		m.add_place_payload("Shetty", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Shetty","getPlaceAPI");
	}
}
}
