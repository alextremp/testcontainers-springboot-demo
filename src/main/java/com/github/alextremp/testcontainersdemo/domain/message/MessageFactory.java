package com.github.alextremp.testcontainersdemo.domain.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageFactory {

  public Message create(String text) {
    return new Message(UUID.randomUUID(), text, LocalDateTime.now());
  }

  public Message create(String id, String text, LocalDateTime created) {
    return new Message(UUID.fromString(id), text, created);
  }
}
