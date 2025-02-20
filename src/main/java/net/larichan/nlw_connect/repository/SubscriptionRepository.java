package net.larichan.nlw_connect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.larichan.nlw_connect.dto.SubscriptionRankingItem;
import net.larichan.nlw_connect.model.Event;
import net.larichan.nlw_connect.model.Subscription;
import net.larichan.nlw_connect.model.User;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Optional<Subscription> findByEventAndSubscribedUser(Event event, User user);

    @Query(value = "SELECT COUNT(subscription_number) as quantidade, indication_user_id, user_name"
            + " FROM tbl_subscription"
            + " INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id"
            + " WHERE event_id = :eventId"
            + " AND indication_user_id IS NOT NULL"
            + " GROUP BY indication_user_id"
            + " ORDER BY quantidade DESC", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Long eventId);
}