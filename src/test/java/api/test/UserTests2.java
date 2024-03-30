package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests2 
{
	Faker faker;        //This will act as test data file, where we take the test data
	User userPayload;          // User means the class name we created under api.payload package
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
			faker=new Faker(); 
			userPayload =new User();          // User means the class name we created under api.payload package
			
			userPayload.setId(faker.idNumber().hashCode());    //hashCode() means randomly generate an ID, most probably the exact name we give via faker/testdata file
			userPayload.setUsername(faker.name().username()); 
			userPayload.setFirstName(faker.name().firstName());   //faker.name(), this is unique coding in the faker class
			userPayload.setLastName(faker.name().lastName()); 
			userPayload.setEmail(faker.internet().safeEmailAddress());   //faker.internet(), this is unique coding in the faker class
			userPayload.setPassword(faker.internet().password(5, 10)); 
			userPayload.setPhone(faker.phoneNumber().cellPhone()); 	
			
	//Logs
			logger= LogManager.getLogger(this.getClass());
	
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*****Creating User*****");
		
		Response response=UserEndPoints2.createUser(userPayload);   //UserEndPoints2 is the class we created under the package api.endpoints
		response.then().log().all( );    //once we receive the response we can use then()
		
		Assert.assertEquals(response.getStatusCode(),200); 
		
		logger.info("*****User is Created*****");		
		logger.debug("debugging....");
		
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("*****Reading User Info*****");
		
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername());   //getUsername(), this is the getter we set in the User.cs in under the class api.payload 
		response.then().log().all( );    
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*****User info is displayed*****");
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("*****Updating User*****");
		
		//Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);  
		response.then().log().body( );    
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after update
		Response responseAfterupdate=UserEndPoints2.readUser(this.userPayload.getUsername()); 
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*****User is Updated*****");
	}
	
	
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("*****Deleting User*****");
		
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());       		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*****User is Deleted*****");
	}
	
	
	
	
}
