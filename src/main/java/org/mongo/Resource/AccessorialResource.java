package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorials;
import org.mongo.Request.AccessorialsRequest;
import org.mongo.Service.AccessorialService;
import org.mongo.common.Enums;

import java.util.List;

@Path("/accessorials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccessorialResource {

    @Inject
    AccessorialService accessorialService;


    @POST
    public String createAccessorials(AccessorialsRequest accessorialsRequest) {
        String resultMessage = accessorialService.createAccessorials(accessorialsRequest.getName(),
                accessorialsRequest.getCode(),
                accessorialsRequest.getVisibleTo(),
                accessorialsRequest.getRateSource(),
                accessorialsRequest.getResources(),
                accessorialsRequest.getHybridSource(),
                accessorialsRequest.getAccountId()
        );

        return resultMessage;
    }

    @GET
    @Path("/search")
    public List<Accessorials> searchAccessorialsByNameOrCodeAndAccountId(
            @QueryParam("searchValue") String searchValue,
            @QueryParam("accountId") ObjectId accountId) {
        return accessorialService.getAccessorialsByNameOrCodeAndAccountId(searchValue, accountId);
    }

    @GET
    @Path("/{accountId}")
    public List<Accessorials> getAccessorialsByAccountId(@PathParam("accountId") ObjectId accountId) {
        return accessorialService.getAccessorialByAccountId(accountId);
    }
















}
