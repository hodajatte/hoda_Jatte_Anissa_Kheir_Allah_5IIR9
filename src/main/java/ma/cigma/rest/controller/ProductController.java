package ma.cigma.rest.controller;

import ma.cigma.rest.model.Product;
import ma.cigma.rest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {

        @Autowired
        private IProductService service;

        @GetMapping(value = "/products",produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
        public List<Product> getAll(){

            return service.getAll();
        }
        @GetMapping(value = "/products/{id}")
        public Product getProductById(@PathVariable(value = "id") Long productId){
            return service.getById(productId);
        }
        @PostMapping(value = "/products")
        public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product){
            service.create(product);
            return new ResponseEntity<>("product is created succefully",
                    HttpStatus.CREATED);
        }
        /**Pour modifier  un produit par son id***/
        @PutMapping(value = "/products/{id}")
        public ResponseEntity<Object>updateProduct(@PathVariable(name = "id")Long productId,@RequestBody Product product) {
            Product productFound = service.getById(productId);
            if (productFound == null)
                return ResponseEntity.notFound().build();
            service.update(productId, product);
            return new ResponseEntity<>("product is updated successfully", HttpStatus.OK);

        }
        @DeleteMapping(value = "/products/{id}")
        public ResponseEntity<Object>deleteProduct(@PathVariable(name ="id") Long productId){
            Product productFound =service.getById(productId);
            if (productFound ==null)
                return ResponseEntity.notFound().build();
            service.delete(productId);
            return new ResponseEntity<>("product is delete successfully" , HttpStatus.OK);
        }
        public IProductService getService(){
            return service;
        }
        public void setService(IProductService service){
            this.service=service;
        }


    }
