package com.ip.hw8.controller;

import com.ip.hw8.entity.Producer;
import com.ip.hw8.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;

    @GetMapping("/allProducer")
    public ModelAndView allProducer(Model model) {
        model.addAttribute("producers", producerService.findAll());
        return new ModelAndView("producer/allProducer");
    }

    @GetMapping("/createProducer")
    public String createForm() {
        return "producer/createProducer";
    }

    @PostMapping("/createProducer")
    public String createProducer(Model model,
            @RequestParam(name = "producerName") String producerName) {
        Producer producerAudit = producerService.findByName(producerName);
        if (producerAudit != null){
            model.addAttribute("producerDuplicate", "Producer with this name already exist!!!\nTry again.");
            return "producer/createProducer";
        }

        producerService.createProducer(producerName);
        model.addAttribute("producerCreate", "Producer create successful");
        return "producer/createProducer";
    }

    @GetMapping("/updateProducer")
    public String updateForm(Model model) {

        model.addAttribute("producers", producerService.findAll());

        return "producer/updateProducer";
    }

    @PostMapping("/updateProducer")
    public String updateProducer(Model model,
                                       @RequestParam(name = "producerId") Long producerId,
                                       @RequestParam(name = "producerName") String producerName) {

        model.addAttribute("producers", producerService.findAll());

        Producer producerAudit = producerService.findByName(producerName);
        if (producerAudit != null){
            model.addAttribute("producerDuplicate", "Producer with this name already exist!!!\nTry again.");
            return "producer/updateProducer";
        }

        producerService.updateProducer(producerId,producerName);
        model.addAttribute("producerUpdate","Producer update successful");
        return "producer/updateProducer";
    }

    @GetMapping("/deleteProducer")
    public String deleteForm(Model model) {

        model.addAttribute("producers", producerService.findAll());

        return "producer/deleteProducer";
    }

    @PostMapping("/deleteProducer")
    public String deleteProducer(Model model,
                                 @RequestParam(name = "producerId") Long producerId) {
        producerService.deleteProducer(producerId);
        return "redirect:deleteProducer";
    }
}
