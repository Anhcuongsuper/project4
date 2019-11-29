package com.project_sem4.fe.controller;

import com.project_sem4.fe.entity.Category;
import com.project_sem4.fe.entity.Story;
import com.project_sem4.fe.service.CategoryService;
import com.project_sem4.fe.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    StoryService storyService;

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String storeCategory(@Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/form";
        }
        categoryService.register(category);
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String showListCategory(Model model) {
        model.addAttribute("listCategory", categoryService.getAllCategory());
        return "category/list";

    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getDetail(@PathVariable long id, Model model) {
        Category category = categoryService.getDetail(id);
        Set<Story> storySet = category.getStories();
        model.addAttribute("category", category);
        model.addAttribute("storySet", storySet);
        model.addAttribute("detailCategory", categoryService.getDetail(id));


        Category category1 = categoryService.getDetail(2);
        Category category2 = categoryService.getDetail(3);
        Category category3 = categoryService.getDetail(4);
        Set<Story> storySet1 = category1.getStories();
        Set<Story> storySet2 = category2.getStories();
        Set<Story> storySet3 = category3.getStories();


        model.addAttribute("story", storySet1);
        model.addAttribute("storyHistory", storySet2);
        model.addAttribute("categoryNgontinh", storySet3);

        return "category/detail";

    }

    @RequestMapping(method = RequestMethod.GET, value = "story_new/list")
    public String getListNew(Model model,
                             @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                             @RequestParam(name = ",limit", required = false, defaultValue = "12") int limit
    ) {
        Page<Story> storyPage = storyService.getListCreatedAt(page, limit);
        model.addAttribute("listStoryNew", storyPage.getContent());
        model.addAttribute("totalPage", storyPage.getTotalPages());
        model.addAttribute("totalElement", storyPage.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        return "story_hot/list";
    }

    // get list story by category
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/list_story")
    public String showStoryByCategory(@PathVariable("id") long category_id, Model model,
                                      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(name = ",limit", required = false, defaultValue = "12") int limit
    ) {
        // lay ra cai category do
        Category category = categoryService.getDetail(category_id);
        Set<Story> storySet = category.getStories();
        model.addAttribute("category", category);
        model.addAttribute("storySet", storySet);
        return "category/list_story";
    }
}
