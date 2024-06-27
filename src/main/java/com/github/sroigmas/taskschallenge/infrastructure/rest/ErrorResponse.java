package com.github.sroigmas.taskschallenge.infrastructure.rest;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {}
