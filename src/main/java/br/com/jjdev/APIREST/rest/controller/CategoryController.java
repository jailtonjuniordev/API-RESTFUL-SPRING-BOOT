package br.com.jjdev.APIREST.rest.controller;

import br.com.jjdev.APIREST.domain.category.Category;
import br.com.jjdev.APIREST.rest.dto.CategoryDTO;
import br.com.jjdev.APIREST.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) throws Exception {
        return new ResponseEntity<>(this.categoryService.editCategory(id, categoryDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.categoryService.deleteCategoryById(id), HttpStatus.GONE);
    }
}
