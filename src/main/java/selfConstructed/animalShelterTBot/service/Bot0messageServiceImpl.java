package selfConstructed.animalShelterTBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.Bot0message;
import selfConstructed.animalShelterTBot.repository.Bot0Repository;

import java.util.Collection;
import java.util.Optional;

@Service
public class Bot0messageServiceImpl implements Bot0messageService {
    private final Bot0Repository bot0Repository;
    private final Logger logger = LoggerFactory.getLogger(Bot0messageService.class);

    public Bot0messageServiceImpl(Bot0Repository bot0Repository) {
        this.bot0Repository = bot0Repository;
    }

    @Override
    public Collection<Bot0message> getAll() {
        logger.debug("Bot0message findAll");
        return bot0Repository.findAll();
    }

    @Override
    public Optional<Bot0message> editMessage(Bot0message bot0message) {
        if (bot0Repository.findById(bot0message.getMessage_type()).isPresent()) {
            return Optional.of(bot0Repository.save(bot0message));
        }
        return Optional.empty();
    }
}