/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 24, 2024 
 */
package ui;

import javax.swing.*;

import models.Attendee;
import models.Event;
import services.AttendeeService;
import services.EventService;

import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * User interface for the Event Management System using JFrame.
 */
public class UserInterface {
    private AttendeeService attendeeService;
    private EventService eventService;
    private JFrame frame;
    private JPanel panel;
    
    // Add date format for display
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public UserInterface() {
        attendeeService = new AttendeeService();
        eventService = new EventService();
        initialize();
    }
    
    /**
     * Method to view sorted attendees.
     *
     * @param textArea The text area to display messages.
     */
    private void sortAttendees(JTextArea textArea) {
        attendeeService.sortAttendees();
        textArea.setText("Attendees sorted by registration time:\n" + attendeeService.displayAttendees());
    }

    /**
     * Initializes the GUI components.
     */
    private void initialize() {
        frame = new JFrame("Event Management System");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        frame.add(inputPanel, BorderLayout.SOUTH);

        JButton addEventButton = new JButton("Add Event");
        JButton addAttendeeButton = new JButton("Add Attendee");
        JButton viewEventsButton = new JButton("View Events");
        JButton viewAttendeesButton = new JButton("View Attendees");
        JButton editEventButton = new JButton("Edit Event");
        JButton deleteEventButton = new JButton("Delete Event");
        JButton editAttendeeButton = new JButton("Edit Attendee");
        JButton deleteAttendeeButton = new JButton("Delete Attendee");
        JButton sortAttendeesButton = new JButton("Sort Attendees by Registration Time");

        inputPanel.add(addEventButton);
        inputPanel.add(addAttendeeButton);
        inputPanel.add(viewEventsButton);
        inputPanel.add(viewAttendeesButton);
        inputPanel.add(editEventButton);
        inputPanel.add(deleteEventButton);
        inputPanel.add(editAttendeeButton);
        inputPanel.add(deleteAttendeeButton);
        inputPanel.add(sortAttendeesButton);

        // Action listeners for buttons
        addEventButton.addActionListener(e -> addEvent(textArea)); // Pass the textArea correctly
        addAttendeeButton.addActionListener(e -> addAttendee(textArea));
        viewEventsButton.addActionListener(e -> textArea.setText(eventService.displayEvents()));
        viewAttendeesButton.addActionListener(e -> textArea.setText(attendeeService.displayAttendees()));
        editEventButton.addActionListener(e -> editEvent1(textArea));
        deleteEventButton.addActionListener(e -> deleteEvent(textArea));
        editAttendeeButton.addActionListener(e -> editAttendee1(textArea));
        deleteAttendeeButton.addActionListener(e -> deleteAttendee(textArea));
        sortAttendeesButton.addActionListener(e -> sortAttendees(textArea));

        frame.setVisible(true);
    }

    /**
     * Method to add an event via input dialog.
     *
     * @param textArea The text area to display messages.
     */
    private void addEvent(JTextArea textArea) {
        String name = JOptionPane.showInputDialog(frame, "Enter event name:");
        String date = JOptionPane.showInputDialog(frame, "Enter event date:");
        String location = JOptionPane.showInputDialog(frame, "Enter event location:");

        // Input validation
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Name cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (date == null || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Date cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (location == null || location.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Location cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Event event = new Event(name, date, location); // Make sure the Event constructor works
            eventService.addEvent(event);
            textArea.setText("Event added successfully.");
        } catch (IllegalArgumentException e) {
            // Handle the case where the Event constructor throws an exception
            textArea.setText("Error: " + e.getMessage());
        }
    }
    
    private void editEvent1(JTextArea textArea) {
        String eventIdText = JOptionPane.showInputDialog(frame, "Enter the event ID to edit:");

        // Check if the input is empty or null before parsing
        if (eventIdText == null || eventIdText.trim().isEmpty()) {
            textArea.setText("Error: Event ID cannot be empty.");
            return;
        }

        try {
            int eventId = Integer.parseInt(eventIdText.trim());  // Parse the event ID
            
            // Retrieve the existing event by ID
            Event event = eventService.getEvent(eventId);  // Assuming you have a method to get an event by ID

            // If event doesn't exist
            if (event == null) {
                textArea.setText("Error: Event not found.");
                return;
            }

            // Display the current details in input dialogs
            String name = JOptionPane.showInputDialog(frame, "Enter new event name:", event.getName());
            String date = JOptionPane.showInputDialog(frame, "Enter new event date:", event.getDate());
            String location = JOptionPane.showInputDialog(frame, "Enter new event location:", event.getLocation());

            // Input validation
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Name cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (date == null || date.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Date cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (location == null || location.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Location cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the event with new details
            event.setName(name);
            event.setDate(date);
            event.setLocation(location);

            // Update the event in the service (assuming you have an update method in your service)
            eventService.updateEvent(event);  // Assuming you have a method like this

            // Display updated event details
            textArea.setText("Event updated successfully:\n" + event.toString());  // Use toString to show updated info

        } catch (NumberFormatException e) {
            textArea.setText("Error: Invalid Event ID. Please enter a valid integer.");
        } catch (Exception e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }
    
    /**
     * Method to add an attendee via input dialog.
     *
     * @param textArea The text area to display messages.
     */
    private void addAttendee(JTextArea textArea) {
        String name = JOptionPane.showInputDialog(frame, "Enter attendee name:");
        String email = JOptionPane.showInputDialog(frame, "Enter attendee email:");
        String phone = JOptionPane.showInputDialog(frame, "Enter attendee phone:");
        
        
     // Input validation
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Name cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Email cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (phone == null || phone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Error: Phone cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Attendee attendee = new Attendee(name, email, phone);
            attendeeService.addAttendee(attendee);
            textArea.setText("Event added successfully.");
        } catch (IllegalArgumentException e) {
            // Handle the case where the Event constructor throws an exception
            textArea.setText("Error: " + e.getMessage());
        }
    }

    private void editAttendee1(JTextArea textArea) {
        String attendeeIdText = JOptionPane.showInputDialog(frame, "Enter the attendee ID to edit:");

        // Check if the input is empty or null before parsing
        if (attendeeIdText == null || attendeeIdText.trim().isEmpty()) {
            textArea.setText("Error: Attendee ID cannot be empty.");
            return;
        }

        try {
            int attendeeId = Integer.parseInt(attendeeIdText.trim());  // Parse the attendee ID
            
            // Prompt for new details for the attendee
            String name = JOptionPane.showInputDialog(frame, "Enter new name:");
            String email = JOptionPane.showInputDialog(frame, "Enter new email:");
            String phone = JOptionPane.showInputDialog(frame, "Enter new phone:");

            // Input validation for the new details
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Name cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Email cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (phone == null || phone.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Phone cannot be null or empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create a new Attendee object with the updated details
            Attendee updatedAttendee = new Attendee(name, email, phone);

            // Call the service to edit the attendee
            attendeeService.editAttendee(attendeeId, updatedAttendee);
            textArea.setText("Attendee edited successfully.");
        } catch (NumberFormatException e) {
            textArea.setText("Error: Invalid Attendee ID. Please enter a valid integer.");
        } catch (Exception e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }


    /**
     * Method to delete an event.
     *
     * @param textArea The text area to display messages.
     */
    private void deleteEvent(JTextArea textArea) {
        String eventIdText = JOptionPane.showInputDialog(frame, "Enter the event ID to delete:");

        // Check if the input is empty or null before parsing
        if (eventIdText == null || eventIdText.trim().isEmpty()) {
            textArea.setText("Error: Event ID cannot be empty.");
            return;
        }

        try {
            int eventId = Integer.parseInt(eventIdText.trim());  // Parse the event ID
            eventService.deleteEvent(eventId);  // Call the method to delete the event
            textArea.setText("Event deleted successfully.");
        } catch (NumberFormatException e) {
            textArea.setText("Error: Invalid Event ID. Please enter a valid integer.");
        } catch (Exception e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }

    /**
     * Method to edit an attendee.
     *
     * @param textArea The text area to display messages.
     */
    private void editAttendee(JTextArea textArea) {
        String attendeeIdText = JOptionPane.showInputDialog(frame, "Enter the attendee ID to edit:");

        // Check if the input is empty or null before parsing
        if (attendeeIdText == null || attendeeIdText.trim().isEmpty()) {
            textArea.setText("Error: Attendee ID cannot be empty.");
            return;
        }

        try {
            int attendeeId = Integer.parseInt(attendeeIdText.trim());  // Parse the attendee ID
            // Proceed with editing the attendee
            attendeeService.editAttendee(attendeeId, null);  // Modify this line as needed
            textArea.setText("Attendee edited successfully.");
        } catch (NumberFormatException e) {
            textArea.setText("Error: Invalid Attendee ID. Please enter a valid integer.");
        } catch (Exception e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }

    /**
     * Method to delete an attendee.
     *
     * @param textArea The text area to display messages.
     */
    private void deleteAttendee(JTextArea textArea) {
        String attendeeIdText = JOptionPane.showInputDialog(frame, "Enter the attendee ID to delete:");

        // Check if the input is empty or null before parsing
        if (attendeeIdText == null || attendeeIdText.trim().isEmpty()) {
            textArea.setText("Error: Attendee ID cannot be empty.");
            return;
        }

        try {
            int attendeeId = Integer.parseInt(attendeeIdText.trim());  // Parse the attendee ID
            attendeeService.deleteAttendee(attendeeId);  // Call the method to delete the attendee
            textArea.setText("Attendee deleted successfully.");
        } catch (NumberFormatException e) {
            textArea.setText("Error: Invalid Attendee ID. Please enter a valid integer.");
        } catch (Exception e) {
            textArea.setText("Error: " + e.getMessage());
        }
    }
}  