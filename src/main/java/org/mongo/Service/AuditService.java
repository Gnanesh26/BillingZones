package org.mongo.Service;

import com.mongodb.client.MongoClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.mongo.Entity.Task;
import org.mongo.Entity.TaskAudit;
import java.time.LocalDateTime;

@ApplicationScoped
public class AuditService {

    @Inject
    MongoClient mongoClient;

    public void createAuditEntry(String action, ObjectId taskId, String username) {
        TaskAudit audit = new TaskAudit();
        audit.timestamp = LocalDateTime.now();
        audit.action = action;
        audit.taskId = taskId;
        audit.username = username;
        mongoClient.getDatabase("audit_log").getCollection("task_audit", TaskAudit.class).insertOne(audit);
    }

    public Task updateTask(String id, Task updatedTask) {
        Task existingTask = Task.findById(new ObjectId(id));

        if (existingTask != null) {
            existingTask.title = updatedTask.title;
            existingTask.description = updatedTask.description;
            existingTask.update();

            createAuditEntry("Updated", existingTask.id, "Venkat");

            return existingTask;
        } else {
            return null;
        }
    }



    public boolean deleteTask(String id) {
        Task existingTask = Task.findById(new ObjectId(id));

        if (existingTask != null) {
            existingTask.delete();

            createAuditEntry("Deleted", existingTask.id, "Venkat");

            return true;
        } else {
            return false;
        }
    }
}


