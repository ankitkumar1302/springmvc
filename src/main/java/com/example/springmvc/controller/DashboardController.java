package com.example.springmvc.controller;

import com.example.springmvc.model.Complaint;
import com.example.springmvc.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";  // This resolves to the dashboard.html template
    }

    @PostMapping("/submitComplaint")
    public String submitComplaint(@RequestParam("type") String type,
                                  @RequestParam("description") String description,
                                  @RequestParam("image") MultipartFile image,
                                  @RequestParam("userId") String userId,
                                  Model model) {
        Complaint complaint = new Complaint();
        complaint.setType(type);
        complaint.setDescription(description);
        complaint.setStatus("open");
        complaint.setUserId(userId);

        if (!image.isEmpty()) {
            try {
                String imagePath = "/images/" + image.getOriginalFilename();
                File file = new File("src/main/resources/static" + imagePath);
                image.transferTo(file);
                complaint.setImageUrl(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        complaintService.saveComplaint(complaint);
        model.addAttribute("message", "Complaint submitted successfully!");
        return "dashboard";  // This resolves to the dashboard.html template
    }

    @GetMapping("/viewComplaints")
    public String viewComplaints(Model model) {
        List<Complaint> openComplaints = complaintService.getOpenComplaints();
        model.addAttribute("complaints", openComplaints);
        return "viewComplaints";  // This resolves to the viewComplaints.html template
    }

    @GetMapping("/complaintHistory/{userId}")
    public String complaintHistory(@PathVariable("userId") String userId, Model model) {
        List<Complaint> complaints = complaintService.getComplaintHistory(userId);
        model.addAttribute("complaints", complaints);
        return "complaintHistory";  // This resolves to the complaintHistory.html template
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // This resolves to the login.html template
    }
}

/*
    TODO: I am not able to send the images to the database have to finish that.
    TODO: Create the conditions in the login after validation got access to the dashboard.
    TODO: Connect with same database. where we can access the data from the database.
    TODO: Create particular tables for that.
    TODO: Email Validation
    TODO: Login Form Fields {email,password}
    TODO: Make the UI clean and good
    TODO: Make the user ID Primary Key

 */