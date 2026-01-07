package org.example.quoraapp.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Question extends BaseModel{

    private String title;
    private String content;
}
