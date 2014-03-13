package com.city81.redisPubSub.model;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity(name = "message")
public class Message extends BaseModel {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
