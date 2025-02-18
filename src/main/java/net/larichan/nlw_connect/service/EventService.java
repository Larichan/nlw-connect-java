package net.larichan.nlw_connect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.larichan.nlw_connect.model.Event;
import net.larichan.nlw_connect.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        event.setPrettyName(event.getTitle().toLowerCase().replace(" ", "-"));
        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Iterable<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventByPrettyName(String prettyName) {
        return eventRepository.findByPrettyName(prettyName);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setTitle(eventDetails.getTitle());
            event.setPrettyName(eventDetails.getPrettyName());
            event.setLocation(eventDetails.getLocation());
            event.setPrice(eventDetails.getPrice());
            event.setStartDate(eventDetails.getStartDate());
            event.setEndDate(eventDetails.getEndDate());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            return eventRepository.save(event);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}