/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 24, 2024
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class Event {
    private String name;
    private String date;
    private String location;
    private String id; // Unique ID for the event

    // Constructor with ID generation
    public Event(String name, String date, String location) {
        // Validate the event name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        // Validate the event date
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be null or empty.");
        }
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
        // Validate the event location
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Event location cannot be empty.");
        }

        this.name = name;
        this.date = date;
        this.location = location;
        this.id = UUID.randomUUID().toString();  // Generate a unique ID for the event
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;  // Return the unique ID for the event
    }

    // Setter methods with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty.");
        }
        this.name = name;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Event date cannot be null or empty.");
        }
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
        this.date = date;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Event location cannot be empty.");
        }
        this.location = location;
    }

    // Override the toString() method to return a readable string
    @Override
    public String toString() {
        return "Event ID: " + getId() + "\nEvent Name: " + getName() + "\nDate: " + getDate() + "\nLocation: " + getLocation();
    }

    private boolean isValidDate(String date) {
        // First, check the format using a regular expression to ensure it's yyyy-MM-dd
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;  // Date format is not yyyy-MM-dd
        }

        // Then, check if the date is actually valid using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Strict date parsing (e.g., no February 30th)
        try {
            sdf.parse(date);  // Try to parse the date
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
  }