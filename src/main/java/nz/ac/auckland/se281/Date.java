package nz.ac.auckland.se281;

public class Date {
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  @Override
  public String toString() {
    // Makes sure the month is is the format of DD/MM/YYYY
    return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
  }
}
