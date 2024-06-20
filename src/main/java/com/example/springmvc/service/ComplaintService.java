package com.example.springmvc.service;

import com.example.springmvc.model.Complaint;
import com.example.springmvc.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint saveComplaint(Complaint complaint) {
        complaint.setRegistrationDateTime(LocalDateTime.now());
        return complaintRepository.save(complaint);
    }

    public List<Complaint> getOpenComplaints() {
        return complaintRepository.findByStatus("open");
    }

    public List<Complaint> getComplaintHistory(String userId) {
        return complaintRepository.findByUserId(userId);
    }
}