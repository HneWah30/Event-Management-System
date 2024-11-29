package models;

import java.io.Serializable;
import java.util.regex.Pattern;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents an attendee in the system.
 * The class stores the name, email, phone number, and registration time of an attendee,
 * and provides validation for the name, email, and phone number fields.
 */
public class Attendee implements Serializable {
    private String name;          // Attendee's full name
    private String email;         // Attendee's email address
    private String phoneNumber;   // Attendee's phone number (must be 10 digits)
    private Date registrationTime; // The time when the attendee registered

    // Regular expression patterns for validating the email, phone number, and name
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");

    // Formatter to display the registration date in a readable format
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructor for creating an Attendee object.
     * It validates the provided name, email, and phone number before setting the values.
     * If any validation fails, an IllegalArgumentException is thrown.
     *
     * @param name       The name of the attendee.
     * @param email      The email address of the attendee.
     * @param phoneNumber The phone number of the attendee.
     */
    public Attendee(String name, String email, String phoneNumber) {
        // Validate the inputs before setting values
        String validationError = validateAttendee(name, email, phoneNumber);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);  // Throw exception if validation fails
        }

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationTime = new Date(); // Set the registration time to the current time
    }

    /**
     * Retrieves the name of the attendee.
     *
     * @return The name of the attendee.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the attendee.
     * This method also validates the name before setting the value.
     *
     * @param name The new name to set for the attendee.
     * @throws IllegalArgumentException if the name is invalid.
     */
    public void setName(String name) {
        // Validate the name and other attributes before setting the name
        String validationError = validateAttendee(name, this.email, this.phoneNumber);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);  // Throw exception if validation fails
        }
        this.name = name;
    }

    /**
     * Retrieves the email address of the attendee.
     *
     * @return The email address of the attendee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets a new email address for the attendee.
     * This method also validates the email before setting the value.
     *
     * @param email The new email address to set for the attendee.
     * @throws IllegalArgumentException if the email is invalid.
     */
    public void setEmail(String email) {
        // Validate the email and other attributes before setting the email
        String validationError = validateAttendee(this.name, email, this.phoneNumber);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);  // Throw exception if validation fails
        }
        this.email = email;
    }

    /**
     * Retrieves the phone number of the attendee.
     *
     * @return The phone number of the attendee.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets a new phone number for the attendee.
     * This method also validates the phone number before setting the value.
     *
     * @param phoneNumber The new phone number to set for the attendee.
     * @throws IllegalArgumentException if the phone number is invalid.
     */
    public void setPhoneNumber(String phoneNumber) {
        // Validate the phone number and other attributes before setting the phone number
        String validationError = validateAttendee(this.name, this.email, phoneNumber);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);  // Throw exception if validation fails
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the registration time of the attendee.
     *
     * @return The registration time of the attendee.
     */
    public Date getRegistrationTime() {
        return registrationTime;
    }

    /**
     * Provides a string representation of the Attendee object.
     * The format will be: Attendee: {name}, Email: {email}, Phone: {phone}, Registered: {registrationTime}
     *
     * @return A string representation of the Attendee object.
     */
    @Override
    public String toString() {
        return "Attendee: " + name + ", Email: " + email + ", Phone: " + phoneNumber + ", Registered: " + dateFormat.format(registrationTime);
    }

    /**
     * Validates the attendee's details (name, email, phone number).
     * This method checks whether the name contains only letters and spaces,
     * whether the email follows a valid format, and whether the phone number is exactly 10 digits.
     *
     * @param name The name of the attendee.
     * @param email The email address of the attendee.
     * @param phoneNumber The phone number of the attendee.
     * @return A string describing the validation error, or null if all fields are valid.
     */
    public static String validateAttendee(String name, String email, String phoneNumber) {
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            return "Name is required and must contain only letters and spaces.";
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            return "Invalid name. Only letters and spaces are allowed.";
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            return "Email is required.";
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return "Invalid email format.";
        }

        // Validate phone number
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Phone number is required.";
        }
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            return "Phone number must be 10 digits.";
        }

        return null;  // Return null if all validations pass
    }
}