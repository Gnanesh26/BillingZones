//package org.mongo.Resource;
//
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import org.mongo.Entity.ZipCodes;
//import org.mongo.Repository.AccountRepository;
//
//import java.util.*;
//
//@Path("/zip-codes")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class ZipCodesResource {
//
//
//    @Inject
//    AccountRepository accountRepository;
//
//
//    @POST
//    public Response createZipCodes(ZipCodes zipCodes) {
//        Set<Integer> zipCodesToCompare = new LinkedHashSet<>();
//        Set<Integer> uniqueFirstThreeDigits = new LinkedHashSet<>();
//        Set<Integer> uniqueFiveDigits = new LinkedHashSet<>();
//
//        for (String zipCode : zipCodes.getZipCodes()) {
//            int zipCodeValue;
//            try {
//                zipCodeValue = Integer.parseInt(zipCode);
//            } catch (NumberFormatException e) {
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity("Invalid zip-code format: " + zipCode)
//                        .build();
//            }
//
//            if (zipCode.length() == 3) {
//                int firstThreeDigits = zipCodeValue * 100;
//
//                if (!uniqueFirstThreeDigits.add(firstThreeDigits)) {
//                    return Response.status(Response.Status.BAD_REQUEST)
//                            .entity("Same first three digits zip-code !! Check again")
//                            .build();
//                }
//
//                for (int j = 0; j <= 100; j++) {
//                    int newZipCode = firstThreeDigits + j;
//                    zipCodesToCompare.add(newZipCode);
//                }
//            } else if (zipCode.length() == 5) {
//                if (!uniqueFiveDigits.add(zipCodeValue)) {
//                    return Response.status(Response.Status.BAD_REQUEST)
//                            .entity("Same five digits zip-code !! Check again")
//                            .build();
//                }
//
//                zipCodesToCompare.add(zipCodeValue);
//            } else {
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity("Invalid zip-code length , Please check again and enter!")
//                        .build();
//            }
//        }
//
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
//}
//
//
//
