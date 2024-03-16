package com.example.manager_tour_springboot.controller.entity;

import com.example.manager_tour_springboot.model.Type;
import com.example.manager_tour_springboot.service.type.ITypeService;
import org.hibernate.annotations.TypeBinderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService iTypeService;

    @GetMapping
    public ModelAndView formListType() {
        ModelAndView modelAndView = new ModelAndView("/type/list");
        Iterable<Type> types = iTypeService.findAll();
        modelAndView.addObject("type", types);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreateType() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createType(@ModelAttribute("type") Type type) {
        iTypeService.save(type);
        return "redirect:/type";
    }

    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<Type>type = iTypeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        }
        return null;
    }

    @PostMapping("/update/{id}")
    public String UpdateType(@ModelAttribute("type") Type type) {
        iTypeService.save(type);
        return "redirect:/type";
    }

    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable Long id) {
        Optional<Type> type = iTypeService.findById(id);
        if (type.isPresent()) {
            iTypeService.remove(id);
            return "redirect:/type";
        }
        return null;
    }

}
