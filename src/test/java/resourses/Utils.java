package resourses;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification baseReq;
	
	ResponseSpecification res;
	
	public RequestSpecification requestSpecificationBuild() throws IOException {
		
		if(baseReq==null){
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			baseReq = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			
			return baseReq;
		}
		return baseReq;
	}
	public ResponseSpecification respnonseSpecificationBuild() {
		res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).expectBody("scope", equalTo("APP")).build();
		
		return res;
	}
	
	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\sreerar\\eclipse-workspace\\CucumberG Framework Google API\\src\\test\\java\\resourses\\global.properties"));
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response , String keyValue) {
		JsonPath js = new JsonPath(response.asString());
		return js.get(keyValue);
	}
	
	
}
