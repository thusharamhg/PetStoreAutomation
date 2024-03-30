package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;




//UserEndPints.java 
//Created for perform Create, Read, Update, Delete requests to the user API. 


public class UserEndPoints 
{
	
	public static Response createUser(User payload)
	{
		Response response=given() 
				.contentType(ContentType.JSON) 
				.accept(ContentType.JSON) 
				.body(payload) 
			.when() 
				.post(Rootes.post_url) ;
		
				return response;
	}
	
	
 
	public static Response readUser(String userName) 
	{
		Response response=given() 
				.pathParam("username",userName)
			.when() 
				.get(Rootes.get_url) ;
		
				return response;	
	}
	
	
	public static Response updateUser(String userName, User payload)  //We have to send the username and User request body as input
	{
		Response response=given() 
				.contentType(ContentType.JSON)     //can see this in swagger -H section   (-H 'Content-Type: application/json' \)
				.accept(ContentType.JSON)         // can see this in swagger -H section   (-H 'accept: application/json' \)
				.pathParam("username",userName)   // 1st one is define in the Roots class as the url parameter  (public static String update_url= base_url+"/user/{username}";)  , 2nd one is the input value which user to update
				.body(payload)                   //Input of the request body
			.when() 
				.put(Rootes.update_url) ;
		
				return response;
	}
	
	
	public static Response deleteUser(String userName) 
	{
		Response response=given() 
				.pathParam("username",userName)
			.when() 
				.delete(Rootes.delete_url) ;
		
				return response;	
	}
	
	


}
