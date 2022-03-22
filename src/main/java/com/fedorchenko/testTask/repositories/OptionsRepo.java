package com.fedorchenko.testTask.repositories;

import com.fedorchenko.testTask.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface OptionsRepo extends JpaRepository<Option, Long> {
}
