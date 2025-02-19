package net.larichan.nlw_connect.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.larichan.nlw_connect.dto.ErrorMessage;
import net.larichan.nlw_connect.exception.EventNotFoundException;
import net.larichan.nlw_connect.exception.SubscriptionConflictException;
import net.larichan.nlw_connect.exception.UserIndicationNotFoundException;
import net.larichan.nlw_connect.model.Subscription;
import net.larichan.nlw_connect.model.User;
import net.larichan.nlw_connect.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping({ "/{eventPrettyName}", "/{eventPrettyName}/{userId}" })
    public ResponseEntity<?> createSubscription(@PathVariable String eventPrettyName,
            @RequestBody User user, @PathVariable(required = false) Long userId) {
        try {
            return ResponseEntity.ok(subscriptionService.createSubscription(eventPrettyName, user, userId));
        } catch (EventNotFoundException | UserIndicationNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
        } catch (SubscriptionConflictException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(exception.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Integer id) {
        Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
        return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Subscription>> getAllSubscriptions() {
        Iterable<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Integer id,
            @RequestBody Subscription subscriptionDetails) {
        Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscriptionDetails);
        return updatedSubscription != null ? ResponseEntity.ok(updatedSubscription) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }
}