package com.city81.redisPubSub.aspects;

import com.city81.redisPubSub.model.Message;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageAspect extends AbstractRedisAspect {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MessageAspect.class);

    @Value("${messaging.redis.channel.messages}")
    private String channelName;

    @After("execution(* com.city81.redisPubSub.repository.MessageDao.save(..))")
    public void interceptMessage(JoinPoint joinPoint) {
            
        Message message = (Message) joinPoint.getArgs()[0];
    
        // this publishes the message
        this.redisTemplate.convertAndSend(channelName, message);

    }

}
