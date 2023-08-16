package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.mongo.Entity.BillingZone;
import org.mongo.Repository.BillingZoneRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Path("/billingZones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BillingZoneResource {

    @Inject
    BillingZoneRepository billingZoneRepository;


    @GET
    @Operation(summary = "Get a list of zones")
    public List<BillingZone> getBills() {
        return BillingZone.listAll();
    }


    @POST
    public Response createBillingZone(BillingZone newBillingZone) {
        // Defining the maximum allowable difference in minimum distance
        double minDistanceDeferrable = 0.01;
        // Retrieve existing billing zones with the same name
        List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());

        if (!existingZones.isEmpty()) {
            // Get the maxDistance of the existing zone with the highest maxDistance
            double highestMaxDistance = existingZones.get(0).getMaxDistance();

            // Calculate the required minDistance
            double requiredMinDistance = highestMaxDistance + minDistanceDeferrable;

            // Check if the newBillingZone's minDistance is different from the required value
            if (newBillingZone.getMinDistance() != requiredMinDistance) {
                // Return a BAD_REQUEST response with an error message
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid distance range. Min distance should be previous max distance + 0.01")
                        .build();
            }
        }

        // Setting timestamps and persisting the new billing zone
        newBillingZone.setUpdatedAt(new Date());
        newBillingZone.setCreatedAt(new Date());
        billingZoneRepository.persist(newBillingZone);

        // Returning a CREATED response with the new billing zone entity
        return Response.status(Response.Status.CREATED)
                .entity(newBillingZone)
                .build();
    }

}





//    @DELETE
//    @Path("/{zoneId}")
//    public Response deleteBillingZone(@PathParam("zoneId") String zoneId) {
//        // Convert the zoneId to ObjectId
//        ObjectId objectId;
//        try {
//            objectId = new ObjectId(zoneId);
//        } catch (IllegalArgumentException e) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("Invalid zone ID format.")
//                    .build();
//        }
//
//        // Retrieve the billing zone with the provided zoneId
//        BillingZone zoneToDelete = BillingZone.findById(objectId);
//
//        if (zoneToDelete == null) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Zone with the specified ID not found.")
//                    .build();
//        }
//
//        // Delete the zone from the repository
//        zoneToDelete.delete();
//
//        return Response.status(Response.Status.OK)
//                .entity("Zone configuration deleted successfully.")
//                .build();
//    }
//}