package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorial;
import org.mongo.Repository.AccessorialRepository;
import org.mongo.Request.AccessorialsRequest;
import org.mongo.Service.AccessorialService;

import java.util.List;

@Path("/accessorials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccessorialResource {

    @Inject
    AccessorialService accessorialService;

    @Inject
    AccessorialRepository accessorialRepository;


    @POST
    public String createAccessorials(AccessorialsRequest accessorialsRequest) {
        String resultMessage = accessorialService.createAccessorials(accessorialsRequest.getName(),
                accessorialsRequest.getCode(),
                accessorialsRequest.getVisibleTo(),
                accessorialsRequest.getRateSource(),
                accessorialsRequest.getResources(),
                accessorialsRequest.getHybridSource(),
                accessorialsRequest.getAccountId());

        return resultMessage;
    }

    @GET
    @Path("/search")
    public List<Accessorial> searchAccessorialsByNameOrCodeAndAccountId(
            @QueryParam("searchValue") String searchValue,
            @QueryParam("accountId") ObjectId accountId) {
        return accessorialService.getAccessorialsByNameOrCodeAndAccountId(searchValue, accountId);
    }

    @GET
    @Path("/{accountId}")
    public List<Accessorial> getAccessorialsByAccountId(@PathParam("accountId") ObjectId accountId) {
        return accessorialService.getAccessorialByAccountId(accountId);
    }


    @DELETE
    @Path("/{id}")
    public String deleteAccessorials(@PathParam("id") ObjectId accessorialsId) {
        return accessorialService.deleteAccessorials(accessorialsId);
    }


    @PUT
    @Path("/{id}")
    public Response updateAccessorial(
            @PathParam("id") ObjectId accessorialsId,
            AccessorialsRequest accessorialsRequest) {

        String resultMessage = accessorialService.updateAccessorial(accessorialsId, accessorialsRequest);

        if (resultMessage.equals("Accessorials updated successfully.")) {
            return Response.status(Response.Status.OK).entity(resultMessage).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(resultMessage).build();
        }
    }
}

