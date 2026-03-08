package com.command.itdaserver.domain.post.domain;

import com.command.itdaserver.global.entity.BaseIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag extends BaseIdEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Hashtag(String name) {
        this.name = name;
    }
}
