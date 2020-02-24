package com.devapo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitInfoOutput {
    private String fullName;
    private String description;
    private String cloneUrl;
    @Min(value = 0)
    private int stars;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String createdAt;
}
