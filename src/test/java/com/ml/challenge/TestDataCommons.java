package com.ml.challenge;

import com.ml.challenge.dto.*;
import com.ml.challenge.enums.Ship;
import com.ml.challenge.model.TopSecret;

import java.util.Arrays;
import java.util.List;

import static com.ml.challenge.enums.Ship.KENOBI;

public final class TestDataCommons {
    private TestDataCommons() {
    }

    public static TopSecretDto buildTopSecretDto() {
        return TopSecretDto.builder()
                .distance(1000D)
                .message(Arrays.asList("", "este", "es", "un", "mensaje", "mensaje"))
                .name(KENOBI.name())
                .build();
    }

    public static TopSecret buildTopSecret() {
        return TopSecret.builder()
                .distance(1000D)
                .message(new String[]{"", "este", "es", "un", "mensaje", "mensaje"})
                .name(KENOBI.name())
                .build();
    }

    public static TopSecretSplitResponse buildTopSecretSplitResponse() {
        return TopSecretSplitResponse.builder()
                .distance(1000D)
                .message(Arrays.asList("", "este", "es", "un", "mensaje", "mensaje"))
                .build();
    }

    public static TopSecretRequest buildTopSecretRequest() {
        TopSecretDto topSecretDto1 = TopSecretDto.builder()
                .name(Ship.KENOBI.name())
                .message(Arrays.asList("", "este", "es", "un", "mensaje"))
                .distance(100.0D)
                .build();

        TopSecretDto topSecretDto2 = TopSecretDto.builder()
                .name(Ship.SKYWALKER.name())
                .message(Arrays.asList("este", "", "un", "mensaje"))
                .distance(115.5D)
                .build();

        TopSecretDto topSecretDto3 = TopSecretDto.builder()
                .name(Ship.SATO.name())
                .message(Arrays.asList("", "", "es", "", "mensaje"))
                .distance(142.7D)
                .build();

        return TopSecretRequest.builder()
                .satellites(Arrays.asList(topSecretDto1, topSecretDto2, topSecretDto3))
                .build();
    }

    public static List<TopSecret> buildTopSecrets() {
        TopSecret topSecret1 = TopSecret.builder()
                .name(Ship.KENOBI.name())
                .message(new String[]{"", "este", "es", "un", "mensaje"})
                .distance(100.0D)
                .build();

        TopSecret topSecret2 = TopSecret.builder()
                .name(Ship.SKYWALKER.name())
                .message(new String[]{"este", "", "un", "mensaje"})
                .distance(115.5D)
                .build();

        TopSecret topSecret3 = TopSecret.builder()
                .name(Ship.SATO.name())
                .message(new String[]{"", "", "es", "", "mensaje"})
                .distance(142.7D)
                .build();

        return Arrays.asList(topSecret1, topSecret2, topSecret3);
    }

    public static List<List<String>> buildMessage() {
        return Arrays.asList(Arrays.asList("", "este", "es", "un", "mensaje"),
                Arrays.asList("este", "", "un", "mensaje"),
                Arrays.asList("", "", "es", "", "mensaje"));
    }

    public static TopSecretResponse buildTopSecretResponse(String message, double positionX, double positionY) {
        return TopSecretResponse.builder()
                .position(Position.builder()
                        .y(positionY)
                        .x(positionX)
                        .build())
                .message(message)
                .build();
    }
}
