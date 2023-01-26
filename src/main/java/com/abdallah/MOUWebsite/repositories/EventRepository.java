package com.abdallah.MOUWebsite.repositories;

import org.springframework.data.repository.CrudRepository;
import com.abdallah.MOUWebsite.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
