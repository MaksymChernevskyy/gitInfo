package com.devapo.services;

import com.devapo.entity.GitInfoOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonConverter {

    private final ObjectMapper mapper = new ObjectMapper();

    GitInfoOutput convert(String json) throws JsonProcessingException {
        final JsonNode node = mapper.readTree(json);
        return GitInfoOutput.builder()
                .fullName(node.required("full_name").asText())
                .description(node.required("description").asText())
                .cloneUrl(node.required("clone_url").asText())
                .createdAt(node.required("created_at").asText())
                .stars(node.required("stargazers_count").asInt())
                .build();
    }
}
