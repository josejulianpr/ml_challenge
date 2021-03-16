package com.ml.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.challenge.configuration.ObjectMapperConfiguration;
import com.ml.challenge.dto.TopSecretRequest;
import com.ml.challenge.dto.TopSecretResponse;
import com.ml.challenge.model.TopSecret;
import com.ml.challenge.repository.TopSecretRepository;
import com.ml.challenge.service.LocationService;
import com.ml.challenge.service.MessageService;
import com.ml.challenge.service.TopSecretService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.ml.challenge.TestDataCommons.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TopSecretController.class, TopSecretService.class, ObjectMapperConfiguration.class})
class TopSecretControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TopSecretRepository topSecretRepository;

    @MockBean
    private LocationService locationService;

    @MockBean
    private MessageService messageService;

    @Autowired
    private TopSecretController topSecretController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(topSecretController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void topSecret_WhenDataOk_ThenReturnOk() throws Exception {

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

        MvcResult result = mockMvc.perform(
                post("/topsecret")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();

        TopSecretResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), TopSecretResponse.class);

        assertEquals(expected, response);

        verify(topSecretRepository).saveAll(topSecrets);
        verify(topSecretRepository).findAll();
        verify(messageService).getMessage(buildMessage());
        verify(locationService).getLocation(distances);
    }

}