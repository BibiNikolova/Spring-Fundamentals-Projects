package com.example.shoppinglistapp.service;

import com.example.shoppinglistapp.model.dto.CreateProductDTO;
import com.example.shoppinglistapp.model.dto.ProductByCategoryDTO;
import com.example.shoppinglistapp.model.dto.ProductViewDTO;
import com.example.shoppinglistapp.model.entity.Category;
import com.example.shoppinglistapp.model.entity.Product;
import com.example.shoppinglistapp.model.enums.CategoryName;
import com.example.shoppinglistapp.repository.CategoryRepo;
import com.example.shoppinglistapp.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public boolean create(CreateProductDTO createProductDTO) {

        Category byName = this.categoryRepo.findByName(createProductDTO.getCategoryName()).orElseThrow();

        Product product = Product.builder()
                .name(createProductDTO.getName())
                .description(createProductDTO.getDescription())
                .neededBefore(createProductDTO.getNeededBefore())
                .price(createProductDTO.getPrice())
                .category(byName)
                .build();

        this.productRepo.save(product);

        return true;
    }

    private ProductViewDTO viewProductDTO(Product product) {

        return ProductViewDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    public Set<ProductViewDTO> getProductsByCategory(Category byName) {

        return this.productRepo.findByCategory(byName)
                .stream()
                .map(this::viewProductDTO)
                .collect(Collectors.toSet());
    }

    public ProductByCategoryDTO getProducts() {

        ProductByCategoryDTO product = new ProductByCategoryDTO();
        product.setFood(getProductsByCategory(extracted(CategoryName.FOOD)));
        product.setDrink(getProductsByCategory(extracted(CategoryName.DRINK)));
        product.setHousehold(getProductsByCategory(extracted(CategoryName.HOUSEHOLD)));
        product.setOther(getProductsByCategory(extracted(CategoryName.OTHER)));
        return product;
    }

    public BigDecimal getTotalPrice() {

        return this.productRepo.findAll()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

    }

    public void buyProduct(Long id) {

        Product current = this.productRepo.findById(id).orElseThrow();
        this.productRepo.delete(current);

    }

    public void buyAllProducts() {

        this.productRepo.deleteAll();

    }

    private Category extracted(CategoryName categoryName) {
        return this.categoryRepo.findByName(categoryName).orElseThrow();
    }
}

