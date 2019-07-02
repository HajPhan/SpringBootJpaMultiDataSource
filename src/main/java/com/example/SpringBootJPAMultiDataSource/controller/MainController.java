package com.example.SpringBootJPAMultiDataSource.controller;

import com.example.SpringBootJPAMultiDataSource.dao.AdvertiserDAO;
import com.example.SpringBootJPAMultiDataSource.dao.Employee1DAO;
import com.example.SpringBootJPAMultiDataSource.dao.Employee2DAO;
import com.example.SpringBootJPAMultiDataSource.dao.PublisherDAO;
import com.example.SpringBootJPAMultiDataSource.entity1.PublishersEntity;
import com.example.SpringBootJPAMultiDataSource.entity2.AdvertisersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PublisherDAO publisherDAO;

    @Autowired
    private AdvertiserDAO advertiserDAO;

    @Autowired
    private Employee1DAO employee1Dao;

    @Autowired
    private Employee2DAO employee2Dao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<AdvertisersEntity> advertiser = this.advertiserDAO.listAdvertisers();
        List<PublishersEntity> publisher = this.publisherDAO.listPublishers();

        model.addAttribute("advertisers", advertiser);
        model.addAttribute("publishers", publisher);

        return "home";
    }

    @RequestMapping(value = "/save1/{number}", method = RequestMethod.GET)
    public String saveEmployee1(Model model, @PathVariable Double number) {
        Double result = this.employee1Dao.listEmployee(number);
        model.addAttribute("results", result);
        return "save-page-1";
    }

    @RequestMapping(value = "/save2/{number}", method = RequestMethod.GET)
    public String saveEmployee2(Model model, @PathVariable Double number) {
        Double result = this.employee2Dao.listEmployee(number);
        model.addAttribute("results", result);
        return "save-page-2";
    }


}
