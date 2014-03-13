package com.city81.redisPubSub.repository.impl;

import org.springframework.stereotype.Repository;

import com.city81.redisPubSub.model.Message;
import com.city81.redisPubSub.repository.MessageDao;

@Repository(value = "messageDao")
public class MessageDaoImpl implements MessageDao {

    public void save(Message message) {
        // TODO
    }

}
