package com.ip.hw8.controller;

import com.ip.hw8.service.ProducerService;
import com.ip.hw8.service.ProductService;
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
    private final ProducerService producerService;
    private final ProductService productService;

    @GetMapping("/allProduct")
    public ModelAndView allProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return new ModelAndView("product/allProduct");
    }

    @GetMapping("/createProductForm")
    public ModelAndView createForm(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return new ModelAndView("product/createProductForm");
    }

    @PostMapping("/createProduct")
    public ModelAndView createProduct(Model model,
                                      @RequestParam(name = "productName") String productName,
                                      @RequestParam(name = "productPrice") BigDecimal productPrice,
                                      @RequestParam(name = "producerId") Long producerId) {

        productService.createProduct(productName, productPrice, producerId);

        model.addAttribute("producers", producerService.findAll());

        return new ModelAndView("product/createProduct");
    }

    @GetMapping("/updateProductForm")
    public ModelAndView updateForm(Model model) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("producers", producerService.findAll());

        return new ModelAndView("product/updateProductForm");
    }

    @PostMapping("/updateProduct")
    public ModelAndView updateProduct(Model model,
                                      @RequestParam(name = "productId") Long productId,
                                      @RequestParam(name = "productName") String productName,
                                      @RequestParam(name = "productPrice") BigDecimal productPrice,
                                      @RequestParam(name = "producerId") Long producerId) {
        productService.updateProduct(productId, productName, productPrice, producerId);

        model.addAttribute("products", productService.findAll());
        model.addAttribute("producers", producerService.findAll());

        return new ModelAndView("product/updateProduct");
    }

    @GetMapping("/deleteProductForm")
    public ModelAndView deleteForm(Model model) {

        model.addAttribute("products", productService.findAll());

        return new ModelAndView("product/deleteProductForm");
    }

    @PostMapping("/deleteProduct")
    public ModelAndView deleteProduct(Model model,
                                      @RequestParam(name = "productId") Long productId) {

        model.addAttribute("products", productService.findAll());

        productService.deleteProduct(productId);

        return new ModelAndView("product/deleteProduct");
    }
}
