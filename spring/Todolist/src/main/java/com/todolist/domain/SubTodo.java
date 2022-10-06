package com.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_todo")
public class SubTodo extends BaseObject {
    @Column(name = "title")
    private String title;

    @Column(name = "notes")
    private String notes;

    @Column(name = "done")
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Todo parent;
}
