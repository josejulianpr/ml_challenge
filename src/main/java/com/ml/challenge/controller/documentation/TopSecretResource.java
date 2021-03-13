package com.ml.challenge.controller.documentation;

import com.ml.challenge.dto.TopSecretRequest;
import com.ml.challenge.dto.TopSecretResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(tags = "TopSecret Controller")
public interface TopSecretResource {

    @ApiOperation("Get the location of the ship and the message it emits.")
    ResponseEntity<TopSecretResponse> topSecret(TopSecretRequest request);
}
