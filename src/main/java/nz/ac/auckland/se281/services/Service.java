package nz.ac.auckland.se281.services;

import nz.ac.auckland.se281.Types.CateringType;

public abstract class Service {
  protected int cost;
  protected CateringType cateringType;

  public Service(int cost, CateringType cateringType) {
    this.cost = cost;
    this.cateringType = cateringType;
  }

  @Override
  public abstract String toString();

  public int getCost() {
    return this.cost;
  }

  public String getStringCost() {
    return String.valueOf(this.cost); 
  }
}
