package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.MainController;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@SpringBootTest
class TestProject1ApplicationTests {

	@Autowired
	MainController mainController;
	
	@Autowired
	UserRepo userRepo;
	
	/*
	 * @Test void contextLoads() { }
	 */

	@Test  
    public void testRegister(){  
		
		User user = new User();
		
		String input = "04:30 PM, Sat 5/12/2018";
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "hh:mm a, EEE M/d/uuuu" , Locale.US );  // Specify locale to determine human language and cultural norms used in translating that input string.
		LocalDateTime ldt = LocalDateTime.parse( input , f );
		ZoneId z = ZoneId.of( "America/Toronto" ) ;
		ZonedDateTime zdt = ldt.atZone( z ) ;  
		
		user.setUserid("1");
		user.setFirstname("Kiran");
		user.setLastname("B");
		user.setUsername("bkiranraju001");
		user.setDob(zdt.toInstant());
		user.setEmail("kiran@gmail.com");
		user.setPassword("12345");
		user.setPincode(517501);
		user.setIsflagged(false);
		user.setJoiningdate(zdt.toInstant());
		
		
        assertEquals("User created", mainController.register(user));   
    }  
	
	
	@Test  
    public void testUpdate(){  
		
		User user = new User();
		
		String input = "04:30 PM, Sat 5/12/2018";
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "hh:mm a, EEE M/d/uuuu" , Locale.US );  // Specify locale to determine human language and cultural norms used in translating that input string.
		LocalDateTime ldt = LocalDateTime.parse( input , f );
		ZoneId z = ZoneId.of( "America/Toronto" ) ;
		ZonedDateTime zdt = ldt.atZone( z ) ;  
		
		user.setUserid("1");
		user.setFirstname("Kiran");
		user.setLastname("raju");
		user.setUsername("bkiranraju001");
		user.setDob(zdt.toInstant());
		user.setEmail("kiran1@gmail.com");
		user.setPassword("123456");
		user.setPincode(517501);
		user.setIsflagged(false);
		user.setJoiningdate(zdt.toInstant());
		
		
        assertEquals("User details are updated", mainController.update(user, "1"));   
    }
	
	@Test  
    public void testSearch(){
		
		List<User> user1 = userRepo.findByFirstnameOrderByDobDesc("Kiran");

		List<User> user2 = userRepo.findByLastnameOrderByDobDesc("raju");
		
		List<User> user3 = userRepo.findByPincodeOrderByDobDesc(517501);
		
        assertEquals(user1, mainController.search("1", "", 0));   
        
        assertEquals(user2, mainController.search("", "raju", 0));
        
        assertEquals(user3, mainController.search("", "", 517501));
        
    }


	@Test  
    public void testHardDelete(){  
        assertEquals("User is deleted", mainController.delete("1"));   
    }

	@Test  
    public void testSoftDelete(){  
        assertEquals("1", mainController.softDelete("1"));
        assertEquals("User is deleted", mainController.delete("1"));
    }
}
