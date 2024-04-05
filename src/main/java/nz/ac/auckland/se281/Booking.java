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
  private Date dateOfBooking;

  public Booking(String venueCode, Date date, String email, int attendees, Date bookingDate) {
    this.venueCode = venueCode;
    this.date = date;
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    this.services = new ArrayList<>();
    this.dateOfBooking = bookingDate;
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

  private int getTotalBookingPrice(int venueCost) {
    int total = venueCost;
    for (Service service : this.services) {
      total += service.getCost();
    }
    return total;
  }

  public String getInvoice(String venueName, int venueHireFee) {
    String message = "";

    String venueHireFeeString = String.valueOf(venueHireFee);

    // Adds the header to the message
    message +=
        MessageCli.INVOICE_CONTENT_TOP_HALF.getMessage(
            this.bookingReference,
            this.email,
            this.dateOfBooking.toString(),
            this.date.toString(),
            String.valueOf(this.attendees),
            venueName);

    // TODO Add Venue Fee
    message += "\n" +  MessageCli.INVOICE_CONTENT_VENUE_FEE.getMessage(venueHireFeeString);

    // TODO Add catering fee

    // TODO Add music fee

    // TODO Add Floral Fee
    

    String totalCostString = String.valueOf(this.getTotalBookingPrice(venueHireFee));
    message += MessageCli.INVOICE_CONTENT_BOTTOM_HALF.getMessage(totalCostString);

    return message;
  }
}
