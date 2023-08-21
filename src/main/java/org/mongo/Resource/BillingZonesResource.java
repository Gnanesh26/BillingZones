package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.BillingZones;
import org.mongo.Service.BillingZoneService;


@Path("/billingZones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BillingZonesResource {


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
            return billingZoneService.deleteBillingZone(zoneId);
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid format for Zone ID.")
                    .build();
        }
    }


//    @PUT
//    public Response updateBillingZone(BillingZones updatedBillingZone) {
//      return billingZoneService.updateBillingZone(updatedBillingZone);
//    }
}