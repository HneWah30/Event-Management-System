/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 25, 2024 
 */
package tests;

/**
 * 
 */
import org.junit.jupiter.api.Test;

import models.Attendee;

import static org.junit.jupiter.api.Assertions.*;

public class AttendeeTest {

	    @Test
	    public void testInvalidEmail() {
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            new Attendee("Jane Doe", "invalid-email", "1234567890");
	        });
	        assertEquals("Invalid email format.", exception.getMessage());
	    }
	    
	    @Test
	    public void testEmptyName() {
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            new Attendee("", "john@example.com", "1234567890");
	        });
	        assertEquals("Name is required and must contain only letters and spaces.", exception.getMessage());
	    }

	    @Test
	    public void testInvalidPhoneNumber() {
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            new Attendee("John Doe", "john@example.com", "12345");  // Invalid phone number
	        });
	        assertEquals("Phone number must be 10 digits.", exception.getMessage());
	    }

	    @Test
	    public void testValidAttendee() {
	        Attendee attendee = new Attendee("John Doe", "john.doe@example.com", "1234567890");
	        assertNotNull(attendee);
	        assertEquals("John Doe", attendee.getName());
	        assertEquals("john.doe@example.com", attendee.getEmail());
	        assertEquals("1234567890", attendee.getPhoneNumber());
	    }
	}