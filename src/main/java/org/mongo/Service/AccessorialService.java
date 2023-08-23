package org.mongo.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.mongo.Entity.Accessorial;
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
//        boolean accountExists = accessorialRepository.existsByAccountId(accountId);
//        if (!accountExists) {
//            return "Account ID not found.";
//        }
//
//        boolean nameExists = accessorialRepository.existsByNameAndCodeForAccount(name, code, accountId);
//        boolean codeExists = accessorialRepository.existsByCodeAndNameForAccount(code, name, accountId);
//
//        if (nameExists && codeExists) {
//            return "An accessorials entry with the same name and code already exists for this account.";
//        } else if (nameExists) {
//            return "An accessorials entry with the same name already exists for this account.";
//        } else if (codeExists) {
//            return "An accessorials entry with the same code already exists for this account.";
//        }

        Accessorial accessorialByName = Accessorial.find("name", name).firstResult();
        Accessorial accessorialByCode = Accessorial.find("code", code).firstResult();

        if (accessorialByName != null || accessorialByCode != null) {
            return "Invalid!! NAME and CODE must be unique! Please check and enter Again.";
        }
//        if (accessorialByCode!=null){
//            return "Invalid!! Code must be unique.";
//        }

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

//
//    public String updateAccessorials(ObjectId accessorialsId, String newName, String newCode,
//                                     Enums.VisisbleTo newVisibleTo, Enums.RateSourceType newRateSource,
//                                     Enums.ResourcesType newResources, Enums.HybridSource newHybridSource) {
//        Optional<Accessorials> accessorialsOptional = Optional.ofNullable(accessorialRepository.findById(accessorialsId));
//
//        if (!accessorialsOptional.isPresent()) {
//            return "Accessorials entry not found.";
//        }
//
//        Accessorials existingAccessorials = accessorialsOptional.get();
//
//
//        existingAccessorials.setName(newName);
//        existingAccessorials.setCode(newCode);
//        existingAccessorials.setVisibleto(newVisibleTo);
//        existingAccessorials.setRateSource(newRateSource);
//        existingAccessorials.setResources(newResources);
//        existingAccessorials.setHybridSource(newHybridSource);
//
//        accessorialRepository.persist(existingAccessorials);
//
//        return "Accessorials entry updated successfully.";
//    }


    public String deleteAccessorials(ObjectId accessorialsId) {
        Accessorial accessorial = Accessorial.findById(accessorialsId);

        if (accessorial == null) {
            return "Accessorials not found .";
        }
        Accessorial.deleteById(accessorialsId);
        return "Accessorials deleted successfully.";
    }











}
























