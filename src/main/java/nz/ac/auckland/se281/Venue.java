package nz.ac.auckland.se281;

public class Venue {
  private String venueName;
  private String venueCode;
  private Integer venueCapacity;
  private Integer venueHireFee;

  public Venue(String name, String code, Integer capacity, Integer hireFee) {
    this.venueName = name;
    this.venueCode = code;
    this.venueCapacity = capacity;
    this.venueHireFee = hireFee;
  }
}
