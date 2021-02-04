package com.example.microservices.product_catalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCatalogService {

    @Value("${server.port}")
    String serverPort;

    private static ArrayList<Product> mongoTemplate = new ArrayList<Product>();
    
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return product;
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        mongoTemplate.add(product);      
        return product;
    }

    @GetMapping("/product/{id}")
    public Product getProductDetails(@PathVariable  String id){
	for(Product p : mongoTemplate) {
	    if(p.getId().equalsIgnoreCase(id)) {
		return p;
	    }
	}
	return new Product();
    }


    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable String id) {
        Product toDeleteProduct = new Product();
        toDeleteProduct.setId(id);

        mongoTemplate.remove(toDeleteProduct);
        return "Product Deleted-"+id;
    }

    @GetMapping("/product")
    public List<Product> getProductList(){
        return mongoTemplate;
    }

    @GetMapping("/product/version")
    public String getVersionInfo(){
        return "Version - V1";
    }


}