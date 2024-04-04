package nz.ac.auckland.se281.services;

import nz.ac.auckland.se281.Types.CateringType;

public abstract class Service {
  protected int cost;

  public Service(int cost) {
    this.cost = cost;
  }

  public Service() {

  }

  protected void setCost(int cost) {
    this.cost = cost;
  } 

  @Override
  public abstract String toString();
  
  public int getCost() {
    return this.cost;
  }

  // Returns string of cost
  public String getStringCost() {
    return String.valueOf(this.cost); 
  }
}
