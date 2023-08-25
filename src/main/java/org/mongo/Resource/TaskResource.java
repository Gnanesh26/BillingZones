package org.mongo.Resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mongo.Entity.Task;
import org.mongo.Service.AuditService;


@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    AuditService auditService;




    @POST
    public Response create(Task task) {
        task.persist();
        auditService.createAuditEntry("Created", task.id, "Venkat");
        return Response.status(Response.Status.CREATED).build();
    }





    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Task updatedTask) {
        Task updatedEntity = auditService.updateTask(id, updatedTask);

        if (updatedEntity != null) {
            return Response.ok(updatedEntity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }




    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        boolean deleted = auditService.deleteTask(id);

        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}


