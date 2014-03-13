package com.city81.redisPubSub.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.SerializationUtils;

import javax.inject.Inject;

import com.city81.redisPubSub.model.Message;
import com.city81.redisPubSub.repository.MessageDao;
import com.city81.redisPubSub.service.MessageManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.DeferredResult;

@Service(value = "messageManager")
@Transactional(propagation = Propagation.REQUIRED)
public class MessageManagerImpl implements MessageManager, MessageListener {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MessageManagerImpl.class);

    @Inject
    private MessageDao messageDao;

    @Value("${messaging.long.polling.timeout}")
    private Long deferredResultTimeout;
    
    private final List<DeferredResult<Message>> messageDeferredResultList
        = new ArrayList<DeferredResult<Message>>();
    
    public Message addMessage(String text) throws Exception {

        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException("Missing required param text");
        }

        // create message and save
        Message message = new Message();
        message.setText(text);

        messageDao.save(message);

        return message;
    }

    public void onMessage(
            org.springframework.data.redis.connection.Message redisMessage,
            byte[] pattern) {

        Message message = (Message) SerializationUtils.deserialize(redisMessage.getBody());
        
        // set the deferred results for the user
        for (DeferredResult<Message> deferredResult : this.messageDeferredResultList) {
                deferredResult.setResult(message);
        }

    }
    
    public DeferredResult<Message> getNewMessage() throws Exception {

        final DeferredResult<Message> deferredResult =
                new DeferredResult<Message>(deferredResultTimeout);

        deferredResult.onCompletion(new Runnable() {
            public void run() {
                messageDeferredResultList.remove(deferredResult);
            }
        });

        deferredResult.onTimeout(new Runnable() {
            public void run() {
                messageDeferredResultList.remove(deferredResult);
            }
        });

        messageDeferredResultList.add(deferredResult);

        return deferredResult;
    }
    
}
