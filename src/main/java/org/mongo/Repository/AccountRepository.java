package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.mongo.Entity.Account;

public class AccountRepository implements PanacheMongoRepository<Account> {
}
