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

  public Date(String date) {
    // Given a date in the form of DD/MM/YYYY, converts it the integer equivalent
    String[] dateOptions = date.split("/");
    int day = Integer.parseInt(dateOptions[0]);
    int month = Integer.parseInt(dateOptions[1]);
    int year = Integer.parseInt(dateOptions[2]);

    this.day = day;
    this.month = month;
    this.year = year;
  }

  @Override
  public String toString() {
    // Returns string in the format of DD/MM/YYYY
    return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
  }

  public boolean isDateBehind(Date otherDate) {
    // Returns true is the parsed in date is behind this instances date

    // Returns true is the year is behind, and false if the year is in ahead
    if (otherDate.year > this.year) {
      return false;
    } else if (otherDate.year < this.year) {
      return true;
    }

    // The year is the same
    // Checks for months
    if (otherDate.month > this.month) {
      return false;
    } else if (otherDate.month < this.month) {
      return true;
    }

    // The month is the same
    // Checks for the day
    // The same date is considered true
    return otherDate.day < this.day;
  }
}
