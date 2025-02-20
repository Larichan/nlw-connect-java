package net.larichan.nlw_connect.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.larichan.nlw_connect.dto.SubscriptionRankingByUser;
import net.larichan.nlw_connect.dto.SubscriptionRankingItem;
import net.larichan.nlw_connect.dto.SubscriptionResponse;
import net.larichan.nlw_connect.exception.EventNotFoundException;
import net.larichan.nlw_connect.exception.SubscriptionConflictException;
import net.larichan.nlw_connect.exception.UserIndicationNotFoundException;
import net.larichan.nlw_connect.model.Event;
import net.larichan.nlw_connect.model.Subscription;
import net.larichan.nlw_connect.model.User;
import net.larichan.nlw_connect.repository.EventRepository;
import net.larichan.nlw_connect.repository.SubscriptionRepository;
import net.larichan.nlw_connect.repository.UserRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public SubscriptionResponse createSubscription(String eventPrettyName, User user, Long userId) {
        Event savedEvent = eventRepository.findByPrettyName(eventPrettyName)
                .orElseThrow(() -> new EventNotFoundException(
                        "Evento " + eventPrettyName
                                + " não encontrado. Certifique-se de que o nome do evento está correto."));
        User savedUser = userRepository.findByUserEmail(user.getUserEmail()).orElseGet(() -> userRepository.save(user));

        subscriptionRepository.findByEventAndSubscribedUser(savedEvent, savedUser)
                .ifPresent(subscription -> {
                    throw new SubscriptionConflictException("Usuário " + savedUser.getUserEmail()
                            + " já está inscrito no evento " + savedEvent.getPrettyName());
                });

        Subscription newSub = new Subscription();

        if (userId != null) {
            newSub.setIndicationUser(
                    userRepository.findById(userId).orElseThrow(() -> new UserIndicationNotFoundException(
                            "Usuário da indicação não encontrado. Não foi possível se inscrever.")));
        }

        newSub.setEvent(savedEvent);
        newSub.setSubscribedUser(savedUser);
        newSub = subscriptionRepository.save(newSub);

        return new SubscriptionResponse(newSub.getSubscriptionNumber(), "http://eventcoder.com/subscription/"
                + newSub.getEvent().getPrettyName() + "/" + newSub.getSubscribedUser().getId());
    }

    public List<SubscriptionRankingItem> getCompleteRanking(String eventPrettyName) {
        Event savedEvent = eventRepository.findByPrettyName(eventPrettyName)
                .orElseThrow(() -> new EventNotFoundException(
                        "Ranking do evento " + eventPrettyName
                                + " não existe. Certifique-se de que o nome do evento está correto."));
        return subscriptionRepository.generateRanking(savedEvent.getId());
    }

    public SubscriptionRankingByUser getRankingByUser(String eventPrettyName, Long userId) {
        List<SubscriptionRankingItem> itens = getCompleteRanking(eventPrettyName);

        SubscriptionRankingItem result = itens.stream().filter(item -> item.userId().equals(userId)).findFirst()
                .orElseThrow(() -> new UserIndicationNotFoundException(
                        "Usuário " + userId + " não encontrado no ranking do evento " + eventPrettyName));

        // TODO: compararar com o uso do itens.indexOf(item) + 1
        Integer position = IntStream.range(0, itens.size()).filter(i -> itens.get(i).userId().equals(userId))
                .findFirst().getAsInt();

        return new SubscriptionRankingByUser(result, position + 1);
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