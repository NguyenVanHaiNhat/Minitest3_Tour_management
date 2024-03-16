package com.example.tours.controller;

import com.example.tours.model.Tour;
import com.example.tours.model.Type;
import com.example.tours.service.ITourService;
import com.example.tours.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {
    @Autowired
    private ITourService iTourService;
    @Autowired
    private ITypeService iTypeService;

    @ModelAttribute("types")
    public Iterable<Type> listTypes(){
        return iTypeService.findAll();
    }

    @GetMapping
    public ModelAndView listTour(){
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = iTourService.findAll();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateTour(){
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tours", new Tour());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createTour(@ModelAttribute("tour") Tour tour){
        iTourService.save(tour);
        return "redirect:/tours";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        Optional<Tour> tourOptional = iTourService.findById(id);
        if (tourOptional.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/tour/edit");
            modelAndView.addObject("tours", tourOptional.get());
            return modelAndView;
        }
        return new ModelAndView("error");
    }
    @PostMapping("/edit/{id}")
    public String editTour(@ModelAttribute Tour tour){
        iTourService.save(tour);
        return "redirect:/tours";
    }
    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id){
        Optional<Tour> tourOptional = iTourService.findById(id);
        if (tourOptional.isPresent()){
            iTourService.remove(id);
            return "redirect:/tours";
        }
        return "redirect:/error";
    }
}
