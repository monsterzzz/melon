package com.monster.melon.controller;

import com.monster.melon.pojo.Event;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.EventService;
import com.monster.melon.util.Common;
import com.monster.melon.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/event/1");
    }

    @GetMapping("/{page}")
    public Response getOnePageEvents(@PathVariable(value = "page") Integer page){
        Response response = new Response();
        response.setCode(70000);
        response.setMsg("success");
        response.setData(eventService.getOnePageEvent(page));
        return response;
    }

//    @GetMapping("/{tag}")
//    public void get1(@PathVariable(value = "tag") String tag,HttpServletResponse response) throws IOException {
//        response.sendRedirect("/event/" + tag + "/1");
//    }

    @GetMapping("/{tag}/{page}")
    public Response get2(@PathVariable(value = "tag") String tag,@PathVariable(value = "page") Integer page){
        Response response = new Response();
        response.setCode(70000);
        response.setMsg("success");
        response.setData(eventService.getOnePageEvent(page));
        return response;
    }

    @PostMapping("")
    public Response insertEvent(Event event, HttpServletRequest request){
        Response response = new Response();
        if(Common.checkLength(event.getDescription(),2,32)){
            response.setCode(70002);
            response.setMsg("too short description");
            return response;
        }

        if(Common.checkLength(event.getName(),2,16)){
            response.setCode(70003);
            response.setMsg("too short name");
            return response;
        }

        User user = UserUtil.getCurrentUserBySession(request);
        eventService.insertEvent(event,user.getId());
        response.setCode(70001);
        response.setMsg("success");
        return response;
    }


}
