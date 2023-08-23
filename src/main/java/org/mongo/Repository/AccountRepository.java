package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.mongo.Entity.Account;
@ApplicationScoped
public class AccountRepository implements PanacheMongoRepository<Account> {


}
