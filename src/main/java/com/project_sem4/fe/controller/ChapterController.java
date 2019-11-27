package com.project_sem4.fe.controller;

import com.project_sem4.fe.entity.Chapter;
import com.project_sem4.fe.service.ChapterService;
import com.project_sem4.fe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @Autowired
    StoryService storyService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createChapter(Model model) {
        model.addAttribute("createChapter", new Chapter());
        model.addAttribute("listStory", storyService.getAll());
        return "chapter/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String storeChapter(@Valid Chapter chapter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "chapter/form";
        }
        chapterService.register(chapter);
        return "chapter/success";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String showListChapter(Model model) {
        model.addAttribute("listChapter", chapterService.getAllChapter());
        return "chapter/list";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getChapterDetail(@PathVariable long id, Model model) {
        model.addAttribute("detailChapter", chapterService.getDetail(id));
        model.addAttribute("storyService", storyService.getDetail(id));
        return "chapter/detail";
    }
}
