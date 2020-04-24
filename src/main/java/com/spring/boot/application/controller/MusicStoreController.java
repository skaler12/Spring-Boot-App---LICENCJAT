package com.spring.boot.application.controller;


import com.spring.boot.application.dto.ChangePasswordForm;
import com.spring.boot.application.entity.MusicStore;
import com.spring.boot.application.entity.Opinion;
import com.spring.boot.application.entity.User;
import com.spring.boot.application.service.MusicStoreService;
import com.spring.boot.application.service.OpinionService;
import com.spring.boot.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class MusicStoreController {
    @Autowired
    OpinionService opinionService;
    @Autowired
    MusicStoreService musicStoreService;
    @Autowired
    UserService userService;

     @GetMapping("/musicStore")
    public String opinion(Model model) {
      model.addAttribute("opinion", new Opinion());
     model.addAttribute("opinions", opinionService.findAll());
     model.addAttribute("musicStore", new MusicStore());
     model.addAttribute("allStores", musicStoreService.findAll());
         model.addAttribute("listTab","active");

         return "musicStore-form/Music-view";
    }
    @PostMapping("/musicStore")
    public String createOpinion(Opinion opinion, Model model, MusicStore musicStore) {
        opinionService.save(opinion);
        model.addAttribute("opinion", new Opinion());
        model.addAttribute("opinions", opinionService.findAll());
        model.addAttribute("stores", musicStoreService.findAll());
        return "musicStore-form/Music-view";
    }
    @GetMapping("/musicStore/{id}")
    public String getDetails(@PathVariable(name = "id") Long storeId, Model model) {
        MusicStore musicStore = musicStoreService.findById(storeId).get();
        model.addAttribute("musicStore", musicStore);
        model.addAttribute("allStores", musicStoreService.findAll());
        return "musicStore-form/Music-view";
    }
}
