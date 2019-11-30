package com.monster.melon.service;

import com.monster.melon.mapper.TagMapper;
import com.monster.melon.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author monster
 */

@Service
public class TagService {

    private final TagMapper tagMapper;

    public TagService(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    public void insertTag(Tag tag) {
        tagMapper.insertTag(tag);
    }


    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }
}
