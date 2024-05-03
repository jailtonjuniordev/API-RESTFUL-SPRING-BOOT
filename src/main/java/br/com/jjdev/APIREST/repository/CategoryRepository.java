package br.com.jjdev.APIREST.repository;

import br.com.jjdev.APIREST.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findAllByName(String name);
}
