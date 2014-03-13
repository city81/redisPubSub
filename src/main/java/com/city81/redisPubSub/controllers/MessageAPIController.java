package com.city81.redisPubSub.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.city81.redisPubSub.model.Message;
import com.city81.redisPubSub.service.MessageManager;

@Controller
@RequestMapping("/messages")
public class MessageAPIController {

    @Inject
    private MessageManager messageManager;

    //
    // ADD A MESSAGE
    //
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public Message addMessage(
            @RequestParam(required = true) String text) throws Exception {
        return messageManager.addMessage(text);
    }
    
    //
    // LONG POLLING
    //
    @RequestMapping(value = "/watch", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public DeferredResult<Message> getNewMessage() throws Exception {
        return messageManager.getNewMessage();
    }
    
    
}
