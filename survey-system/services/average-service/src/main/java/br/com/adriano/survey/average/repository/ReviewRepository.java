package br.com.adriano.survey.average.repository;

import br.com.adriano.survey.average.entity.ReviewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<ReviewEntity, String> {

    boolean existsByOrderId(Long orderId);
}
