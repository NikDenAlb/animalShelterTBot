package selfConstructed.animalShelterTBot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selfConstructed.animalShelterTBot.model.Bot0message;
import selfConstructed.animalShelterTBot.service.Bot0messageService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/hello-bot")
public class Bot0messageController {
    private final Bot0messageService bot0MessageService;

    public Bot0messageController(Bot0messageService bot0MessageService) {
        this.bot0MessageService = bot0MessageService;
    }

    @Operation(summary = "Cписок сообщений начального бота",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "список сообщений. Меняйте текст сообщения через редактор по его ключу",
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

    @Operation(summary = "Ведите новый текст сообщения в формате Ключ-Значение",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Отредактированное сообщение",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Bot0message.class)
                                    )
                            }
                    )}
    )
    @PutMapping
    public ResponseEntity<Bot0message> editMessage(@RequestBody Bot0message bot0message) {
        Optional<Bot0message> editBot0message = bot0MessageService.editMessage(bot0message);
        return editBot0message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
