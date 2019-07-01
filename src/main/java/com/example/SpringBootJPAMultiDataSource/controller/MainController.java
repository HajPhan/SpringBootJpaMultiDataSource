package com.example.SpringBootJPAMultiDataSource.controller;

import com.example.SpringBootJPAMultiDataSource.dao.AdvertiserDAO;
import com.example.SpringBootJPAMultiDataSource.dao.PublisherDAO;
import com.example.SpringBootJPAMultiDataSource.entity1.PublishersEntity;
import com.example.SpringBootJPAMultiDataSource.entity2.AdvertisersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PublisherDAO publisherDAO;

    @Autowired
    private AdvertiserDAO advertiserDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<AdvertisersEntity> advertiser = this.advertiserDAO.listAdvertisers();
        List<PublishersEntity> publisher = this.publisherDAO.listPublishers();

        model.addAttribute("advertisers", advertiser);
        model.addAttribute("publishers", publisher);

        return "home";
    }

}
