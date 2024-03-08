package selfConstructed.animalShelterTBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.model.Bot0message;

import selfConstructed.animalShelterTBot.repository.Bot0Repository;

import java.util.Collection;


@Service
public class Bot0messageServiceImpl implements Bot0messageService {
    private final Bot0Repository bot0Repository;
    private final Logger logger = LoggerFactory.getLogger(Bot0messageService.class);

    @Autowired
    public Bot0messageServiceImpl(Bot0Repository bot0Repository) {
        this.bot0Repository = bot0Repository;
    }

    public Collection<Bot0message> getAll() {
        logger.debug("Bot0message findAll");
        return bot0Repository.findAll();
    }

//    public VolunteerInfo addVolunteer(VolunteerInfo volunteer) {
//        logger.debug("Adding volunteer");
//        return volunteerRepository.save(volunteer);
//    }

//    public Optional<VolunteerInfo> findVolunteer(long id) {
//        logger.debug("findVolunteer id={}",id);
//        return volunteerRepository.findById(id);
//    }
//
//    public VolunteerInfo editVolunteer(VolunteerInfo volunteer) {
//        logger.debug("Editing volunteer");
//        return volunteerRepository.save(volunteer);
//    }
//
//    public void deleteVolunteer(Long id) {
//        logger.debug("deleteVolunteer id={}",id);
//        volunteerRepository.deleteById(id);
//    }
}
