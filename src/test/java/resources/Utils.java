package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
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
	public static RequestSpecification ReqSpec;
	public static ResponseSpecification ResSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if(ReqSpec==null) {
		PrintStream ReqLog=new PrintStream(new FileOutputStream("RequestLogs.txt"));
		PrintStream ResLog=new PrintStream(new FileOutputStream("ResponseLogs.txt"));
		ReqSpec = new RequestSpecBuilder().
				setBaseUri(getGlobalValue("baseURI"))
				.addFilter(RequestLoggingFilter.logRequestTo(ReqLog))
				.addFilter(ResponseLoggingFilter.logResponseTo(ResLog))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		return ReqSpec;
		}
		return ReqSpec;
	}
	
	public ResponseSpecification responseSpecification() {
		ResSpec=new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).build();
		
		return ResSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public Object getJsonVal(Response response, String key) {
		JsonPath js=new JsonPath(response.asString());
		return js.get(key);
	}
}
