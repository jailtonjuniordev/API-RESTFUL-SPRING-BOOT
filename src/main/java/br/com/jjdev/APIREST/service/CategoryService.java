package br.com.jjdev.APIREST.service;

import br.com.jjdev.APIREST.domain.category.Category;
import br.com.jjdev.APIREST.repository.CategoryRepository;
import br.com.jjdev.APIREST.rest.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public String createCategory(CategoryDTO category) {
        Category categoryQueried = this.categoryRepository.findAllByName(category.name());

        if (categoryQueried == null) {
            Category createdCategory = new Category();
            BeanUtils.copyProperties(category, createdCategory);
            categoryRepository.save(createdCategory);
            return "Category created successfully";
        } else return "Category already exists";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) throws Exception {

        Optional<Category> queriedCategory = categoryRepository.findById(id);

        if (queriedCategory.isEmpty()) {
            throw new Exception("Not Found");
        } else return queriedCategory.get();
    }

    public Category editCategory(Long id, CategoryDTO category) throws Exception {
        Category categoryEdited = this.findCategoryById(id);

        Category categoryName = this.categoryRepository.findAllByName(category.name());

        if (categoryName != null) {
            throw new Exception("Category already exists");
        }
        categoryEdited.setName(category.name());
        categoryRepository.save(categoryEdited);
        return categoryEdited;
    }

    public String deleteCategoryById(Long id) throws Exception {
        this.categoryRepository.deleteById(this.findCategoryById(id).getId());
        return "Category deleted successfully";
    }

}
