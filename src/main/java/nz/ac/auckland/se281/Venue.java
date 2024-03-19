package nz.ac.auckland.se281;

public class Venue {
  private String name;
  private String code;
  private Integer capacity;
  private Integer hireFee;

  public Venue(String name, String code, Integer capacity, Integer hireFee) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }

  public Integer getCapcity() {
    return this.capacity;
  }

  public Integer getHireFee(){
    return this.hireFee;
  }
}
