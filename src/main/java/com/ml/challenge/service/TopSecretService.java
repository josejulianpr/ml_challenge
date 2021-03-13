package com.ml.challenge.service;

import com.ml.challenge.dto.*;
import com.ml.challenge.enums.Ship;
import com.ml.challenge.model.TopSecret;
import com.ml.challenge.repository.TopSecretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ml.challenge.enums.Ship.*;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class TopSecretService {

    private final TopSecretRepository topSecretRepository;
    private final LocationService locationService;
    private final MessageService messageService;

    @Transactional
    public Optional<TopSecretResponse> topSecret(TopSecretRequest request) {
        try {
            List<TopSecret> topSecrets = this.toMap(request.getSatellites());

            if (isNotEmpty(topSecrets)) {
                topSecretRepository.saveAll(topSecrets);
            }

            return this.decryptMessageAndPosition();
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<TopSecretResponse> postTopSecretSplit(TopSecretSplitRequest request) {
        try {
            TopSecret topSecret = this.toMap(request.getSatellites());

            if (isNotBlank(topSecret.getName())) {
                topSecretRepository.save(topSecret);
            }

            return this.decryptMessageAndPosition();
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public Optional<TopSecretSplitResponse> getTopSecretSplit(String satelliteName) {

        Optional<TopSecret> topSecretOptional = topSecretRepository.findByName(satelliteName.toUpperCase().trim());

        if (topSecretOptional.isPresent()) {
            TopSecret topSecret = topSecretOptional.get();

            return Optional.of(TopSecretSplitResponse.builder()
                    .distance(topSecret.getDistance())
                    .message(Arrays.asList(topSecret.getMessage()))
                    .build());
        }

        return Optional.empty();
    }

    private Optional<TopSecretResponse> decryptMessageAndPosition() {
        List<TopSecret> topSecrets = topSecretRepository.findAll();

        List<List<String>> messages = topSecrets.stream()
                .map(x -> Arrays.asList(x.getMessage()))
                .collect(Collectors.toList());

        String message = messageService.getMessage(messages);

        Map<String, Double> collect = topSecrets.stream()
                .collect(Collectors.toMap(TopSecret::getName, TopSecret::getDistance));

        if (collect.size() == 3) {
            List<Double> location = locationService
                    .getLocation(Arrays.asList(collect.get(KENOBI.name()), collect.get(SKYWALKER.name()), collect.get(SATO.name())));

            if (isNotBlank(message) && isNotEmpty(location)) {
                return Optional.of(TopSecretResponse.builder()
                        .message(message)
                        .position(Position.builder()
                                .x(location.get(0))
                                .y(location.get(1))
                                .build())
                        .build());
            }
        }
        return Optional.empty();
    }

    private List<TopSecret> toMap(List<TopSecretDto> request) {
        return request.stream()
                .map(this::toMap)
                .filter(x -> isNotBlank(x.getName()))
                .collect(Collectors.toList());

    }

    private TopSecret toMap(TopSecretDto topSecretDto) {
        return TopSecret.builder()
                .distance(topSecretDto.getDistance())
                .message(toArray(topSecretDto.getMessage()))
                .name(Ship.isShip(topSecretDto.getName()))
                .build();
    }

    private String[] toArray(List<String> message) {
        return isNotEmpty(message) ? message.toArray(new String[message.size()]) : new String[0];
    }

}
