package com.ml.challenge.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageServiceTest {

    private final MessageService service = new MessageService();

    @Test
    void getMessage_WhenListContentAllElementsEmpty() {

        List<String> list1 = Arrays.asList("", "", "", "", "");
        List<String> list2 = Arrays.asList("", "", "", "", "");
        List<String> list3 = Arrays.asList("", "", "", "", "");

        String response = service.getMessage(Arrays.asList(list1, list2, list3));

        assertEquals("", response);

    }

    @Test
    void getMessage_WhenListHaveSameSize() {

        List<String> list1 = Arrays.asList("este", "", "", "mensaje", "");
        List<String> list2 = Arrays.asList("", "es", "", "", "secreto");
        List<String> list3 = Arrays.asList("este", "", "un", "", "");

        String response = service.getMessage(Arrays.asList(list1, list2, list3));

        assertEquals("este es un mensaje secreto", response);
    }

    @Test
    void getMessage_WhenListHaveDistinctSize() {

        List<String> list1 = Arrays.asList("", "este", "es", "un", "mensaje");
        List<String> list2 = Arrays.asList("este", "", "un", "mensaje");
        List<String> list3 = Arrays.asList("", "", "es", "", "mensaje");

        String response = service.getMessage(Arrays.asList(list1, list2, list3));

        assertEquals("este es un mensaje", response);
    }

    @Test
    void getMessage_WhenListlessThree() {

        List<String> list1 = Arrays.asList("", "", "", "", "");
        List<String> list2 = Arrays.asList("", "", "", "", "");

        String response = service.getMessage(Arrays.asList(list1, list2));

        assertEquals("", response);

    }

    @Test
    void getMessage_WhenAnyListIsEmpty() {

        List<String> list1 = Arrays.asList("", "", "", "", "");
        List<String> list2 = Arrays.asList("", "", "", "", "");

        String response = service.getMessage(Arrays.asList(list1, list2, Collections.emptyList()));

        assertEquals("", response);

    }

    @Test
    void getMessage_WhenListIsEmpty() {

        String response = service.getMessage(Collections.emptyList());

        assertEquals("", response);

    }

    @Test
    void getMessage_WhenAnyListIsEmptyAndLessThree() {

        List<String> list1 = Arrays.asList("", "", "", "", "");

        String response = service.getMessage(Arrays.asList(list1, Collections.emptyList()));

        assertEquals("", response);

    }


    @Test
    void getMessage_WhenEdgeCase() {

        List<String> list1 = Arrays.asList("", "este", "es", "un", "mensaje", "mensaje");
        List<String> list2 = Arrays.asList("este", "   ", "", "un", "mensaje", ".");
        List<String> list3 = Arrays.asList("", "", "es", "", "mensaje", "mensaje");

        String response = service.getMessage(Arrays.asList(list1, list2, list3));

        assertEquals("este es un mensaje .", response);
    }
}