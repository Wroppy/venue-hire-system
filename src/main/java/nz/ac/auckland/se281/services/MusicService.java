package nz.ac.auckland.se281.services;

import nz.ac.auckland.se281.MessageCli;

public class MusicService extends Service {
  public MusicService() {
    super(500);
  }
  
  @Override
  public String toString() {
    return MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.getMessage(this.getStringCost());
  }
}
