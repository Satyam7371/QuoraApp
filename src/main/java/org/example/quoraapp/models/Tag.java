package org.example.quoraapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Tag extends BaseModel{

    @ManyToMany(mappedBy = "followedTags")
    private Set<User> followers;
}
