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
    //
    @Inject
    BillingZoneRepository billingZoneRepository;

    //
//
    @GET
    @Operation(summary = "Get a list of zones")
    public List<BillingZone> getBills() {
        return BillingZone.listAll();
    }

    //
//
//    @POST
//    public Response createBillingZone(BillingZone newBillingZone) {
//        double minDistanceDeferrable = 0.01;
//        double tolerance = 0.0001;
//
//        List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());
//
//        // Sort the existing zones by maxDistance in ascending order
//        existingZones.sort(Comparator.comparingDouble(BillingZone::getMaxDistance));
//
//        if (!existingZones.isEmpty()) {
//            double previousMaxDistance = existingZones.get(existingZones.size() - 1).getMaxDistance();
//            double requiredMinDistance = previousMaxDistance + minDistanceDeferrable;
//
//            double inputMinDistance = newBillingZone.getMinDistance();
//
//            if (Math.abs(inputMinDistance - requiredMinDistance) <= tolerance) {
//                newBillingZone.setMinDistance(requiredMinDistance);
//                updateAndPersistBillingZone(newBillingZone);
//                return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
//            } else {
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity("Invalid!!! Min distance must be approximately previous maxDistance + 0.01 only")
//                        .build();
//            }
//        } else {
//            if (existingZones.isEmpty()) {
//                newBillingZone.setUpdatedAt(new Date());
//                newBillingZone.setCreatedAt(new Date());
//            }
//
//            updateAndPersistBillingZone(newBillingZone);
//            return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
//        }
//    }
//
//    private void updateAndPersistBillingZone(BillingZone billingZone) {
//        billingZoneRepository.persist(billingZone);
//    }
//}
//
//
//
//
//    @DELETE
//    @Path("/{zoneId}")
//    public Response deleteBillingZone(@PathParam("zoneId") String zoneId) {
//        ObjectId objectId;
//        try {
//            objectId = new ObjectId(zoneId);
//        } catch (IllegalArgumentException e) {
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("Invalid zone ID format.")
//                    .build();
//        }
//
//        BillingZone zoneToDelete = BillingZone.findById(objectId);
//
//        if (zoneToDelete == null) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Zone with the specified ID not found.")
//                    .build();
//        }
//        zoneToDelete.delete();
//
//        return Response.status(Response.Status.OK)
//                .entity("Zone configuration deleted successfully.")
//                .build();
//    }
//}


    @POST
    public Response createBillingZone(BillingZone newBillingZone) {
        double minDistanceDeferrable = 0.01;

        if (newBillingZone.getMaxDistance() > newBillingZone.getMinDistance()) {
            List<BillingZone> existingZones = BillingZone.list("name", newBillingZone.getName());

            if (!existingZones.isEmpty()) {
                existingZones.sort(Comparator.comparingDouble(BillingZone::getMaxDistance));

                double previousMaxDistance = existingZones.get(existingZones.size() - 1).getMaxDistance();
                double requiredMinDistance = previousMaxDistance + minDistanceDeferrable;

                if (Math.abs(newBillingZone.getMinDistance() - requiredMinDistance) <= 0.0001) {
                    newBillingZone.setMinDistance(requiredMinDistance);

                    newBillingZone.setUpdatedAt(new Date());
                    newBillingZone.setCreatedAt(new Date());

                    billingZoneRepository.persist(newBillingZone);
                    return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Invalid!!! Min distance must be exactly previous maxDistance + 0.01 only")
                            .build();
                }
            } else {
                newBillingZone.setUpdatedAt(new Date());
                newBillingZone.setCreatedAt(new Date());

                billingZoneRepository.persist(newBillingZone);
                return Response.status(Response.Status.CREATED).entity(newBillingZone).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid!!! Max distance must be greater than min distance")
                    .build();
        }
    }
}
