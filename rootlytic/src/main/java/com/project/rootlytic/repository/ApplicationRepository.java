package com.project.rootlytic.repository;

import com.project.rootlytic.model.ApplicationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends MongoRepository<ApplicationModel,String> {
}
