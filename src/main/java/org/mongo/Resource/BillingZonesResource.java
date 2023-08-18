package org.mongo.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.mongo.Entity.Account;
import org.mongo.Entity.BillingZone;
import org.mongo.Entity.BillingZones;
import org.mongo.Entity.ZipCodes;
import org.mongo.Repository.AccountRepository;
import org.mongo.Repository.BillingZonesRepository;

import java.util.*;

@Path("/billingZones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BillingZonesResource {

    @Inject
    BillingZonesRepository billingZonesRepository;

    @Inject
    AccountRepository accountRepository;




    @POST
    public Response createBillingZone(BillingZones newBillingZone) {
        if (newBillingZone.getAccountId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Account ID is required.")
                    .build();
        }

//        ObjectId accountId = null;
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
//
        double previousMaxDistance = 0.0;
        boolean isFirstEntry = true;

        if (!existingZones.isEmpty()) {
            existingZones.sort(Comparator.comparingDouble(BillingZones::getMaxDistance));
            previousMaxDistance = existingZones.get(existingZones.size() - 1).getMaxDistance();
            isFirstEntry = false;
        }

        if (!isFirstEntry) {
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

        billingZonesRepository.persist(newBillingZone);
//
        return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
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
        }
        catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid format for Zone ID.")
                    .build();
        }
    }






    //    @POST
//    public Response createZipCodes(ZipCodes zipCodes) {
//        Set<Integer> zipCodesToCompare = new LinkedHashSet<>();
//        Set<Integer> uniqueFirstThreeDigits = new LinkedHashSet<>();
//
//        for (String zipCode : zipCodes.getZipCodes()) {
//            int firstThreeDigits = Integer.parseInt(zipCode.substring(0, 3)) * 100;
//
//            if (!uniqueFirstThreeDigits.add(firstThreeDigits)) {
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity("Same zip-code !! Check again")
//                        .build();
//            }
//            for (int j = 0; j <= 100; j++) {
//                int arrayOfZipCodes = firstThreeDigits + j;
//                zipCodesToCompare.add(arrayOfZipCodes);
//            }
//        }
//        // Convert the set to a list for storage
//        List<Integer> zipCodesList = new ArrayList<>(zipCodesToCompare);
//
//        ZipCodes codes = new ZipCodes(
//                zipCodes.getName(),
//                zipCodes.getZoneType(),
//                zipCodes.getMinCharge(),
//                zipCodes.getZipCodes(),
//                new Date(),
//                new Date(),
//                zipCodesList);
//
//        codes.persist();
//
//        return Response.ok(codes).build();
//    }















}















