package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
        // Define the allowable difference for minDistance calculation
        double minDistanceDeferrable = 0.01;

        // Find existing billing zones with the same name
        List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());

        // If zones with the same name exist
        if (!existingZones.isEmpty()) {
            // Sort existing zones by maxDistance in descending order
            existingZones.sort(Comparator.comparingDouble(BillingZone::getMaxDistance).reversed());

            // Calculate the required minDistance based on the previous maxDistance
            double requiredMinDistance = existingZones.get(0).getMaxDistance() + minDistanceDeferrable;

            // Check if the new minDistance adheres to the required value with a small tolerance
            if (Math.abs(newBillingZone.getMinDistance() - requiredMinDistance) > 0.0001) {
                // Return a BAD_REQUEST response with an error message
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid distance range. Min distance should be previous max distance + 0.01")
                        .build();
            }
        }

        // Set timestamps and persist the new billing zone
        newBillingZone.setUpdatedAt(new Date());
        newBillingZone.setCreatedAt(new Date());
        billingZoneRepository.persist(newBillingZone);

        // Return a CREATED response with the newBillingZone entity
        return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
    }
}
