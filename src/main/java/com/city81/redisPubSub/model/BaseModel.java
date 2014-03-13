package com.city81.redisPubSub.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.GenerationType;
import java.util.Date;
import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable, Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

    protected BaseModel() {
        this.created = new Date();
    }

    public boolean hasValidId() {
        return id != null && id > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(this.getClass().isAssignableFrom(o.getClass()))) {
            return false;
        }

        BaseModel baseModel = (BaseModel) o;

        return id != null && id.equals(baseModel.id);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return -1;
        }
    }

    public int compareTo(Object o) {
        BaseModel baseModel = (BaseModel) o;
        return this.id.compareTo(baseModel.getId());
    }
}
