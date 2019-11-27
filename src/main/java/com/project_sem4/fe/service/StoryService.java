package com.project_sem4.fe.service;

import com.project_sem4.fe.entity.Story;
import com.project_sem4.fe.pagination.PageModel;
import com.project_sem4.fe.reponsitory.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryService {
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    PageModel pageModel;

    // list

        public  List<Story>getAll(){
        return storyRepository.findAll();
        }
    public List<Story> getByStoryHotView(int view) {

        return storyRepository.getStoryByView(view);
    }

    // page
    public Page<Story> getPage(int page, int limit) {

        return storyRepository.findAll(PageRequest.of(page - 1, limit));
    }

    // detail
    public Story getDetail(long id) {

        return storyRepository.findById(id).orElse(null);
    }

    public Story getDetailHot(long id) {

        return storyRepository.findById(id).orElse(null);
    }

    // create or update

    public Story create(Story story) {
        story.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        story.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        story.setDeletedAt(0);
        story.setStatus(1);
        return storyRepository.save(story);
    }

    public Story update(long id, Story updateStory) {

        Optional<Story> optionalStory = storyRepository.findById(id);
        if (optionalStory.isPresent()) {
            Story existStory = optionalStory.get();
            existStory.setTitle(updateStory.getTitle());
            existStory.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            return storyRepository.save(updateStory);
        }
        return null;
    }

    // delete
    public boolean Delete(long id) {
        Story existStory = storyRepository.findById(id).orElse(null);
        if (existStory == null) {
            return false;
        }
        storyRepository.delete(existStory);
        return true;
    }

//    public Page<Story> search (String title) {
//        pageModel.setSIZE(12);
//        pageModel.initPageAndSize();
//        return storyRepository.findByTitleContaining(title, PageRequest.of(pageModel.getPAGE(), pageModel.getSIZE()));
//    }

    public Page<Story> getListCreatedAt(int page, int limit) {

        return storyRepository.findAllByOrderByCreatedAtDesc(  PageRequest.of(page - 1, limit));

    }

    public Page<Story> getListCreatedAtIndex(int page, int limit) {

        return storyRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page - 1, limit));

    }
    public Page<Story> getAllStory(int page, int limit) {

        return storyRepository.findAll(PageRequest.of(page -1, limit));
    }
    public Page<Story> getListRate(int page, int limit){
        return storyRepository.findAllByOrderByRateDescActor(PageRequest.of(page -1, limit));
    }

    public Story findById(Long storyId) {
        Optional<Story> storyOptional = storyRepository.findById(storyId);
        if (!storyOptional.isPresent()) {
            throw new RuntimeException("Story Not Found!");
        }
        return storyOptional.get();
    }


}
