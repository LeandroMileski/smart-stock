package dev.mileski.smartstock.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRequestRepository extends MongoRepository<PurchaseRequestEntity, String> {

    // Custom query methods can be defined here if needed
    // For example, to find purchase requests by status:
    // List<PurchaseRequest> findByStatus(String status);

}
