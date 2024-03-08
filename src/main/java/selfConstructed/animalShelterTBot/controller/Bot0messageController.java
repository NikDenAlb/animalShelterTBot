package selfConstructed.animalShelterTBot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selfConstructed.animalShelterTBot.model.Bot0message;
import selfConstructed.animalShelterTBot.service.Bot0messageService;

import java.util.Collection;

@RestController
@RequestMapping("/hello-bot")
public class Bot0messageController {
    private final Bot0messageService bot0MessageService;

    public Bot0messageController(Bot0messageService bot0MessageService) {
        this.bot0MessageService = bot0MessageService;
    }

    @Operation(summary = "список сообщений начального бота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список сообщений",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Bot0message.class)
                                    )
                            }
                    )}
    )
    @GetMapping
    public Collection<Bot0message> getMessages() {
        return bot0MessageService.getAll();
    }

//    @GetMapping("{id}")
//    public ResponseEntity<VolunteerInfo> getFacultyInfo(@PathVariable long id) {
//        Optional<VolunteerInfo> volunteer = volunteerService.findVolunteer(id);
//        return volunteer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public VolunteerInfo createFaculty(@RequestBody VolunteerInfo volunteer) {
//        return volunteerService.addVolunteer(volunteer);
//    }
//
//    @PutMapping
//    public ResponseEntity<VolunteerInfo> editFaculty(@RequestBody VolunteerInfo volunteer) {
//        VolunteerInfo foundFaculty = volunteerService.editVolunteer(volunteer);
//        if (foundFaculty == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(foundFaculty);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteVolunteer(@PathVariable long id) {
//        volunteerService.deleteVolunteer(id);
//        return ResponseEntity.ok().build();
//    }
}
