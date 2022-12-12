package com.ip.hw8.controller;

import com.ip.hw8.entity.Product;
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

    @GetMapping("/createProduct")
    public String createForm(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return "product/createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(Model model,
                                @RequestParam(name = "productName") String productName,
                                @RequestParam(name = "productPrice") BigDecimal productPrice,
                                @RequestParam(name = "producerId") Long producerId) {
        model.addAttribute("producers", producerService.findAll());
        Product productAudit = productService.findByName(productName);
        if (productAudit != null && productAudit.getProducer().getId() == producerId) {
            model.addAttribute("productDuplicate", "Product with this name and producer already exist!!!\nTry again.");
            return "product/createProduct";
        }
        productService.createProduct(productName, productPrice, producerId);
        model.addAttribute("productCreate", "product create successful");
        return "product/createProduct";
    }

    @GetMapping("/updateProduct")
    public String updateForm(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("producers", producerService.findAll());
        return "product/updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Model model,
                                @RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "productName") String productName,
                                @RequestParam(name = "productPrice") BigDecimal productPrice,
                                @RequestParam(name = "producerId") Long producerId) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("producers", producerService.findAll());
        Product productAudit = productService.findByName(productName);
        if (productAudit != null &&
                productAudit.getProducer().getId() == producerId &&
                productAudit.getId() != productId) {
            model.addAttribute("productDuplicate",
                    "Product with this name and producer already exist!!!\nTry again.");
            return "product/updateProduct";
        }
        productService.updateProduct(productId, productName, productPrice, producerId);
        model.addAttribute("productUpdate", "Product update successful");
        return "product/updateProduct";
    }

    @GetMapping("/deleteProduct")
    public String deleteForm(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/deleteProduct";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(Model model,
                                @RequestParam(name = "productId") Long productId) {
        model.addAttribute("products", productService.findAll());
        productService.deleteProduct(productId);
        return "redirect:deleteProduct";
    }
}
