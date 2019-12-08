package com.monster.melon.controller;


import com.monster.melon.pojo.News;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.EventService;
import com.monster.melon.service.NewsService;
import com.monster.melon.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/news")
public class NewsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NewsService newsService;
    private final EventService eventService;

    @Resource
    private StringRedisTemplate redisTemplate;

    public NewsController(NewsService newsService, EventService eventService) {
        this.newsService = newsService;
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}")
    public void index(HttpServletResponse response,@PathVariable(value = "eventId") Integer eventId) throws IOException {
        response.sendRedirect("/news/" + eventId.toString() + "/1");
    }

    @GetMapping("/{eventId}/{page}")
    public Response getOnePage(@PathVariable(value = "eventId") Integer eventId,@PathVariable(value = "page") Integer page){

        Response response = new Response();
        response.setCode(80000);
        response.setMsg("success");
        response.setData(newsService.getOnePageNews(eventId,page));
        return response;
    }

    @PostMapping("/{eventId}")
    public Response insertNews(News news, HttpServletRequest request,@PathVariable(value = "eventId") Integer eventId){
        User user = UserUtil.getCurrentUserByToken(request);
        news.setUserId(user.getId());
        news.setEventId(eventId);

        newsService.insertNews(news);
        Response response = new Response();
        response.setMsg("success");
        response.setCode(80001);
        return response;
    }


}
