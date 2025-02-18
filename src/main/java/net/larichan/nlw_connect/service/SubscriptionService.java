package net.larichan.nlw_connect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.larichan.nlw_connect.model.Subscription;
import net.larichan.nlw_connect.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public Iterable<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription updateSubscription(Integer id, Subscription subscriptionDetails) {
        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(id);
        if (optionalSubscription.isPresent()) {
            Subscription subscription = optionalSubscription.get();
            subscription.setSubscribedUser(subscriptionDetails.getSubscribedUser());
            subscription.setIndicationUser(subscriptionDetails.getIndicationUser());
            subscription.setEvent(subscriptionDetails.getEvent());
            return subscriptionRepository.save(subscription);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteSubscription(Integer id) {
        subscriptionRepository.deleteById(id);
    }
}