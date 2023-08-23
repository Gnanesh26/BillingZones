package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorials;
import org.mongo.common.Enums;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccessorialRepository implements PanacheMongoRepository<Accessorials> {
    public List<Accessorials> findByCodeOrNameAndAccountId(String searchValue, ObjectId accountId) {
        return find("accountId = ?1 and code = ?2 or name = ?3)",
                accountId, searchValue, searchValue)
                .list();
    }

    public List<Accessorials> findByAccountId(ObjectId accountId) {
        return list("accountId", accountId);
    }

    public boolean existsByNameAndCodeForAccount(String name, String code, ObjectId accountId) {
        return count("name = ?1 and accountId = ?2", name, accountId) > 0;
    }

    public boolean existsByCodeAndNameForAccount(String code, String name, ObjectId accountId) {
        return count("code = ?1 and accountId = ?2", code, accountId) > 0;
    }


    public boolean existsByAccountId(ObjectId accountId) {
        return count("accountId", accountId) > 0;
    }
}

