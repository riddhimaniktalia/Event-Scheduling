package com.riddhi.restapi.model;

@Entity
@Table(name = "group_users")
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group; // Linking to Group table

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Linking to User table

    @Column(updatable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    // Getters and Setters
}
