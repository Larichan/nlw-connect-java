package net.larichan.nlw_connect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tbl_subscription")
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Integer subscriptionNumber;

    @NotNull(message = "Subscribed user is required")
    @ManyToOne
    @JoinColumn(name = "subscribed_user_id")
    private User subscribedUser;

    @ManyToOne
    @JoinColumn(name = "indication_user_id")
    private User indicationUser;

    @NotNull(message = "Event is required")
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
