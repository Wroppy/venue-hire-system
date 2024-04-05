package nz.ac.auckland.se281.services;

import nz.ac.auckland.se281.MessageCli;
import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private CateringType cateringType;

  public CateringService(CateringType cateringType, int attendees) {
    super(attendees * cateringType.getCostPerPerson());
    this.cateringType = cateringType;
  }

  @Override
  public String toString() {
    return String.format("Catering (%s)", cateringType.getName());
  }

  @Override
  public String invoiceString() {
    String cateringName = this.cateringType.getName();
    String cost = this.getStringCost();
    return MessageCli.INVOICE_CONTENT_CATERING_ENTRY.getMessage(cateringName, cost);
  }

}
