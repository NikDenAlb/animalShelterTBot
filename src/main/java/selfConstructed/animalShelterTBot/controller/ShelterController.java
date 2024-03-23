package selfConstructed.animalShelterTBot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selfConstructed.animalShelterTBot.model.Bot0message;
import selfConstructed.animalShelterTBot.model.Shelter;
import selfConstructed.animalShelterTBot.service.Bot0messageService;
import selfConstructed.animalShelterTBot.service.ShelterService;

import java.util.Collection;
import java.util.Optional;

/**
 * class for setting up shelter information.
 * has no post or delete operations
 */
@RestController
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    /**
     * get all shelter messages from DataBase
     * Use method of service {@link Bot0messageService#getAll()}
     *
     * @return Collection<Shelter>
     */
    @Operation(summary = "Cписок сообщений начального бота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список сообщений. Меняйте текст сообщения через put в /hello-bot по его ключу",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Bot0message.class)
                                    )
                            }
                    )}
    )
    @GetMapping
    public Collection<Shelter> getMessages() {
        return shelterService.getAll();
    }

    /**
     * edit specific message in database
     * Use method of service {@link Bot0messageService#editMessage(Bot0message bot0message)}
     *
     * @return Bot0message or notFound() if message_type is not in DataBase
     */
    @Operation(summary = "Введите новый текст сообщения нужному типу ответа",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение с новым текстом",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Shelter.class)
                                    )
                            }
                    )}
    )
    @PutMapping
    public ResponseEntity<Shelter> editMessage(@RequestBody Shelter shelter) {
        Optional<Shelter> editShelter = shelterService.editMessage(shelter);
        return editShelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}