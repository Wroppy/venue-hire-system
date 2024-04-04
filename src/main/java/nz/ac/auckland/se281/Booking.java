package nz.ac.auckland.se281;

public class Booking {
  private String venueCode;
  private Date date;
  private String email;
  private int attendees;
  private String bookingReference;

  public Booking(String venueCode, Date date, String email, int attendees ) {
    this.venueCode = venueCode;
    this.date = date;
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
  }

  public String getBookingRef() {
    return this.bookingReference;
  }

  public Date getDate() {
    return this.date;
  }

  public String getVenueCode() {
    return this.venueCode;
  }

}
