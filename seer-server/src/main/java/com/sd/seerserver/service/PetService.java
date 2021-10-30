package com.sd.seerserver.service;

import com.sd.seerserver.entity.Pet;
import com.sd.seerserver.entity.PetForm;

import java.util.List;

public interface PetService {
    Pet infoById(int id);

    int randomId();

    List<Pet> baseInfos(Integer[] ids);

    List<Pet> search(PetForm petForm);
}
