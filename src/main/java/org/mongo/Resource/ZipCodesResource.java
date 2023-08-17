package org.mongo.Resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mongo.Entity.ZipCodes;

import java.util.*;

@Path("/zip-codes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ZipCodesResource {


//
//    @POST
//    public ZipCodes createZipCodes(ZipCodes zipCodes) {
//
//        List<Integer> zipCodesToCompare = new ArrayList<>();
//
//        for (String zipCode : zipCodes.getZipCodes()) {
//
//            int firstThreeDigits = Integer.parseInt(zipCode.substring(0, 3)) * 100;
//
//            for (int j = 0; j <= 100; j++) {
//                zipCodesToCompare.add(firstThreeDigits + j);
//            }
//        }
//
//        ZipCodes codes = new ZipCodes(
//                zipCodes.getName(),
//                zipCodes.getZoneType(),
//                zipCodes.getMinCharge(),
//                zipCodes.getZipCodes(),
//                new Date(),
//                new Date(),
//                zipCodesToCompare);
//
//        codes.persist();
//
//        return codes;
//    }
//}


    @POST
    public Response createZipCodes(ZipCodes zipCodes) {
        Set<Integer> zipCodesToCompare = new LinkedHashSet<>();
        Set<Integer> uniqueFirstThreeDigits = new LinkedHashSet<>();

        for (String zipCode : zipCodes.getZipCodes()) {
            int firstThreeDigits = Integer.parseInt(zipCode.substring(0, 3)) * 100;

            if (!uniqueFirstThreeDigits.add(firstThreeDigits)) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Same zip-code !! Check again")
                        .build();
            }


            for (int j = 0; j <= 100; j++) {
                int arrayOfZipCodes = firstThreeDigits + j;
                zipCodesToCompare.add(arrayOfZipCodes);
            }
        }


        // Convert the set to a list for storage
        List<Integer> zipCodesList = new ArrayList<>(zipCodesToCompare);

        ZipCodes codes = new ZipCodes(
                zipCodes.getName(),
                zipCodes.getZoneType(),
                zipCodes.getMinCharge(),
                zipCodes.getZipCodes(),
                new Date(),
                new Date(),
                zipCodesList);

        codes.persist();

        return Response.ok(codes).build();
    }
}


