package br.com.jjdev.APIREST.rest.controller;

import br.com.jjdev.APIREST.domain.product.Product;
import br.com.jjdev.APIREST.rest.dto.ProductDTO;
import br.com.jjdev.APIREST.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductDTO productDTO) throws Exception {
        return new ResponseEntity<>(this.productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.productService.findProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws Exception {
        return new ResponseEntity<>(this.productService.editProductById(id, productDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.productService.deleteProductById(id), HttpStatus.GONE);
    }
}
