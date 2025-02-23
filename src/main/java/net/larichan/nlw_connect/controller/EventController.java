package net.larichan.nlw_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.larichan.nlw_connect.model.Event;
import net.larichan.nlw_connect.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Event> getEventById(@PathVariable Long id) {
    // return eventService.getEventById(id).map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    // @GetMapping
    // public ResponseEntity<Iterable<Event>> getAllEvents() {
    // Iterable<Event> events = eventService.getAllEvents();
    // return ResponseEntity.ok(events);
    // }

    @GetMapping("/prettyName/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable String prettyName) {
        return eventService.getEventByPrettyName(prettyName).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody
    // Event eventDetails) {
    // return ResponseEntity.ok(eventService.updateEvent(id, eventDetails));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
    // eventService.deleteEvent(id);
    // return ResponseEntity.noContent().build();
    // }
}
