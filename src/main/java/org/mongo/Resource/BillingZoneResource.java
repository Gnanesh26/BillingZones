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
            double minDistanceDeferrable = 0.01;

            // Find existing zones with the same name and order by maxDistance descending
            List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());

            if (!existingZones.isEmpty()) {
                // Sort the existing zones by maxDistance in descending order
                existingZones.sort(Comparator.comparingDouble(BillingZone::getMaxDistance).reversed());

                // Calculate the required minimum distance based on the previous max distance
                double previousMaxDistance = existingZones.get(0).getMaxDistance();
                double requiredMinDistance = previousMaxDistance + minDistanceDeferrable;

                if (Math.abs(newBillingZone.getMinDistance() - requiredMinDistance) <= 0.0001) {
                    // Update timestamps and persist the newBillingZone
                    newBillingZone.setUpdatedAt(new Date());
                    newBillingZone.setCreatedAt(new Date());

                    billingZoneRepository.persist(newBillingZone);
                    // Return a success response with the newly created billing zone
                    return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
                } else {
                    // Return a bad request response if the minimum distance requirement is not met
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Invalid distance range for the same name. Min distance must be exactly previous max distance + 0.01")
                            .build();
                }
            } else {
                // If no existing zones with the same name, update timestamps and persist the newBillingZone
                newBillingZone.setUpdatedAt(new Date());
                newBillingZone.setCreatedAt(new Date());

                billingZoneRepository.persist(newBillingZone);

                // Return a success response with the newly created billing zone
                return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
            }
        }




    @DELETE
    @Path("/{zoneId}")
    public Response deleteBillingZone(@PathParam("zoneId") String zoneId) {
        // Convert the zoneId to ObjectId
        ObjectId objectId;
        try {
            objectId = new ObjectId(zoneId);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid zone ID format.")
                    .build();
        }

        // Retrieve the billing zone with the provided zoneId
        BillingZone zoneToDelete = BillingZone.findById(objectId);

        if (zoneToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zone with the specified ID not found.")
                    .build();
        }

        // Delete the zone from the repository
        zoneToDelete.delete();

        return Response.status(Response.Status.OK)
                .entity("Zone configuration deleted successfully.")
                .build();
    }
}