package com.ip.hw8.controller;

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

    @GetMapping("/createProducerForm")
    public ModelAndView createForm() {
        return new ModelAndView("producer/createProducerForm");
    }

    @PostMapping("/createProducer")
    public ModelAndView createProducer(@RequestParam(name = "producerName") String producerName) {

        producerService.createProducer(producerName);

        return new ModelAndView("producer/createProducer");
    }

    @GetMapping("/updateProducerForm")
    public ModelAndView updateForm(Model model) {

        model.addAttribute("producers", producerService.findAll());

        return new ModelAndView("producer/updateProducerForm");
    }

    @PostMapping("/updateProducer")
    public ModelAndView updateProducer(Model model,
                                       @RequestParam(name = "producerId") Long producerId,
                                       @RequestParam(name = "producerName") String producerName) {

        model.addAttribute("producers", producerService.findAll());

        producerService.updateProducer(producerId,producerName);

        return new ModelAndView("producer/updateProducer");
    }

    @GetMapping("/deleteProducerForm")
    public ModelAndView deleteForm(Model model) {

        model.addAttribute("producers", producerService.findAll());

        return new ModelAndView("producer/deleteProducerForm");
    }

    @PostMapping("/deleteProducer")
    public ModelAndView deleteProducer(@RequestParam(name = "producerId") Long producerId) {
        producerService.deleteProducer(producerId);
        return new ModelAndView("producer/deleteProducer");
    }
}
