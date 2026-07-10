package br.com.adriano.survey.review.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
@CompoundIndex(
        name = "idx_restaurant_created_at",
        def = "{'restaurantId': 1 'createdAt': -1}"
)
@CompoundIndex(
        name = "idx_order_unique",
        def = "{'orderId': 1}",
        unique = true
)
public class ReviewEntity {

    @Id
    private String id;
    private Long orderId;
    private Long restaurantId;
    private Long userId;
    private Integer rating;
    private Instant createdAt;
}
