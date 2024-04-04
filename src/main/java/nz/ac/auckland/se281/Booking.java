package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.services.Service;

public class Booking {
  private String venueCode;
  private Date date;
  private String email;
  private int attendees;
  private String bookingReference;
  private ArrayList<Service> services;

  public Booking(String venueCode, Date date, String email, int attendees) {
    this.venueCode = venueCode;
    this.date = date;
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    this.services = new ArrayList<>();
  }

  public Booking() {}

  public String getBookingRef() {
    return this.bookingReference;
  }

  public Date getDate() {
    return this.date;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public int getDay() {
    return this.date.getDay();
  }

  public int getAttendees() {
    return this.attendees;
  }

  public void addService(Service service) {
    this.services.add(service);
  }
}
