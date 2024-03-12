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

/**
 * class for setting up bot0 messages.
 * has no post or delete operations
 */
@RestController
@RequestMapping("/hello-bot")
public class Bot0messageController {
    private final Bot0messageService bot0MessageService;

    public Bot0messageController(Bot0messageService bot0MessageService) {
        this.bot0MessageService = bot0MessageService;
    }

    /**
     * get all bot0 messages from DataBase
     * Use method of service {@link Bot0messageService#getAll()}
     *
     * @return Collection<Bot0message>
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
    public Collection<Bot0message> getMessages() {
        return bot0MessageService.getAll();
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
