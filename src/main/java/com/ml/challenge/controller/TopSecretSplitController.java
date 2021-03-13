package com.ml.challenge.controller;

import com.ml.challenge.controller.documentation.TopSecretSplitResource;
import com.ml.challenge.dto.TopSecretResponse;
import com.ml.challenge.dto.TopSecretSplitRequest;
import com.ml.challenge.dto.TopSecretSplitResponse;
import com.ml.challenge.exception.GenericNotFountException;
import com.ml.challenge.service.TopSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.ml.challenge.enums.ErrorCode.NOT_ENOUGH_INFORMATION;
import static com.ml.challenge.enums.ErrorCode.SATELLITE_NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("topsecret_split")
public class TopSecretSplitController implements TopSecretSplitResource {

    private final TopSecretService topSecretService;

    @PostMapping
    @Override
    public ResponseEntity<TopSecretResponse> postTopSecretSplit(
            @NotNull @Valid @RequestBody TopSecretSplitRequest request) {
        return topSecretService.postTopSecretSplit(request)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new GenericNotFountException(NOT_ENOUGH_INFORMATION));
    }

    @GetMapping("{satellite_name}")
    @Override
    public ResponseEntity<TopSecretSplitResponse> getTopSecretSplit(
            @NotBlank @PathVariable("satellite_name") String satelliteName) {
        return topSecretService.getTopSecretSplit(satelliteName)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new GenericNotFountException(SATELLITE_NOT_FOUND));
    }
}
