package com.scheduleprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void update(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
