package com.fedorchenko.testTask.services;

import com.fedorchenko.testTask.entities.Option;
import com.fedorchenko.testTask.enums.EntityList;
import com.fedorchenko.testTask.exceptions.EntityNotFoundException;
import com.fedorchenko.testTask.repositories.OptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    OptionRepo optionRepo;

    public List<Option> getOptions() {
        return optionRepo.findAll();
    }

    public Option saveOption(Option newOption) {
        return optionRepo.save(newOption);
    }

    public Option findOptionById(Long id) {
        return optionRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityList.OPTION, id));
    }

    public void deleteOptionById(Long id) {
        optionRepo.deleteById(id);
    }
}
