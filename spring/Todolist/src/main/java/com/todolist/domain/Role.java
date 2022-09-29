package com.todolist.domain;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

@XmlRootElement
@Table(
        name = "tbl_role"
)
@Entity
public class Role {
    @Transient
    private static final long serialVersionUID = 6318192070978248463L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "role_name",
            length = 150,
            nullable = false
    )
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }


    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String toString() {
        return String.format("Role: %s", this.name);
    }
}