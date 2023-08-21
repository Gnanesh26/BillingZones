package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.Account;
import org.mongo.Entity.BillingZones;
import org.mongo.Repository.AccountRepository;
import org.mongo.Repository.BillingZonesRepository;
import org.mongo.Service.BillingZoneService;

import java.util.*;

@Path("/billingZones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BillingZonesResource {

    @Inject
    BillingZonesRepository billingZonesRepository;

    @Inject
    AccountRepository accountRepository;


    @Inject
    BillingZoneService billingZoneService;

    @POST
    public Response createBillingZone(BillingZones newBillingZone) {
        return billingZoneService.createBillingZone(newBillingZone);
    }





    @DELETE
    @Path("/{zoneId}")
    public Response deleteBillingZone(@PathParam("zoneId") String zoneIdStr) {
        try {
            ObjectId zoneId = new ObjectId(zoneIdStr);

            BillingZones zoneToDelete = billingZonesRepository.findById(zoneId);
            if (zoneToDelete == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Zone configuration not found.")
                        .build();
            }

            billingZonesRepository.delete(zoneToDelete);
            return Response.status(Response.Status.OK)
                    .entity("Zone deleted successfully").
                    build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid format for Zone ID.")
                    .build();
        }
    }
}















