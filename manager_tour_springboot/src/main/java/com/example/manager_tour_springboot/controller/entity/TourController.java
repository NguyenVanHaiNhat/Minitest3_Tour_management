package com.example.manager_tour_springboot.controller.entity;

import com.example.manager_tour_springboot.model.Tour;
import com.example.manager_tour_springboot.model.Type;
import com.example.manager_tour_springboot.service.tour.ITourService;
import com.example.manager_tour_springboot.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/tour")
public class TourController {
    @Autowired
    private ITourService iTourService;
    @Autowired
    private ITypeService iTypeService;
    @ModelAttribute("type")
    public Iterable<Type> listType(){
       return iTypeService.findAll();
    }
    @GetMapping
    public ModelAndView formListTour() {
        ModelAndView modelAndView = new ModelAndView("/tour/list");
        Iterable<Tour> tours = iTourService.findAll();
        modelAndView.addObject("tour", tours);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreateTour() {
        ModelAndView modelAndView = new ModelAndView("/tour/create");
        modelAndView.addObject("tour", new Tour());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createTour(@ModelAttribute("tour") Tour tour) {
        iTourService.save(tour);
        return "redirect:/tour";
    }

    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<Tour> tour = iTourService.findById(id);
        if (tour.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/tour/update");
            modelAndView.addObject("tour", tour.get());
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/update/{id}")
    public String UpdateTour(@ModelAttribute("tour") Tour tour) {
        iTourService.save(tour);
        return "redirect:/tour";
    }

    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable Long id) {
        Optional<Tour> tour = iTourService.findById(id);
        if (tour.isPresent()) {
            iTourService.remove(id);
            return "redirect:/tour";
        }
        return null;
    }


}
