package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

import nz.ac.auckland.se281.MessageCli;
import java.util.ArrayList;

public class VenueHireSystem {
  private ArrayList<Venue> venues;

  public VenueHireSystem() {
    this.venues = new ArrayList<Venue>();
    // For testing
    this.venues.add(new Venue("Hello", "FG", 10, 10));
    this.venues.add(new Venue("Hello there", "AW", 10, 10));
  }

  private boolean isVenueCodeUnique(String code) {
    // Loops through each venue and checks if the code is in use
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
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
  public void printVenues() {
    // TODO implement this method
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

        // Checks if the venue name is valid
        venueName = venueName.trim();
        if (venueName.length() == 0) {
          System.out.println(MessageCli.VENUE_NOT_CREATED_EMPTY_NAME);
          return;
        }

        // Checks for venue code uniqueness
        if (!this.isVenueCodeUnique(venueCode)) {
          // Not unique, print error message
          // conflicting venue should not be empty as it exists due to if
          String conflictingVenue = this.getVenueName(venueCode);
          System.out.println(MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.getMessage(venueCode, conflictingVenue));
          return;
        }

        // Checks for capacity
        // First checks for digits
        if (!this.isInt(capacityInput)) {
          System.out.println(MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.getMessage("capacity", ""));
          return;
        }
        Integer capacity = Integer.parseInt(capacityInput);
        // Checks for positive numbers > 0
        if (capacity <= 0) {
          // Negative capacity error
          System.out.println(MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.getMessage("capacity", " positive"));
          return;
        }
        
        // Checks for valid hire fee
        if (!this.isInt(hireFeeInput)) {
          System.out.println(MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.getMessage("hire fee", ""));
          return;
        }
        Integer hireFee = Integer.parseInt(hireFeeInput);
        // Checks for positive numbers > 0
        if (hireFee <= 0) {
          // Negative hire fee error
          System.out.println(MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.getMessage("hire fee", " positive"));
          return;
        }
        Venue venue = new Venue(venueName, venueCode, capacity, hireFee);
        this.venues.add(venue);
      }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
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
