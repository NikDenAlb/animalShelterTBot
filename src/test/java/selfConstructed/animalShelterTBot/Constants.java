package selfConstructed.animalShelterTBot;

import selfConstructed.animalShelterTBot.model.Shelter;

import java.util.ArrayList;
import java.util.List;

import static selfConstructed.animalShelterTBot.model.TypePet.Cat;
import static selfConstructed.animalShelterTBot.model.TypePet.Dog;

public class Constants {

    public static final Shelter SHELTER_1 = new Shelter(1L, "ShelterName1", Cat, "ShelterAddress1", "ShelterTime1", "ShelterInfo1");
    public static final Shelter SHELTER_2 = new Shelter(2L, "ShelterName2", Dog, "ShelterAddress2", "ShelterTime2", "ShelterInfo2");


    public static final List<Shelter> SHELTER_LIST = new ArrayList<>(List.of(SHELTER_1, SHELTER_2));



}