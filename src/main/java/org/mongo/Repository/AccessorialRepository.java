package org.mongo.Repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorial;

import java.util.List;

@ApplicationScoped
public class AccessorialRepository implements PanacheMongoRepository<Accessorial> {
    public List<Accessorial> findByCodeOrNameAndAccountId(String searchValue, ObjectId accountId) {
        return find("accountId = ?1 and code = ?2 or name = ?3)",
                accountId, searchValue, searchValue)
                .list();
    }


    public List<Accessorial> findByAccountId(ObjectId accountId) {
        return list("accountId", accountId);
    }


    public boolean existsByNameAndAccount(String name, ObjectId accountId) {
        return find("name = ?1 and accountId = ?2", name, accountId).count() > 0;
    }

    public boolean existsByCodeAndAccount(String code, ObjectId accountId) {
        return find("code = ?1 and accountId = ?2", code, accountId).count() > 0;
    }


//    public Accessorial findByCode(String code) {
//        return find("code", code).firstResult();
//    }


    public boolean existsByNameAndCodeForAccount(String name, String code, ObjectId accountId) {
        return count("name = ?1 and accountId = ?2", name, accountId) > 0;
    }

    public boolean existsByCodeAndNameForAccount(String code, String name, ObjectId accountId) {
        return count("code = ?1 and accountId = ?2", code, accountId) > 0;
    }


//    public boolean existsByAccountId(ObjectId accountId) {
//        return count("accountId", accountId) > 0;
//    }

}

