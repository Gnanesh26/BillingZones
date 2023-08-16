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
import java.util.Set;
import java.util.stream.Collectors;

@Path("/zip-codes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ZipCodesResource {


    @POST
    public ZipCodes createZipCodes(ZipCodes zipCodes) {

        List<Integer> zipCodesToCompare = new ArrayList<>();

        for (String zipCode : zipCodes.getZipCodes()) {

            int firstThreeDigits = Integer.parseInt(zipCode.substring(0, 3)) * 100;

            for (int j = 0; j <= 100; j++) {
                zipCodesToCompare.add(firstThreeDigits + j);
            }
        }

        ZipCodes codes = new ZipCodes(
                zipCodes.getName(),
                zipCodes.getZoneType(),
                zipCodes.getMinCharge(),
                zipCodes.getZipCodes(),
                new Date(),
                new Date(),
                zipCodesToCompare);

        codes.persist();

        return codes;
    }
}


