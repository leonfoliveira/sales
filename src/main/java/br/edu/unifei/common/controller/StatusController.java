package br.edu.unifei.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@Tag(name = "Status")
public class StatusController {
    @GetMapping
    @Operation(summary = "Get server status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public String getStatus() {
        return "OK";
    }
}
