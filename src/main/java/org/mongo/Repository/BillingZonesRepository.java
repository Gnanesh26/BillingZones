package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mongo.Entity.BillingZones;

@ApplicationScoped
public class BillingZonesRepository implements PanacheMongoRepository<BillingZones> {
}
