package resourses;

import java.util.ArrayList;
import java.util.List;

import com.apitesting.RestAssuredAPITest.POJO.Location;
import com.apitesting.RestAssuredAPITest.POJO.Place;



public class TestData {
	
	public Place addPlacePayloadBuild(String name, String language, String address) {
		Place place = new Place();
		
		Location location = new Location();
		location.setLat(-98.333494);
		location.setLng(53.427362);
		
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		
		List<String> type = new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		
		place.setTypes(type);
		place.setWebsite("http://google.com");
		place.setLanguage(language);
		
		return place;
	}
	
	public String deletePlacePlayloanduild(String placeId) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+ placeId +"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
