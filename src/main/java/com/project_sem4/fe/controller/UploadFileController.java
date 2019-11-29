package com.project_sem4.fe.controller;

import com.project_sem4.fe.entity.Chapter;
import com.project_sem4.fe.entity.Story;
import com.project_sem4.fe.entity.UploadFile;
import com.project_sem4.fe.service.ChapterService;
import com.project_sem4.fe.service.StoryService;
import com.project_sem4.fe.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/upload_file")
public class UploadFileController {

    private static Logger LOGGER = Logger.getLogger(UploadFileController.class.getSimpleName());
    @Autowired
    StoryService storyService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    UploadFileService uploadFileService;


    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createUploadFile(Model model) {
        model.addAttribute("uploadFile", new UploadFile());
        return "uploadFile/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createUploadFile(@Valid UploadFile uploadFile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "uploadFile/form";
        }
        uploadFileService.Create(uploadFile);
        return "Index";
    }

    // multi
    @RequestMapping(method = RequestMethod.GET, value = "/create-chapter")
    public String index() {
        return "uploadform";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/store-chapter")
    public String uploadMultipartFile(
            @RequestParam(name = "title", required = true) String title,
            @RequestParam(name = "content", required = true) String content,
            @RequestParam(name = "images", required = true) String[] images,
            Model model) {
        Story story = storyService.getDetail(3);
        if (story == null) {
            return "exception/error404";
        }
        Chapter chapter = new Chapter();
        chapter.setStory(story);
        chapter.setTitle(title);
        chapter.setContent(content);
        chapter.setCode(1);
        chapter.setEpisode(1);
        if (images != null && images.length > 0) {
            for (int i = 0; i < images.length; i++) {
                String link = images[i];
                chapter.addUploadFile(new UploadFile(link));
            }
        }
        chapterService.register(chapter);
        return "uploadform";
    }
}
