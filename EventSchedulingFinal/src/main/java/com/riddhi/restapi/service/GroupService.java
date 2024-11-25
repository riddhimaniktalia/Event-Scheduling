package com.riddhi.restapi.service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(String groupName, String groupDescription, User creator) {
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupDescription(groupDescription);
        group.setCreator(creator);
        return groupRepository.save(group);
    }
}

