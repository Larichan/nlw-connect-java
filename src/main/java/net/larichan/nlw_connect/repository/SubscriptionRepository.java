package net.larichan.nlw_connect.repository;

import org.springframework.data.repository.CrudRepository;
import net.larichan.nlw_connect.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}