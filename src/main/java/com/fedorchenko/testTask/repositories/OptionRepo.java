package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepo extends JpaRepository<Option, Long> {
}
