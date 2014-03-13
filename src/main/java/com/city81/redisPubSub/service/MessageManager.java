package com.city81.redisPubSub.service;

import org.springframework.web.context.request.async.DeferredResult;

import com.city81.redisPubSub.model.Message;

public interface MessageManager {

    Message addMessage(String text) throws Exception;

    DeferredResult<Message> getNewMessage() throws Exception;

}