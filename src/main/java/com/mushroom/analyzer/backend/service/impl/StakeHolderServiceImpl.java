package com.mushroom.analyzer.backend.service.impl;

import com.mushroom.analyzer.backend.exception.SWException;
import com.mushroom.analyzer.backend.exception.pojo.SWExceptionCode;
import com.mushroom.analyzer.backend.model.entity.StakeHolder;
import com.mushroom.analyzer.backend.model.repository.StakeHolderRepository;
import com.mushroom.analyzer.backend.service.StakeHolderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class StakeHolderServiceImpl implements StakeHolderService {

    private final StakeHolderRepository stakeHolderRepository;
    private final ModelMapper modelMapper;

    public StakeHolderServiceImpl(StakeHolderRepository stakeHolderRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.stakeHolderRepository = stakeHolderRepository;
    }


    @Override
    public StakeHolder getStakeHolder(long id) throws SWException {
        return getStakeHolderById(id);
    }

    public StakeHolder getStakeHolderById(long id) throws SWException {
        Optional<StakeHolder> stakeHolder = stakeHolderRepository.findById(id);
        if(stakeHolder.isEmpty()){
            throw new SWException(
                    HttpStatus.BAD_REQUEST,
                    SWExceptionCode.MASH001,
                    "Stakeholder not found.",
                    "Stakeholder not found with id: " + id
            );
        }
        return stakeHolder.get();
    }
}
