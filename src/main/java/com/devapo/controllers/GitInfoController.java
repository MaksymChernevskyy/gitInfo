package com.devapo.controllers;

import com.devapo.entity.GitInfoOutput;
import com.devapo.exceptions.BadRequestException;
import com.devapo.services.GitOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/repositories")
public class GitInfoController {

    @Autowired
    private GitOutputService gitOutputService;

    public GitInfoController(GitOutputService repositoryService) {
        this.gitOutputService = repositoryService;
    }

    @GetMapping(value = "/{owner}/{repository}")
    @ExceptionHandler({BadRequestException.class})
    public GitInfoOutput getRepositoryInfo(@RequestParam(value = "owner") String owner, @PathVariable(value = "repository") String repository
    ) throws IOException {
        return gitOutputService.getRepositoryInfo(owner, repository);
    }
}
