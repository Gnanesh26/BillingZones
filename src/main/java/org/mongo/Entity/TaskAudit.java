package org.mongo.Entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection="task_audit")
public class TaskAudit extends PanacheMongoEntity {
    public LocalDateTime timestamp;
    public String action;
    public ObjectId taskId;
    public String username;

    public TaskAudit(LocalDateTime timestamp, String action, ObjectId taskId, String username) {
        this.timestamp = timestamp;
        this.action = action;
        this.taskId = taskId;
        this.username = username;
    }

    public TaskAudit() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ObjectId getTaskId() {
        return taskId;
    }

    public void setTaskId(ObjectId taskId) {
        this.taskId = taskId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

