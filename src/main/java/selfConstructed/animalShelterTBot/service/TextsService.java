package selfConstructed.animalShelterTBot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TextsService {
    private Map<String, String> texts;

    public TextsService() throws IOException {
        texts = new ObjectMapper().readValue(new ClassPathResource("Texts.json").getFile(), HashMap.class);
    }

    public String getTextOrDefault(String key, String defaultValue) {
        return Optional.ofNullable(texts.get(key)).orElse(defaultValue);
    }
}
