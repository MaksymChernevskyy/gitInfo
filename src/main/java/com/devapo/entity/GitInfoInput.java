package com.devapo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitInfoInput {
    @NotBlank(message = "User cannot be blank")
    private String user;
    @NotBlank(message = "Repository name cannot be blank")
    private String repositoryName;
}
