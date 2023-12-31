package com.ssilvestre.graphql.youtube_graphql.modules.category;

//import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @MutationMapping()
  CategoryEntity addCategory(@Argument CategoryInput category) {
    var categoryCreated = this.categoryRepository.save(new CategoryEntity(category.name));
    return categoryCreated;
  }

  @Secured("ROLE_USER")
  @QueryMapping()
  Optional<CategoryEntity> categoryById(@Argument UUID id) {
    var category = this.categoryRepository.findById(id);
    return category;
  }

  record CategoryInput(String name) {
  }
  @Secured("ROLE_USER")
  @QueryMapping
  public Iterable<CategoryEntity> allCategories() {
    return categoryRepository.findAll();
  }
}
