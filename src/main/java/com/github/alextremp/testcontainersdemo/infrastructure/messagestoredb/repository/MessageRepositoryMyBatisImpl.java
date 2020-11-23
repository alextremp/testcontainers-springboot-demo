package com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository;

import com.github.alextremp.testcontainersdemo.domain.message.Message;
import com.github.alextremp.testcontainersdemo.domain.message.MessageFactory;
import com.github.alextremp.testcontainersdemo.domain.message.MessageRepository;
import com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis.MessageMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryMyBatisImpl implements MessageRepository {

  private final MessageMapper messageMapper;
  private final MessageFactory messageFactory;

  public MessageRepositoryMyBatisImpl(MessageMapper messageMapper, MessageFactory messageFactory) {
    this.messageMapper = messageMapper;
    this.messageFactory = messageFactory;
  }

  @Override
  public void save(Message message) {
    messageMapper.insert(message.getId().toString(), message.getText(), message.getCreated());
  }
}
