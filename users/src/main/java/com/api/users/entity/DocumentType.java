package com.api.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "document_type")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z]$", message = "Must contain exactly one alphabetic character.")
    private String type;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "documentType")
    private List<User> users;

    public DocumentType(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }
}
