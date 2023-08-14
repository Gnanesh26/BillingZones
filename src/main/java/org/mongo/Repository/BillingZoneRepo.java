package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mongo.Entity.BillingZone;
@ApplicationScoped
public class BillingZoneRepo implements PanacheMongoRepository<BillingZone> {

}
