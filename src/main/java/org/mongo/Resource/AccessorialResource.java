package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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



//    @PUT
//    @Path("/{id}")
//    public String updateAccessorials(
//            @PathParam("id") ObjectId accessorialsId,
//            AccessorialsRequest updateRequest) {
//
//        return accessorialService.updateAccessorials(
//                accessorialsId,
//                updateRequest.getName(),
//                updateRequest.getCode(),
//                updateRequest.getVisibleTo(),
//                updateRequest.getRateSource(),
//                updateRequest.getResources(),
//                updateRequest.getHybridSource());
//    }

    @DELETE
    @Path("/{id}")
    public String deleteAccessorials(@PathParam("id") ObjectId accessorialsId) {
        return accessorialService.deleteAccessorials(accessorialsId);
    }
    }

