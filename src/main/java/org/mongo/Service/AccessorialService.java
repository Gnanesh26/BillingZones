package org.mongo.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorial;
import org.mongo.Entity.Account;
import org.mongo.Repository.AccessorialRepository;
import org.mongo.Repository.AccountRepository;
import org.mongo.Request.AccessorialsRequest;
import org.mongo.common.Enums;

import java.util.List;



@ApplicationScoped
public class AccessorialService {
    @Inject
    AccessorialRepository accessorialRepository;

    AccountRepository accountRepository;

    public String createAccessorials(String name, String code, Enums.VisisbleTo visibleTo,
                                     Enums.RateSourceType rateSource, Enums.ResourcesType resources, Enums.HybridSource hybridSource,
                                     ObjectId accountId) {
//        Account accountExists = accountRepository.findById(accountId);
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

//        Accessorial accessorialByName = Accessorial.find("name", name).firstResult();
//        Accessorial accessorialByCode = Accessorial.find("code", code).firstResult();
//
//        if (accessorialByName != null || accessorialByCode != null) {
//            return "Invalid!! NAME and CODE must be unique! Please check and enter Again.";
//        }
////        if (accessorialByCode!=null){
////            return "Invalid!! Code must be unique.";
////        }

        Accessorial newAccessorial = new Accessorial(name, code, visibleTo, rateSource, resources, hybridSource, accountId);
        accessorialRepository.persist(newAccessorial);
        return "Accessorials entry created successfully.";
    }






    public List<Accessorial> getAccessorialsByNameOrCodeAndAccountId(String searchValue, ObjectId accountId) {
        return accessorialRepository.findByCodeOrNameAndAccountId(searchValue, accountId);
    }






    public List<Accessorial> getAccessorialByAccountId(ObjectId accountId) {
        return accessorialRepository.findByAccountId(accountId);
    }






    public String deleteAccessorials(ObjectId accessorialsId) {
        Accessorial accessorial = Accessorial.findById(accessorialsId);

        if (accessorial == null) {
            return "Accessorials not found .";
        }
        Accessorial.deleteById(accessorialsId);
        return "Accessorials deleted successfully.";
    }


    public String updateAccessorial(ObjectId accessorialsId, AccessorialsRequest accessorialsRequest) {
        Accessorial existingAccessorial = Accessorial.findById(accessorialsId);

        if (existingAccessorial == null) {
            return "Accessorials not found.";
        }

        // Check for unique name and code within the same account
        if (!existingAccessorial.getName().equals(accessorialsRequest.getName()) &&
                accessorialRepository.existsByNameAndAccount(accessorialsRequest.getName(), existingAccessorial.getAccountId())) {
            return "An Accessorial with the same name already exists for this account.";
        }

        if (!existingAccessorial.getCode().equals(accessorialsRequest.getCode()) &&
                accessorialRepository.existsByCodeAndAccount(accessorialsRequest.getCode(), existingAccessorial.getAccountId())) {
            return "An Accessorial with the same code already exists for this account.";
        }



//    public String updateAccessorial(ObjectId accessorialsId, AccessorialsRequest accessorialsRequest) {
//        Accessorial existingAccessorial = Accessorial.findById(accessorialsId);
//
//        if (existingAccessorial == null) {
//            return "Accessorials not found.";
//        }

        existingAccessorial.setName(accessorialsRequest.getName());
        existingAccessorial.setCode(accessorialsRequest.getCode());
        existingAccessorial.setVisibleto(accessorialsRequest.getVisibleTo());
        existingAccessorial.setRateSource(accessorialsRequest.getRateSource());
        existingAccessorial.setResources(accessorialsRequest.getResources());
        existingAccessorial.setHybridSource(accessorialsRequest.getHybridSource());
        existingAccessorial.update();
        return "Accessorials updated successfully.";
    }
}






































