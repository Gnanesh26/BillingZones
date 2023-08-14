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
        // Defining the maximum allowable difference in minimum distance
        double minDistanceDeferrable = 0.01;

        // Retrieving existing billing zones with the same name
        List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());


        // Checking if there are existing zones with the same name
        if (!existingZones.isEmpty() && newBillingZone.getMinDistance() != existingZones.get(0).getMaxDistance() + minDistanceDeferrable) {
            // Returning a BAD_REQUEST response if the minimum distance doesn't meet requirements
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid distance range. Min distance should be previous max distance + 0.01")
                    .build();
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