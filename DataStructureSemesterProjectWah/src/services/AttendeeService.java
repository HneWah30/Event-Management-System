/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 24, 2024 
 */
package services;

import dataStructures.AttendeeList;
import models.Attendee;

public class AttendeeService {
    
    // Internal list to store attendees
    private AttendeeList attendeeList;

    /**
     * Constructs an AttendeeService object and initializes the attendee list.
     */
    public AttendeeService() {
        // Initializes the attendeeList object
        attendeeList = new AttendeeList();
    }

    /**
     * Adds a new attendee to the attendee list.
     * 
     * @param attendee The Attendee object to be added.
     * @throws IllegalArgumentException If the attendee is null.
     */
    public void addAttendee(Attendee attendee) {
        if (attendee == null) {
            throw new IllegalArgumentException("Attendee cannot be null.");
        }
        // Adds the provided attendee to the list
        attendeeList.addAttendee(attendee);
    }

    /**
     * Returns a string representation of all attendees in the list.
     * 
     * @return A string containing details of all attendees.
     */
    public String displayAttendees() {
        return attendeeList.displayAttendees();
    }

    /**
     * Edits an existing attendee's details at the specified index.
     * 
     * @param index The index of the attendee to be updated.
     * @param updatedAttendee The updated Attendee object containing the new details.
     * @throws IndexOutOfBoundsException If the index is out of range of the attendee list.
     * @throws IllegalArgumentException If the updatedAttendee is null.
     */
    public void editAttendee(int index, Attendee updatedAttendee) {
        if (updatedAttendee == null) {
            throw new IllegalArgumentException("Updated Attendee cannot be null.");
        }
        // Check if the provided index is valid
        if (index < 0 || index >= attendeeList.size()) {
            throw new IndexOutOfBoundsException("Attendee index out of bounds.");
        }
        
        // Retrieve the attendee and update its details
        Attendee attendee = attendeeList.getAttendee(index);
        attendee.setName(updatedAttendee.getName());
        attendee.setEmail(updatedAttendee.getEmail());
        attendee.setPhoneNumber(updatedAttendee.getPhoneNumber());
    }

    /**
     * Deletes an attendee from the list at the specified index.
     * 
     * @param index The index of the attendee to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range of the attendee list.
     */
    public void deleteAttendee(int index) {
        // Check if the provided index is valid
        if (index < 0 || index >= attendeeList.size()) {
            throw new IndexOutOfBoundsException("Attendee index out of bounds.");
        }
        // Deletes the attendee at the specified index
        attendeeList.deleteAttendee(index);
    }

    /**
     * Sorts the list of attendees, usually based on some criteria like name.
     */
    public void sortAttendees() {
        attendeeList.sortAttendees();
    }
}
