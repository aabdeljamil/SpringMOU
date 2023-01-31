package com.abdallah.MOUWebsite.data;

import org.springframework.data.repository.CrudRepository;
import com.abdallah.MOUWebsite.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}