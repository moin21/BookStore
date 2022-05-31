package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.NamedStoredProcedureQuery;

@Data
@RequiredArgsConstructor
public class LoginDTO {
    private String loginID;
    private String password;
}
