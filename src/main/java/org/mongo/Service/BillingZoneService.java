package org.mongo.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.Account;
import org.mongo.Entity.BillingZones;
import org.mongo.Repository.AccountRepository;
import org.mongo.Repository.BillingZonesRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
@ApplicationScoped
public class BillingZoneService {
    @Inject
    AccountRepository accountRepository;

    @Inject
    BillingZonesRepository billingZonesRepository;

    public Response createBillingZone(BillingZones newBillingZone) {
        if (newBillingZone.getAccountId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Account ID is required.")
                    .build();
        }

        ObjectId accountId;

        try {
            accountId = new ObjectId(String.valueOf(newBillingZone.getAccountId()));
        } catch (IllegalArgumentException ex) {
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

        List<BillingZones> existingZones = BillingZones.list("accountId", accountId);
        double previousMaxDistance = 0.0;
        boolean isFirst = true;

        if (!existingZones.isEmpty()) {
            existingZones.sort(Comparator.comparingDouble(BillingZones::getMaxDistance));
            previousMaxDistance = existingZones.get(existingZones.size() - 1).getMaxDistance();
            isFirst = false;
        }

        if (!isFirst) {
            double requiredMinDistance = previousMaxDistance + minDistanceDeferrable;
            if (Math.abs(newBillingZone.getMinDistance() - requiredMinDistance) > 0.01) {
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

        billingZonesRepository.persist(newBillingZone);

        return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
    }






    public Response deleteBillingZone(ObjectId zoneId) {
        BillingZones zoneToDelete = billingZonesRepository.findById(zoneId);
        if (zoneToDelete == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zone configuration not found.")
                    .build();
        }

        billingZonesRepository.delete(zoneToDelete);
        return Response.status(Response.Status.OK)
                .entity("Zone deleted successfully")
                .build();
    }
}


