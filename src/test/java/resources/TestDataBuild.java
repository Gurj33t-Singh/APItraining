package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		
		//creates and returns the pojo body payload
		
		AddPlace body=new AddPlace();
		List<String> types= new ArrayList<String>();
		types.add("Shoe park");
		types.add("shop");
		Location L=new Location();
		L.setLat(-40.383494);
		L.setLng(40.427362);
		body.setLocation(L);
		body.setAccuracy(20);
		body.setName(name);
		body.setPhone_number("(+91) 988 001 1434");
		body.setAddress(address);
		body.setWebsite("http://google.com");
		body.setLanguage(language);
		body.setTypes(types);
		
		return body;
	}
	
	public DeletePlace deletePlacePayload(String place_id) {
		DeletePlace body=new DeletePlace();
		body.setPlace_id(place_id);
		return body;
	}
	
}
