package com.riddhi.restapi.controller;

import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riddhi.restapi.model.Group;
import com.riddhi.restapi.service.EventService;
import com.riddhi.restapi.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository; // Assuming this exists in Swetha's code

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Map<String, Object> payload) {
        String groupName = (String) payload.get("groupName");
        String groupDescription = (String) payload.get("groupDescription");
        Long creatorId = ((Number) payload.get("creatorId")).longValue();

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = groupService.createGroup(groupName, groupDescription, creator);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/{groupId}/events")
    public ResponseEntity<Event> createEvent(@PathVariable Long groupId, @RequestBody Map<String, Object> payload) {
        String eventTitle = (String) payload.get("eventTitle");
        LocalDate eventDate = LocalDate.parse((String) payload.get("eventDate"));
        LocalTime eventTime = LocalTime.parse((String) payload.get("eventTime"));
        String eventLocation = (String) payload.get("eventLocation");
        String eventDescription = (String) payload.get("eventDescription");

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Event event = eventService.createEvent(eventTitle, eventDate, eventTime, eventLocation, eventDescription, group);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/{groupId}/users")
    public ResponseEntity<Void> addUsersToGroup(@PathVariable Long groupId, @RequestBody List<Long> userIds) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        List<User> users = userRepository.findAllById(userIds);
        eventService.addUsersToGroup(group, users);
        return ResponseEntity.ok().build();
    }
}

