package com.monster.melon.service;


import com.monster.melon.mapper.EventMapper;
import com.monster.melon.pojo.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService{

    private final EventMapper eventMapper;

    @Value("${event_num}")
    private Integer eventNum;

    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public List<Event> getAllEvents() {
        return eventMapper.getAllEvents();
    }

    public List<Event> getOnePageEvent(Integer page) {
        page = Math.max(page-1,0);
        return eventMapper.getOnePageEvent(page * eventNum,eventNum);
    }

    public void insertEvent(Event event,Integer userId) {
        eventMapper.insertEvent(event,userId);
    }

    public void deleteEvent(Integer id) {
        eventMapper.deleteEvent(id);
    }

    public void updateViewNum(Integer viewNum,Integer eventId){
        eventMapper.updateViewNum(viewNum,eventId);
    }

    public List<Event> getEventsByTag(Integer tagId,Integer page){
        page = Math.max(page-1,0);
        return eventMapper.getEventsByTag(tagId,page*eventNum,eventNum);
    }
}
