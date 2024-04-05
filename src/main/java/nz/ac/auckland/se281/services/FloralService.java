package nz.ac.auckland.se281.services;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends Service {
  FloralType floralType;

  public FloralService(FloralType floralType) {
    super(floralType.getCost());
  }

  @Override
  public String invoiceString() {
    return MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.getMessage(
        this.floralType.getName(), this.getStringCost());
  }

  @Override
  public String toString() {
    return String.format("Floral (%s)", this.floralType.getName());
  }
}
