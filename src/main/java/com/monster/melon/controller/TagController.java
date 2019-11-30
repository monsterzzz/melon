package com.monster.melon.controller;

import com.monster.melon.pojo.Event;
import com.monster.melon.pojo.Tag;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.EventService;
import com.monster.melon.service.TagService;
import com.monster.melon.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author monster
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    private final EventService eventService;


    public TagController(TagService tagService, EventService eventService) {
        this.tagService = tagService;
        this.eventService = eventService;
    }

    @GetMapping("")
    public Response getAllTag(){
        Response response = new Response();
        response.setData(tagService.getAllTag());
        return response;
    }

    @PostMapping("")
    public Response addTag(){
        Tag tag = new Tag();
        tag.setDescription("vn");
        tagService.insertTag(tag);
        Response response = new Response();
        response.setMsg("ddd");
        return response;
    }

    @GetMapping("/{tagId}")
    public void getTagEventsIndex(@PathVariable("tagId") Integer tagId, HttpServletResponse response){
        try {
            response.sendRedirect("/tag/" + tagId + "/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{tagId}/{page}")
    public Response getTagEvents(@PathVariable("tagId") Integer tagId,@PathVariable("page") Integer page){
        Response response = new Response();
        MailUtil.sendTxtMail("springboot test mail","hello world","373016896@qq.com");
        List<Event> events = eventService.getEventsByTag(tagId,page);
        if(events.size() != 0){
            response.setCode(90000);
            response.setMsg("success");
            response.setData(events);
            return response;
        }
        response.setCode(90001);
        response.setMsg("no more message");
        return response;
    }
}
