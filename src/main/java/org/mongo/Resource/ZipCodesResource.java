package org.mongo.Resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.mongo.Entity.ZipCodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/zip-codes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ZipCodesResource {


    @POST
    public ZipCodes createZipCodes(ZipCodes zipCodes) {
        // Calculate zipCodesToCompare
        List<Integer> zipCodesToCompare = new ArrayList<>();

        // Loop through each input zip code
        for (String zipCode : zipCodes.getZipCodes()) {
            // Get the first three digits of the input zip code
            int firstThreeDigits = Integer.parseInt(zipCode.substring(0, 3)) * 100;

            // Generate a range of zip codes based on the first three digits
            for (int j = 0; j <= 100; j++) {
                zipCodesToCompare.add(firstThreeDigits + j);
            }
        }

        // Create a new ZipCodes instance with calculated data
        ZipCodes codes = new ZipCodes(
                zipCodes.getName(),
                zipCodes.getZoneType(),
                zipCodes.getMinCharge(),
                zipCodes.getZipCodes(),
                new Date(), // updatedAt
                new Date(), // createdAt
                zipCodesToCompare);

        codes.persist(); // Save to MongoDB

        return codes;
    }
}


