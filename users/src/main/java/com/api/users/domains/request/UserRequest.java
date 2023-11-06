package com.api.users.domains.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class UserRequest {
    @NotNull @NotBlank
    @Pattern(regexp = "^[CP]$", message = "Only 'C' or 'P' are allowed")
    private String documentType;
    @NotNull @NotBlank
    @Pattern(regexp = "^[0-9-]{1,10}$", message = "It must contain between 1 and 10 numeric digits or hyphens (-).")
    private String document;
}
