package com.project_sem4.fe.controller;

import com.project_sem4.fe.entity.Category;
import com.project_sem4.fe.entity.Story;
import com.project_sem4.fe.service.CategoryService;
import com.project_sem4.fe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    StoryService storyService;
    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "/")
    public String HomePage() {
        return "Index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        model.addAttribute("pageStory", storyService.getPage(1, 10));
        model.addAttribute("listStoryNew", storyService.getListCreatedAtIndex(1, 10));
        model.addAttribute("listStory", storyService.getAllStory(1, 8));
        model.addAttribute("listCategory", categoryService.getAllCategory());
        model.addAttribute("listStoryHot", storyService.getListRate(1, 10));
        Category category1 = categoryService.getDetail(1);
        Category category2 = categoryService.getDetail(2);
        Category category3 = categoryService.getDetail(7);
        Category category4 = categoryService.getDetail(10);

        Set<Story> storySet1 = category1.getStories();
        Set<Story> storySet2 = category2.getStories();
        Set<Story> storySet3 = category3.getStories();
        Set<Story> storySet4 = category4.getStories();

        model.addAttribute("category1", category1);
        model.addAttribute("category2", category2);
        model.addAttribute("category3", category3);
        model.addAttribute("category4", category4);

        model.addAttribute("storySet1", storySet1);
        model.addAttribute("storySet2", storySet2);
        model.addAttribute("storySet3", storySet3);
        model.addAttribute("storySet4", storySet4);

        return "Index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rules")
    public String rules() {

        return "rules";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contact")
    public String contact() {

        return "contact";
    }


}
