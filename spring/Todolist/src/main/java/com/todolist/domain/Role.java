package com.todolist.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@Entity
public class Role implements GrantedAuthority {
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

    @Override
    public String getAuthority() {
        return this.name;
    }
}