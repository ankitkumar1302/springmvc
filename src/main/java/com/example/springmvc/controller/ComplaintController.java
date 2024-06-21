package com.example.springmvc.controller;

import com.example.springmvc.model.Complaint;
import com.example.springmvc.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@Controller
//public class ComplaintController {
//
//    @Autowired
//    private ComplaintService complaintService;
//
//    @GetMapping("/viewComplaints")
//    public String viewComplaints(Model model) {
//        List<Complaint> complaints = complaintService.getOpenComplaints();
//        model.addAttribute("complaints", complaints);
//        return "viewComplaints";
//    }
//}
