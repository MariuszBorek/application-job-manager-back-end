package com.manager.repository;

import com.manager.model.TaskArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskArchiveRepository extends JpaRepository<TaskArchive, Integer> {
}
