package selfConstructed.animalShelterTBot;

import selfConstructed.animalShelterTBot.model.Shelter;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static final Shelter SHELTER_1 = new Shelter(1L, "ShelterName1", 1L, "ShelterAddress1", "ShelterTime1", "ShelterInfo1");
    public static final Shelter SHELTER_2 = new Shelter(2L, "ShelterName2", 2L, "ShelterAddress2", "ShelterTime2", "ShelterInfo2");


    public static final List<Shelter> SHELTER_LIST = new ArrayList<>(List.of(SHELTER_1, SHELTER_2));



}