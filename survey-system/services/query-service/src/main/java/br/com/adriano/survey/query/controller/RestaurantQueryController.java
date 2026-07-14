package br.com.adriano.survey.query.controller;

import br.com.adriano.survey.query.dto.RestaurantRatingResponse;
import br.com.adriano.survey.query.service.RestaurantRatingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantQueryController {

    private final RestaurantRatingQueryService service;

    @GetMapping("{restaurantId}")
    public RestaurantRatingResponse find(
            @PathVariable Long restaurantId
    ) {
        return service.findByRestaurant(restaurantId);
    }
}
