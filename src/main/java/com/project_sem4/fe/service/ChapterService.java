package com.project_sem4.fe.service;

import com.project_sem4.fe.entity.Chapter;
import com.project_sem4.fe.entity.Story;
import com.project_sem4.fe.pagination.PageModel;
import com.project_sem4.fe.reponsitory.ChapterRepository;
import com.project_sem4.fe.reponsitory.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    StoryService storyService;

    @Autowired
    PageModel pageModel;


    public Page<Chapter> getAllChapterByStory(long storyId, Pageable pageable) {
//        pageModel.setSIZE(8);
//        pageModel.initPageAndSize();
        return chapterRepository.findAllByStoryIdOrderByIdDesc(storyId, pageable);
    }

    public List<Chapter> getAllChapter() {
        return chapterRepository.findAll();
    }

    public Chapter getDetail(long id) {

        return chapterRepository.findById(id).orElse(null);
    }

    public Chapter register(Chapter chapter) {
        chapter.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        chapter.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        chapter.setDeletedAt(0);
        chapter.setStatus(1);
        return chapterRepository.save(chapter);
    }

    public Chapter update(long id, Chapter updateChapter) {

        Optional<Chapter> optionalChapter = chapterRepository.findById(id);
        if (optionalChapter.isPresent()) {
            Chapter existChapter = optionalChapter.get();
            existChapter.setTitle(updateChapter.getTitle());
            existChapter.setContent(updateChapter.getContent());
            existChapter.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            return chapterRepository.save(updateChapter);
        }
        return null;
    }


    public boolean deleted(long id) {
        Chapter existChapter = chapterRepository.findById(id).orElse(null);
        if (existChapter == null) {
            return false;
        }
        chapterRepository.delete(existChapter);
        return true;
    }

    public Long createChapterByStoryId(Long storyId, Chapter chapter) {
        Story story = storyService.findById(storyId);
        chapter.setStory(story);
        chapterRepository.save(chapter);
        return chapter.getId();
    }

    public Chapter findByIdAndStoryId(long chapterId, long storyId) {
        Story story = storyService.findById(storyId);
        Chapter chapter = getDetail(chapterId);
        chapter.setStory(story);
        return chapterRepository.findById(chapterId).orElse(null);
    }
}
