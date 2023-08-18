package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mongo.Entity.ZipCodes;
@ApplicationScoped
public class ZipCodesRepository implements PanacheMongoRepository<ZipCodes> {

}
