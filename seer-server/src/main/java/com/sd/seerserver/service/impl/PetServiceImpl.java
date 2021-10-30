package com.sd.seerserver.service.impl;

import com.sd.seerserver.entity.Move;
import com.sd.seerserver.entity.Pet;
import com.sd.seerserver.entity.PetForm;
import com.sd.seerserver.mapper.MoveMapper;
import com.sd.seerserver.mapper.PetMapper;
import com.sd.seerserver.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetMapper petMapper;

    @Autowired
    private MoveMapper moveMapper;

    @Override
    public Pet infoById(int id) {
        Pet pet = petMapper.findById(id);
        if (pet == null)
            return null;
        List<Move> moves = moveMapper.findByPetId(id);
        pet.setMoves(moves);
        return pet;
    }

    @Override
    public int randomId() {
        return petMapper.randomId();
    }

    @Override
    public List<Pet> baseInfos(Integer[] ids) {
        return petMapper.findByIds(ids);
    }

    @Override
    public List<Pet> search(PetForm petForm) {
        return petMapper.search(petForm);
    }
}
