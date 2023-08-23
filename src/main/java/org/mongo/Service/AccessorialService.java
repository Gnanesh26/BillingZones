package org.mongo.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorials;
import org.mongo.Repository.AccessorialRepository;
import org.mongo.common.Enums;

import java.util.List;


@ApplicationScoped
public class AccessorialService {
    @Inject
    AccessorialRepository accessorialRepository;

    public String createAccessorials(String name, String code, Enums.VisisbleTo visibleTo,
                                     Enums.RateSourceType rateSource, Enums.ResourcesType resources, Enums.HybridSource hybridSource,
                                     ObjectId accountId) {
        boolean accountExists = accessorialRepository.existsByAccountId(accountId);
        if (!accountExists) {
            return "Account ID not found.";
        }

        boolean nameExists = accessorialRepository.existsByNameAndCodeForAccount(name, code, accountId);
        boolean codeExists = accessorialRepository.existsByCodeAndNameForAccount(code, name, accountId);

        if (nameExists && codeExists) {
            return "An accessorials entry with the same name and code already exists for this account.";
        } else if (nameExists) {
            return "An accessorials entry with the same name already exists for this account.";
        } else if (codeExists) {
            return "An accessorials entry with the same code already exists for this account.";
        }

        Accessorials newAccessorials = new Accessorials(name, code, visibleTo, rateSource, resources, hybridSource, accountId);
        accessorialRepository.persist(newAccessorials);
        return "Accessorials entry created successfully.";
    }

    public List<Accessorials> getAccessorialsByNameOrCodeAndAccountId(String searchValue, ObjectId accountId) {
        return accessorialRepository.findByCodeOrNameAndAccountId(searchValue, accountId);
    }


    public List<Accessorials> getAccessorialByAccountId(ObjectId accountId) {
        return accessorialRepository.findByAccountId(accountId);
    }





}