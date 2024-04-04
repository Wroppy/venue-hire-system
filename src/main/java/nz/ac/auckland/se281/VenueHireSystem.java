package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues;
  private Date date;
  private ArrayList<Booking> bookings = new ArrayList<>();

  public VenueHireSystem() {
    this.venues = new ArrayList<>();
  }

  private boolean isVenueCodeUsed(String code) {
    // Loops through each venue and checks if the code is in use
    // Returns false if one exists, and true otherwise
    for (Venue venue : this.venues) {
      if (code.equals(venue.getCode())) {
        return true;
      }
    }
    return false;
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
    String[] strings = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
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
    String nextAvailable;

    // Loops and prints out all venues
    for (Venue venue : this.venues) {
      name = venue.getName();
      code = venue.getCode();
      capacity = String.valueOf(venue.getCapcity());
      fee = String.valueOf(venue.getHireFee());
      try {
        nextAvailable = this.getNextAvailableDate(code);
      } catch (Exception e) {
        nextAvailable = "";
      }
      MessageCli.VENUE_ENTRY.printMessage(name, code, capacity, fee, nextAvailable);
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
    if (this.isVenueCodeUsed(venueCode)) {
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
    // Gets Date instance of specified date input
    this.date = new Date(dateInput);
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  private boolean isVenuesEmpty() {
    return this.venues.size() == 0;
  }

  public void printSystemDate() {
    // Checks if the date has been set
    if (!this.isSystemDateSet()) {
      System.out.println("Current system date is not set.");
      return;
    }

    MessageCli.CURRENT_DATE.printMessage(this.date.toString());
  }

  // Returns true if the date is set
  private boolean isSystemDateSet() {
    return this.date != null;
  }

  private boolean isVenueAvailable(String code, Date date) {
    // Loops through all venues and checks if the code is in use and if the date is the same
    for (Booking booking : this.bookings) {
      // Code doesn't equal -> goes to next iteration
      if (!code.equals(booking.getVenueCode())) {
        continue;
      }

      // Date doesn't equal -> goes to next iteration
      if (!date.isDateEqual(booking.getDate())) {
        continue;
      }

      // Venue already booked
      return false;
    }

    return true;
  }

  private int getVenueCapacity(String code) {
    for (Venue venue : this.venues) {
      if (code.equals(venue.getCode())) {
        return venue.getCapcity();
      }
    }
    return 0;
  }

  public void makeBooking(String[] options) {
    // Checks that the system date is set
    if (!this.isSystemDateSet()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // Checks that there is at least 1 venue in the system
    if (this.isVenuesEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // Unpacks the options param: [venueCode, date, email, attendees]
    String code = options[0];
    Date bookingDate = new Date(options[1]);
    String email = options[2];
    int attendees = Integer.parseInt(options[3]);

    // Checks that the venue code exists
    if (!this.isVenueCodeUsed(code)) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(code);
      return;
    }

    // Checks booking date is not in the past (date >= systemDate)
    if (this.date.isDateBehind(bookingDate)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(
          bookingDate.toString(), this.date.toString());
      return;
    }

    // Checks that the venue is available on the day
    if (!this.isVenueAvailable(code, bookingDate)) {
      String venueName = this.getVenueName(code);
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
          venueName, bookingDate.toString());
      return;
    }

    // TODO Add min and max attendees
    // Checks that attendees is 25% of the max
    int capacity = this.getVenueCapacity(code);

    if (attendees < capacity / 4) {
      String preAttendees = String.valueOf(attendees);
      String newAttendees = String.valueOf(capacity / 4);
      String capacityString = String.valueOf(capacity);
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          preAttendees, newAttendees, capacityString);
      attendees = capacity / 4;
    } else if (attendees > capacity) {
      String preAttendees = String.valueOf(attendees);
      String capacityString = String.valueOf(capacity);

      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          preAttendees, capacityString, capacityString);
      attendees = capacity;
    }

    // Adds to the list after it passes all checks
    Booking booking = new Booking(code, bookingDate, email, attendees);
    this.bookings.add(booking);
    String bookingRef = booking.getBookingRef();
    String attendeesString = String.valueOf(attendees);
    String dateString = bookingDate.toString();
    String venueName = this.getVenueName(code);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        bookingRef, venueName, dateString, attendeesString);
  }

  private ArrayList<Booking> getBookings(String code) {
    // Given the venue code, gets the next available date the venue can be booked on.
    ArrayList<Booking> venueBookings = new ArrayList<>();
    for (Booking booking : this.bookings) {
      if (code.equals(booking.getVenueCode())) {
        venueBookings.add(booking);
      }
    }
    return venueBookings;
  }

  public String getNextAvailableDate(String code) {
    // Gets all the bookings with the code
    ArrayList<Booking> venueBookings = this.getBookings(code);

    // If there are no bookings, then next available date is today
    if (venueBookings.size() == 0) {
      return this.date.toString();
    }
    // Program constraints state that there will be no overflow onto other months.
    // Hence only bookings on the same month and year as the system date matter.
    ArrayList<Booking> validBookings = new ArrayList<>();
    for (Booking booking : venueBookings) {
      Date bookingDate = booking.getDate();

      if (!this.date.isMonthYearEqual(bookingDate)) {
        continue;
      }

      // Bookings that are passed are not needed
      if (bookingDate.getDay() < this.date.getDay()) {
        continue;
      }

      validBookings.add(booking);
    }

    // Sorts the list by day from smallest to biggest
    Comparator<Booking> comparator = Comparator.comparing(Booking::getDay);
    Collections.sort(validBookings, comparator);

    // Traverses the list to find a gap in the days
    int smallestDay = this.date.getDay();

    for (Booking booking : validBookings) {
      // If there is a booking on the current day, to go next
      if (booking.getDay() == smallestDay) {
        smallestDay++;
        continue;
      }
      break;
    }

    return new Date(smallestDay, this.date.getMonth(), this.date.getYear()).toString();
  }

  public void printBookings(String venueCode) {
    // TODO check for 0 venues

    // Checks that the venue code exists
    if (!this.isVenueCodeUsed(venueCode)) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // Gets the bookings
    ArrayList<Booking> bookings = this.getBookings(venueCode);

    // Prints the heading for the output
    String venueName = this.getVenueName(venueCode);
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);

    // Handles the case for 0 bookings made on the venue
    if (bookings.size() == 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
      return;
    }

    // TODO check for multiple bookings

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
