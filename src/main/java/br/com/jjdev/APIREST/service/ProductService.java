package br.com.jjdev.APIREST.service;

import br.com.jjdev.APIREST.domain.product.Product;
import br.com.jjdev.APIREST.repository.ProductRepository;
import br.com.jjdev.APIREST.rest.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public String createProduct(ProductDTO productDTO) throws Exception {
        Product productQueried = this.productRepository.findByName(productDTO.name());

        if (productQueried == null) {
            Product createdProduct = new Product();
            BeanUtils.copyProperties(productDTO, createdProduct);

            productRepository.save(createdProduct);
            return "Product created successfully";
        } else return "Product already exists";
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product findProductById(Long id) throws Exception {
        Optional<Product> queriedProduct = this.productRepository.findById(id);

        if (queriedProduct.isEmpty()) {
            throw new Exception("Not Found");
        } else return queriedProduct.get();
    }

    public Product editProductById(Long id, ProductDTO productDTO) throws Exception {
        Product editedProduct = this.findProductById(id);

        if (productDTO.category() != null) {
            editedProduct.setCategory(this.categoryService.findCategoryById(productDTO.category().getId()));
        }

        if (productDTO.name() != null) {
            editedProduct.setName(productDTO.name());
        }

        if (productDTO.price() != null) {
            editedProduct.setPrice(productDTO.price());
        }
        this.productRepository.save(editedProduct);
        return editedProduct;
    }

    public String deleteProductById(Long id) throws Exception {
        this.productRepository.deleteById(this.findProductById(id).getId());

        return "Product deleted successfully";
    }

}