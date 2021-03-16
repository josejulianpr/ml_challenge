package com.ml.challenge.service;

import com.ml.challenge.dto.*;
import com.ml.challenge.enums.Ship;
import com.ml.challenge.exception.GenericNotFountException;
import com.ml.challenge.model.TopSecret;
import com.ml.challenge.repository.TopSecretRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ml.challenge.TestDataCommons.*;
import static com.ml.challenge.enums.Ship.KENOBI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopSecretServiceTest {

    @Mock
    private TopSecretRepository topSecretRepository;
    @Mock
    private LocationService locationService;
    @Mock
    private MessageService messageService;

    @InjectMocks
    private TopSecretService service;


    @Test
    void topSecret_WhenRequestIsEmpty_ThenReturnEmpty() {

        TopSecretRequest request = TopSecretRequest.builder()
                .satellites(Collections.emptyList())
                .build();

        when(topSecretRepository.findAll())
                .thenReturn(Collections.emptyList());

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository, never()).saveAll(anyCollection());
        verify(topSecretRepository).findAll();
    }

    @Test
    void topSecret_WhenRequestSatellitesMessageIsEmpty_ThenReturnEmpty() {

        TopSecretRequest request = TopSecretRequest.builder()
                .satellites(Collections.singletonList(TopSecretDto.builder()
                        .distance(666D)
                        .message(Collections.emptyList())
                        .name(Ship.class.getName())
                        .build()))
                .build();

        when(topSecretRepository.findAll())
                .thenReturn(Collections.emptyList());

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository, never()).saveAll(anyCollection());
        verify(topSecretRepository).findAll();
    }


    @Test
    void topSecret_WhenInputHaveOnlySatellites_ThenReturnEmpty() {

        TopSecretDto topSecretDto = buildTopSecretDto();

        TopSecret topSecret = buildTopSecret();

        TopSecretRequest request = TopSecretRequest.builder()
                .satellites(Collections.singletonList(topSecretDto))
                .build();

        List<TopSecret> topSecrets = Collections.singletonList(topSecret);

        when(topSecretRepository.saveAll(topSecrets))
                .thenReturn(topSecrets);

        when(topSecretRepository.findAll())
                .thenReturn(topSecrets);

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
    }

    @Test
    void topSecret_WhenGenerateException_ThenReturnEmpty() {

        GenericNotFountException exception = mock(GenericNotFountException.class);

        TopSecretDto topSecretDto = buildTopSecretDto();

        TopSecret topSecret = buildTopSecret();

        TopSecretRequest request = TopSecretRequest.builder()
                .satellites(Collections.singletonList(topSecretDto))
                .build();

        List<TopSecret> topSecrets = Collections.singletonList(topSecret);

        when(topSecretRepository.saveAll(topSecrets))
                .thenReturn(topSecrets);

        when(topSecretRepository.findAll())
                .thenThrow(exception);

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
    }

    @Test
    void topSecret_WhenDataOk_ThenReturnOk() {

        String message = "Este es un mensaje secreto";

        List<Double> distances = Arrays.asList(100.0D, 115.5D, 142.7D);

        double positionX = -487.2859125D;
        double positionY = 1557.014225D;
        List<Double> positions = Arrays.asList(positionX, positionY);

        TopSecretRequest request = buildTopSecretRequest();

        List<TopSecret> topSecrets = buildTopSecrets();

        TopSecretResponse expected = buildTopSecretResponse(message, positionX, positionY);

        when(topSecretRepository.saveAll(topSecrets))
                .thenReturn(topSecrets);

        when(topSecretRepository.findAll())
                .thenReturn(topSecrets);

        when(messageService.getMessage(buildMessage()))
                .thenReturn(message);

        when(locationService.getLocation(distances))
                .thenReturn(positions);

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.of(expected), response);
        assertEquals(expected, response.get());

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
        verify(messageService).getMessage(buildMessage());
        verify(locationService).getLocation(distances);
    }

    @Test
    void topSecret_WhenMessageIsEmpty_ThenReturnOk() {

        List<Double> distances = Arrays.asList(100.0D, 115.5D, 142.7D);

        double positionX = -487.2859125D;
        double positionY = 1557.014225D;
        List<Double> positions = Arrays.asList(positionX, positionY);

        TopSecretRequest request = buildTopSecretRequest();

        List<TopSecret> topSecrets = buildTopSecrets();

        when(topSecretRepository.saveAll(topSecrets))
                .thenReturn(topSecrets);

        when(topSecretRepository.findAll())
                .thenReturn(topSecrets);

        when(messageService.getMessage(buildMessage()))
                .thenReturn("");

        when(locationService.getLocation(distances))
                .thenReturn(positions);

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
        verify(messageService).getMessage(buildMessage());
        verify(locationService).getLocation(distances);
    }

    @Test
    void topSecret_WhenLocationsIsEmpty_ThenReturnOk() {

        String message = "Este es un mensaje secreto";

        List<Double> distances = Arrays.asList(100.0D, 115.5D, 142.7D);

        TopSecretRequest request = buildTopSecretRequest();

        List<TopSecret> topSecrets = buildTopSecrets();

        when(topSecretRepository.saveAll(topSecrets))
                .thenReturn(topSecrets);

        when(topSecretRepository.findAll())
                .thenReturn(topSecrets);

        when(messageService.getMessage(buildMessage()))
                .thenReturn(message);

        when(locationService.getLocation(distances))
                .thenReturn(Collections.emptyList());

        Optional<TopSecretResponse> response = service.topSecret(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
        verify(messageService).getMessage(buildMessage());
        verify(locationService).getLocation(distances);
    }

    @Test
    void postTopSecretSplit_WhenDataOk_ThenReturnOk() {

        String message = "Este es un mensaje secreto";

        List<Double> distances = Arrays.asList(100.0D, 115.5D, 142.7D);

        double positionX = -487.2859125D;
        double positionY = 1557.014225D;
        List<Double> positions = Arrays.asList(positionX, positionY);

        TopSecretSplitRequest request = TopSecretSplitRequest.builder()
                .satellites(TopSecretDto.builder()
                        .name(KENOBI.name())
                        .message(Arrays.asList("", "este", "es", "un", "mensaje"))
                        .distance(100.0D)
                        .build())
                .build();

        TopSecret topSecret = TopSecret.builder()
                .name(KENOBI.name())
                .message(new String[]{"", "este", "es", "un", "mensaje"})
                .distance(100.0D)
                .build();

        List<TopSecret> topSecrets = buildTopSecrets();

        TopSecretResponse expected = buildTopSecretResponse(message, positionX, positionY);

        when(topSecretRepository.save(topSecret))
                .thenReturn(topSecret);

        when(topSecretRepository.findAll())
                .thenReturn(topSecrets);

        when(messageService.getMessage(buildMessage()))
                .thenReturn(message);

        when(locationService.getLocation(distances))
                .thenReturn(positions);

        Optional<TopSecretResponse> response = service.postTopSecretSplit(request);

        assertEquals(Optional.of(expected), response);
        assertEquals(expected, response.get());

        verify(topSecretRepository).save(topSecret);
        verify(topSecretRepository).findAll();
        verify(messageService).getMessage(buildMessage());
        verify(locationService).getLocation(distances);
    }

    @Test
    void postTopSecretSplit_WhenGenerateException_ThenReturnEmpty() {

        GenericNotFountException exception = mock(GenericNotFountException.class);

        TopSecret topSecret = buildTopSecret();
        topSecret.setName("");

        TopSecretSplitRequest request = TopSecretSplitRequest.builder()
                .satellites(TopSecretDto.builder()
                        .name(Ship.class.getName())
                        .message(Arrays.asList("", "este", "es", "un", "mensaje"))
                        .distance(100.0D)
                        .build())
                .build();

        when(topSecretRepository.findAll())
                .thenThrow(exception);

        Optional<TopSecretResponse> response = service.postTopSecretSplit(request);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository, never()).save(any(TopSecret.class));
        verify(topSecretRepository).findAll();
    }

    @Test
    void getTopSecretSplit_WhenSatelliteNotFound_ThenReturnEmpty() {

        String satelliteName = KENOBI.name();

        when(topSecretRepository.findByName(satelliteName))
                .thenReturn(Optional.empty());

        Optional<TopSecretSplitResponse> response = service.getTopSecretSplit(satelliteName);

        assertEquals(Optional.empty(), response);

        verify(topSecretRepository).findByName(satelliteName);
    }

    @Test
    void getTopSecretSplit_Ok_ThenReturnEmpty() {

        String satelliteName = KENOBI.name();

        TopSecret topSecret = buildTopSecret();

        TopSecretSplitResponse expected = buildTopSecretSplitResponse();

        when(topSecretRepository.findByName(satelliteName))
                .thenReturn(Optional.of(topSecret));

        Optional<TopSecretSplitResponse> response = service.getTopSecretSplit(satelliteName);

        assertEquals(Optional.of(expected), response);
        assertEquals(expected, response.get());

        verify(topSecretRepository).findByName(satelliteName);
    }
}