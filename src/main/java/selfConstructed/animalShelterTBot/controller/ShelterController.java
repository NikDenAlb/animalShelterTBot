package selfConstructed.animalShelterTBot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import selfConstructed.animalShelterTBot.model.Shelter;
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
     * get all shelters
     * Use method of service {@link ShelterService#getAll()}
     *
     * @return Collection<Shelter>
     */
    @Operation(summary = "Cписок приютов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Меняйте характеристику приюта черем соответствующий метод по id приюта",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Shelter.class)
                                    )
                            }
                    )}
    )
    @GetMapping
    public Collection<Shelter> getShelters() {
        return shelterService.getAll();
    }

    /**
     * edit name of the Shelter
     * Use method of service {@link ShelterService#editShelterName(String, String)}}
     *
     * @return shelter or notFound() if id is not in the DataBase
     */
    @Operation(summary = "Введите приюту с заданным id новое имя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новое имя",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Shelter.class)
                                    )
                            }
                    )}
    )
    @PutMapping
    public ResponseEntity<Shelter> editShelterName(@RequestParam("id") String id, @RequestParam("shelterName") String shelterName) {
        Optional<Shelter> editShelter = shelterService.editShelterName(id, shelterName);
        return editShelter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}