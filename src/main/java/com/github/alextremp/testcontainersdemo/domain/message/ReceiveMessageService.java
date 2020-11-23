package com.github.alextremp.testcontainersdemo.domain.message;

public class ReceiveMessageService {

  private final MessageFactory messageFactory;
  private final MessageRepository messageRepository;

  public ReceiveMessageService(MessageFactory messageFactory, MessageRepository messageRepository) {
    this.messageFactory = messageFactory;
    this.messageRepository = messageRepository;
  }

  void receiveText(String text) {
    Message message = messageFactory.create(text);
    messageRepository.save(message);
  }
}
