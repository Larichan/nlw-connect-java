package net.larichan.nlw_connect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.larichan.nlw_connect.dto.SubscriptionRankingByUser;
import net.larichan.nlw_connect.dto.SubscriptionRankingItem;
import net.larichan.nlw_connect.dto.SubscriptionResponse;
import net.larichan.nlw_connect.exception.EventNotFoundException;
import net.larichan.nlw_connect.exception.SubscriptionConflictException;
import net.larichan.nlw_connect.exception.UserIndicationNotFoundException;
import net.larichan.nlw_connect.model.User;
import net.larichan.nlw_connect.service.SubscriptionService;

@RestController
@RequestMapping(value = "/api/subscriptions", produces = "application/json")
@Tag(name = "Subscription")
@Validated
public class SubscriptionController {

    private static final int RANKING_FIRST_PLACES = 3;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping({ "/{eventPrettyName}", "/{eventPrettyName}/{userId}" })
    public ResponseEntity<SubscriptionResponse> createSubscription(@PathVariable String eventPrettyName,
            @Valid @RequestBody User user, @PathVariable(required = false) Long userId)
            throws EventNotFoundException, UserIndicationNotFoundException, SubscriptionConflictException {
        return ResponseEntity.ok(subscriptionService.createSubscription(eventPrettyName, user, userId));
    }

    @GetMapping("/{eventPrettyName}/ranking")
    public ResponseEntity<List<SubscriptionRankingItem>> getFirstPlacesRanking(@PathVariable String eventPrettyName)
            throws EventNotFoundException {
        return ResponseEntity
                .ok(subscriptionService.getCompleteRanking(eventPrettyName).subList(0, RANKING_FIRST_PLACES));
    }

    @GetMapping("/{eventPrettyName}/ranking/{userId}")
    public ResponseEntity<SubscriptionRankingByUser> getRankingPositionByUser(@PathVariable String eventPrettyName,
            @PathVariable Long userId) throws EventNotFoundException, UserIndicationNotFoundException {
        return ResponseEntity
                .ok(subscriptionService.getRankingByUser(eventPrettyName, userId));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Integer
    // id) {
    // Optional<Subscription> subscription =
    // subscriptionService.getSubscriptionById(id);
    // return subscription.map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    // @GetMapping
    // public ResponseEntity<Iterable<Subscription>> getAllSubscriptions() {
    // Iterable<Subscription> subscriptions =
    // subscriptionService.getAllSubscriptions();
    // return ResponseEntity.ok(subscriptions);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Subscription> updateSubscription(@PathVariable Integer
    // id,
    // @RequestBody Subscription subscriptionDetails) {
    // Subscription updatedSubscription = subscriptionService.updateSubscription(id,
    // subscriptionDetails);
    // return updatedSubscription != null ? ResponseEntity.ok(updatedSubscription) :
    // ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteSubscription(@PathVariable Integer id) {
    // subscriptionService.deleteSubscription(id);
    // return ResponseEntity.noContent().build();
    // }
}