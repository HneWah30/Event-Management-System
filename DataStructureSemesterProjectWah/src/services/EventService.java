/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 24, 2024 
 */
package services;

import dataStructures.LinkedList;
import models.Event;

public class EventService {

    // LinkedList to store events
    private LinkedList events;

    /**
     * Constructs an EventService object and initializes the events list.
     */
    public EventService() {
        // Initializes the linked list for storing events
        events = new LinkedList();
    }

    /**
     * Adds a new event to the list.
     * 
     * @param event The Event object to be added to the list.
     */
    public void addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        // Adds the provided event to the linked list
        events.add(event);
    }

    /**
     * Displays all events in the list as a formatted string.
     * 
     * @return A string containing the details of all events in the list.
     */
    public String displayEvents() {
        StringBuilder sb = new StringBuilder();

        // Loop through all events in the list and append them to the string builder
        for (int i = 0; i < events.size(); i++) {
            sb.append(i + 1).append(". ").append(events.get(i)).append("\n");
        }

        // Returns the string representation of all events
        return sb.toString();
    }

    /**
     * Retrieves the event at the specified index.
     * 
     * @param index The index of the event to retrieve.
     * @return The Event object at the specified index.
     */
    public Event getEvent(int index) {
        // Check if index is valid
        if (index < 0 || index >= events.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        // Returns the event at the specified index from the list
        return events.get(index);
    }

    /**
     * Edits an existing event by replacing it with a new event at the specified index.
     * 
     * @param index The index of the event to be edited.
     * @param updatedEvent The new Event object containing updated information.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void editEvent(int index, Event updatedEvent) {
        if (updatedEvent == null) {
            throw new IllegalArgumentException("Updated event cannot be null.");
        }
        
        // Check if the provided index is valid
        if (index < 0 || index >= events.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        // Removes the old event at the specified index and adds the updated event
        events.remove(index);
        events.add(updatedEvent);
    }

    /**
     * Deletes the event at the specified index.
     * 
     * @param index The index of the event to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public void deleteEvent(int index) {
        // Check if the provided index is valid
        if (index < 0 || index >= events.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        // Removes the event at the specified index
        events.remove(index);
    }

    /**
     * Updates an existing event based on the ID. Assumes each event has a unique ID.
     * 
     * @param updatedEvent The event object with the updated details.
     */
    public void updateEvent(Event updatedEvent) {
        if (updatedEvent == null) {
            throw new IllegalArgumentException("Updated event cannot be null.");
        }

        boolean updated = false;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.getId() == updatedEvent.getId()) {  // Assuming the Event has a unique ID
                events.set(i, updatedEvent);  // Replace the old event with the updated one
                updated = true;
                break;  // Exit once the event is found and updated
            }
        }

        if (!updated) {
            throw new IllegalArgumentException("Event not found for ID: " + updatedEvent.getId());
        }
    }
}
