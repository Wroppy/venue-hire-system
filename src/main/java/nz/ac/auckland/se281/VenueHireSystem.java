package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;
import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Date;
import java.util.ArrayList;

public class VenueHireSystem {
  private ArrayList<Venue> venues;
  private Date date;

  public VenueHireSystem() {
    this.venues = new ArrayList<Venue>();
  }

  private boolean isVenueCodeUnique(String code) {
    // Loops through each venue and checks if the code is in use
    // Returns false if one exists, and true otherwise
    for (Venue venue : this.venues) {
      if (code.equals(venue.getCode())) {
        return false;
      }
    }
    return true;
  }

  private String getVenueName(String id) {
    // Returns the venue name associated with the ID
    for (Venue venue : this.venues) {
      if (id.equals(venue.getCode())) {
        return venue.getName();
      }
    }
    return "";
  }

  private boolean isInt(String s) {
    // Returns whether a string is in an integer format
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private String getIntString(int n) {
    // Gets the string version of an integer
    String[] strings = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    return strings[n - 1];
  }

  private void printVenuesHeading() {
    int totalVenues = this.venues.size();

    // If no venues, tells the user to add venues
    if (totalVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    String number;
    String connector = "are";

    // Accounts for a single venue
    String plural = "s";
    if (totalVenues == 1) {
      plural = "";
      connector = "is";
    }

    // Sets the number of venue in either english or number form
    if (totalVenues < 10) {
      number = this.getIntString(totalVenues);
    } else {
      number = String.valueOf(totalVenues);
    }

    // Prints the heading based on the number of venues in the system
    MessageCli.NUMBER_VENUES.printMessage(connector, number, plural);
  }

  public void printVenues() {
    this.printVenuesHeading();

    String name;
    String code;
    String capacity;
    String fee;

    // Loops and prints out all venues
    for (Venue venue : this.venues) {
      name = venue.getName();
      code = venue.getCode();
      capacity = String.valueOf(venue.getCapcity());
      fee = String.valueOf(venue.getHireFee());
      MessageCli.VENUE_ENTRY.printMessage(name, code, capacity, fee);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // Checks if the venue name is valid
    venueName = venueName.trim();
    if (venueName.length() == 0) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Checks for venue code uniqueness
    if (!this.isVenueCodeUnique(venueCode)) {
      // Not unique, print error message
      // conflicting venue should not be empty as it exists due to if
      String conflictingVenue = this.getVenueName(venueCode);
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, conflictingVenue);
      return;
    }

    // Checks for capacity
    // First checks for digits
    if (!this.isInt(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    Integer capacity = Integer.parseInt(capacityInput);
    // Checks for positive numbers > 0
    if (capacity <= 0) {
      // Negative capacity error
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // Checks for valid hire fee
    if (!this.isInt(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    Integer hireFee = Integer.parseInt(hireFeeInput);
    // Checks for positive numbers > 0
    if (hireFee <= 0) {
      // Negative hire fee error
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }
    Venue venue = new Venue(venueName, venueCode, capacity, hireFee);
    this.venues.add(venue); // Adds to the system

    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    // Splits the date string into its day, month and year components
    String[] dateParams = dateInput.split("/");
    int day = Integer.parseInt(dateParams[0]);
    int month = Integer.parseInt(dateParams[1]);
    int year = Integer.parseInt(dateParams[2]);

    // Gets LocalDate instance of specified date
    this.date = new Date(day, month, year);
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    // Checks if the date has been set
    if (this.date == null) {
      System.out.println("Current system date is not set.");
      return;
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
