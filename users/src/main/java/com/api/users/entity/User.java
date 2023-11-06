package com.api.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[0-9-]{1,10}$", message = "It must contain between 1 and 10 numeric digits or hyphens (-).")
    private String document;
    private String firstName;
    private String middleName;
    private String firstSurname;
    private String secondSurname;
    @Pattern(regexp = "^[0-9+]{1,13}$", message = "It must contain between 1 and 13 numeric digits or the plus sign (+).")
    private String phoneNumber;
    private String address;
    private String city;
    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;
}
