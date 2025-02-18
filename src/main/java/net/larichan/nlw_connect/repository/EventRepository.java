package net.larichan.nlw_connect.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.larichan.nlw_connect.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
    public Optional<Event> findByPrettyName(String prettyName);
}