package com.ml.challenge.controller;

import com.ml.challenge.controller.documentation.TopSecretResource;
import com.ml.challenge.dto.TopSecretRequest;
import com.ml.challenge.dto.TopSecretResponse;
import com.ml.challenge.exception.GenericNotFountException;
import com.ml.challenge.service.TopSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.ml.challenge.enums.ErrorCode.NOT_ENOUGH_INFORMATION;

@RequiredArgsConstructor
@RestController
@RequestMapping("topsecret")
public class TopSecretController implements TopSecretResource {

    private final TopSecretService topSecretService;

    @PostMapping
    @Override
    public ResponseEntity<TopSecretResponse> topSecret(
            @NotNull @Valid @RequestBody TopSecretRequest request) {

        return topSecretService.topSecret(request)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new GenericNotFountException(NOT_ENOUGH_INFORMATION));
    }
}
