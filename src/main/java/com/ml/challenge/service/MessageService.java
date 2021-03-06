package com.ml.challenge.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class MessageService {

    /***
     *
     * @param messages the message as it is received on each satellite
     * @return the message as generated by the sender of the message
     */
    public String getMessage(List<List<String>> messages) {

        if (this.validateListInput(messages)) {

            Integer max = Collections.max(messages.stream().map(List::size).collect(Collectors.toList()));

            List<String> result = new ArrayList<>();

            initializeList(max, result);

            for (List<String> list : messages) {
                for (int i = 0; i < list.size(); ++i) {
                    if (isNotBlank(list.get(i)) && (isBlank(result.get(i)) || !result.contains(list.get(i)))) {
                        result.set(i, list.get(i));
                    }
                }
            }

            result = result.stream()
                    .distinct()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());

            return String.join(" ", result);
        }
        return "";
    }

    private void initializeList(Integer max, List<String> result) {
        for (int i = 0; i < max; i++) {
            result.add("");
        }
    }

    private boolean validateListInput(List<List<String>> messages) {
        return isNotEmpty(messages) && messages.size() == 3 && messages.stream().allMatch(CollectionUtils::isNotEmpty);
    }

}
