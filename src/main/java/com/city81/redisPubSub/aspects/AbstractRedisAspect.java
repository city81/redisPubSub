package com.city81.redisPubSub.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.inject.Inject;

public abstract class AbstractRedisAspect {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(AbstractRedisAspect.class);

    @Inject
    protected RedisTemplate<String, String> redisTemplate;

}
