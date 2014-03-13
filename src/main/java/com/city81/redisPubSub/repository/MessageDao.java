package com.city81.redisPubSub.repository;

import com.city81.redisPubSub.model.Message;

public interface MessageDao {

    void save(Message message);
    
}
