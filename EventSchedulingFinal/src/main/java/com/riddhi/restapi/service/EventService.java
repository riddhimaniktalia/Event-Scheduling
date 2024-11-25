package com.riddhi.restapi.service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    public Event createEvent(String eventTitle, LocalDate eventDate, LocalTime eventTime,
                             String eventLocation, String eventDescription, Group group) {
        Event event = new Event();
        event.setEventTitle(eventTitle);
        event.setEventDate(eventDate);
        event.setEventTime(eventTime);
        event.setEventLocation(eventLocation);
        event.setEventDescription(eventDescription);
        event.setGroup(group);
        return eventRepository.save(event);
    }

    public void addUsersToGroup(Group group, List<User> users) {
        for (User user : users) {
            GroupUser groupUser = new GroupUser();
            groupUser.setGroup(group);
            groupUser.setUser(user);
            groupUserRepository.save(groupUser);
        }
    }
}
