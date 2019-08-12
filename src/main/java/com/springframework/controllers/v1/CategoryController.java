package com.springframework.controllers.v1;

import com.springframework.api.v1.model.CategoryDTO;
import com.springframework.api.v1.model.CategoryListDTO;
import com.springframework.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Category")
@Controller
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "View list of Categories")
    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        return new ResponseEntity<>
                 (new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @ApiOperation(value = "Get a Category by name")
    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }
}