package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.Account;
import org.mongo.Entity.BillingZone;
import org.mongo.Repository.AccountRepository;
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

    @Inject
    AccountRepository accountRepository;


    @POST
    public Response createBillingZone(BillingZone newBillingZone) {
        if (newBillingZone.getAccountId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Account ID is required.")
                    .build();
        }

        ObjectId accountId = null;
        try {
            accountId = new ObjectId(String.valueOf(newBillingZone.getAccountId()));
        } catch (IllegalArgumentException ignored) {
            // Ignore the exception, accountId will remain null
        }

        if (accountId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Invalid format for Account ID.")
                    .build();
        }

        Account account = accountRepository.findById(accountId);
        if (account == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Account with the provided ID does not exist.")
                    .build();
        }

        double minDistanceDeferrable = 0.01;

        List<BillingZone> existingZones = BillingZone.list("accountId", accountId);

        double previousMaxDistance = 0.0; // Initialize the previousMaxDistance
        boolean isFirstEntry = true;

        if (!existingZones.isEmpty()) {
            existingZones.sort(Comparator.comparingDouble(BillingZone::getMaxDistance));
            previousMaxDistance = existingZones.get(existingZones.size() - 1).getMaxDistance();
            isFirstEntry = false;
        }

        if (!isFirstEntry) {
            double requiredMinDistance = previousMaxDistance + minDistanceDeferrable;
            if (Math.abs(newBillingZone.getMinDistance() - requiredMinDistance) > 0.0001) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid!!! Min distance must be exactly previous maxDistance + 0.01 only")
                        .build();
            }
        }

        if (newBillingZone.getMaxDistance() <= newBillingZone.getMinDistance()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Max distance must be greater than min distance")
                    .build();
        }

        // Check if the name is already used for the same accountId
        boolean nameExists = existingZones.stream()
                .anyMatch(zone -> zone.getName().equals(newBillingZone.getName()));

        if (nameExists) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Name must be unique for the same Account ID.")
                    .build();
        }

        newBillingZone.setUpdatedAt(new Date());
        newBillingZone.setCreatedAt(new Date());
        newBillingZone.setAccountId(accountId);

        billingZoneRepository.persist(newBillingZone);
        return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
    }
}
