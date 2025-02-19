package net.larichan.nlw_connect.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.larichan.nlw_connect.model.Event;
import net.larichan.nlw_connect.model.Subscription;
import net.larichan.nlw_connect.model.User;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Optional<Subscription> findByEventAndSubscribedUser(Event event, User user);
}