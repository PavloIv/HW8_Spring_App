package com.ip.hw8.controller;

import com.ip.hw8.entity.Producer;
import com.ip.hw8.entity.Product;
import com.ip.hw8.repository.ProducerRepository;
import com.ip.hw8.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;


    @GetMapping("/allProduct")
    public ModelAndView allProduct(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return new ModelAndView("product/allProduct");
    }

    @GetMapping("/createProductForm")
    public ModelAndView createForm(Model model){
        model.addAttribute("producers",producerRepository.findAll());
        return new ModelAndView("product/createProductForm");
    }

    @PostMapping("/createProduct")
    public ModelAndView createProduct(Model model,
                                      @RequestParam(name = "productName")String productName,
                                      @RequestParam(name = "productPrice")BigDecimal productPrice,
                                      @RequestParam(name = "producerId")Long producerId){
        Producer producer;
        producer = producerRepository.getReferenceById(producerId);

        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        product.setProducer(producer);

        productRepository.save(product);

        model.addAttribute("producers",producerRepository.findAll());

        return new ModelAndView("product/createProduct");
    }

    @GetMapping("/updateProductForm")
    public ModelAndView updateForm(Model model){

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("producers",producerRepository.findAll());

        return new ModelAndView("product/updateProductForm");
    }

    @PostMapping("/updateProduct")
    public ModelAndView updateProduct(Model model,
                                      @RequestParam(name = "productId")Long productId,
                                      @RequestParam(name = "productName")String productName,
                                      @RequestParam(name = "productPrice")BigDecimal productPrice,
                                      @RequestParam(name = "producerId")Long producerId){


        Producer producer;
        producer = producerRepository.getReferenceById(producerId);

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setPrice(productPrice);
        product.setProducer(producer);

        productRepository.save(product);

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("producers",producerRepository.findAll());

        return new ModelAndView("product/updateProduct");
    }

    @GetMapping("/deleteProductForm")
    public ModelAndView deleteForm(Model model){

        model.addAttribute("products", productRepository.findAll());

        return new ModelAndView("product/deleteProductForm");
    }

    @PostMapping("/deleteProduct")
    public ModelAndView deleteProduct(Model model,
                                      @RequestParam(name = "productId")Long productId){

        model.addAttribute("products", productRepository.findAll());
        productRepository.deleteById(productId);

        return new ModelAndView("product/deleteProduct");
    }
}
