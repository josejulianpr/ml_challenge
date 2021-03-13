package com.ml.challenge.controller.documentation;

import com.ml.challenge.dto.TopSecretResponse;
import com.ml.challenge.dto.TopSecretSplitRequest;
import com.ml.challenge.dto.TopSecretSplitResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

@Api(tags = "TopSecretSplit Controller")
public interface TopSecretSplitResource {

    @ApiOperation("Get the location of the ship and the message it emits.")
    ResponseEntity<TopSecretResponse> postTopSecretSplit(TopSecretSplitRequest request);

    @ApiOperation("Allows to obtain the information of a ship.")
    ResponseEntity<TopSecretSplitResponse> getTopSecretSplit(String satelliteName);
}
