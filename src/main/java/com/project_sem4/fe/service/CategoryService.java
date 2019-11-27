package com.project_sem4.fe.service;

import com.project_sem4.fe.entity.Category;
import com.project_sem4.fe.reponsitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Page<Category> getAllPageCategory(int page, int limit) {
        return categoryRepository.findAll(PageRequest.of(page - 1, limit));
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    public Category getDetail(long id) {

        return categoryRepository.findById(id).orElse(null);
    }

    public Category register(Category category) {
        category.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        category.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        category.setStatus(1);
        return categoryRepository.save(category);
    }

    public Category update(long id, Category updateCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existCategory = optionalCategory.get();
            existCategory.setName(updateCategory.getName());
            existCategory.setDescription(updateCategory.getDescription());
            existCategory.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            return categoryRepository.save(updateCategory);

        }
        return null;
    }

    public boolean deleted(long id) {
        Category existCategory = categoryRepository.findById(id).orElse(null);
        if (existCategory == null) {
            return false;
        }
        categoryRepository.delete(existCategory);
        return true;
    }
    public List<Category> search(String name) {
        return categoryRepository.findByName(name);
    }
    public Page<Category> getAllByCode(int code, int page, int limit){
        return categoryRepository.findAllByCode(code,PageRequest.of(page - 1, limit));
    }
}
