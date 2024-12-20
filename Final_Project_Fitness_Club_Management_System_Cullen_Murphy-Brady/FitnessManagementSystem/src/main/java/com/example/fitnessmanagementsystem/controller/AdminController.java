package com.example.fitnessmanagementsystem.controller;

import com.example.fitnessmanagementsystem.model.Member;
import com.example.fitnessmanagementsystem.model.Trainer;
import com.example.fitnessmanagementsystem.repositories.MemberRepository;
import com.example.fitnessmanagementsystem.repositories.TrainerRepository;
import com.example.fitnessmanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for handling all admin-related operations.
 * Has endpoints for managing trainers, members, and schedules.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    // Injecting AdminService to handle business logic related to admin operations
    @Autowired
    private AdminService adminService;

    // Injecting repositories for accessing members and trainers data
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    /**
     * Displays the admin dashboard with a breakdown of all members and trainers in
     * the system.
     * 
     * @param model Model object that will pass data to the view.
     * @return The name of the view for the dashboard.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Fetch all members and trainers and add them to the model
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("trainers", trainerRepository.findAll());
        return "admin/dashboard"; // Return view name for the admin dashboard
    }

    /**
     * Function to add new members into the system.
     * 
     * @param username       The username of the new member
     * @param password       The password of the new member
     * @param membershipType The type of membership the member has
     * @param trainerId      ID of the trainer assigned to the member, optional
     * @return Redirect to the admin dashboard
     */
    @PostMapping("/members/add")
    public String addMember(@RequestParam String username, @RequestParam String password,
            @RequestParam String membershipType, @RequestParam(required = false) Long trainerId) {
        // Call service to add a new member
        adminService.addMember(username, password, membershipType, trainerId);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after adding
    }

    /**
     * Deletes a member based on their ID.
     * 
     * @param id The ID of the member that needs to be deleted.
     * @return Redirect to the admin dashboard.
     */
    @PostMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        // Call service to delete the member by ID
        adminService.deleteMember(id);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after deletion
    }

    /**
     * Adds a new trainer into the system.
     * 
     * @param username       The username of the new trainer.
     * @param password       The password of the new trainer.
     * @param specialization The trainer's specialty.
     * @param shift          The trainer's regular shift time.
     * @return Redirect to the admin dashboard.
     */
    @PostMapping("/trainers/add")
    public String addTrainer(@RequestParam String username, @RequestParam String password,
            @RequestParam String specialization, @RequestParam String shift) {
        // Call service to add a new trainer
        adminService.addTrainer(username, password, specialization, shift);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after adding
    }

    /**
     * Deletes a trainer based on their ID.
     * 
     * @param id The ID of the trainer to be deleted.
     * @return Redirect to admin dashboard.
     */
    @PostMapping("/trainers/delete/{id}")
    public String deleteTrainer(@PathVariable Long id) {
        // Call service to delete the trainer by ID
        adminService.deleteTrainer(id);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after deletion
    }

    /**
     * Displays the schedules for all members and trainers.
     * 
     * @param model Model object that will pass data to the view.
     * @return The name of the view for schedules.
     */
    @GetMapping("/schedules")
    public String viewSchedules(Model model) {
        // Fetch all members, trainers, and schedules, and add them to the model
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("trainers", trainerRepository.findAll());
        model.addAttribute("schedules", adminService.getAllSchedules());
        return "admin/schedules"; // Return view name for the schedules page
    }

    /**
     * Adds a new schedule for a member and trainer.
     * 
     * @param memberId  The ID of the member to be added to the schedule.
     * @param trainerId The ID of the trainer to be added to the schedule.
     * @param startTime The time that the activity starts.
     * @param endTime   The time that the activity ends.
     * @param activity  The activity assigned in the schedule.
     * @return Redirects to the schedules page.
     */
    @PostMapping("/schedules/add")
    public String addSchedule(@RequestParam Long memberId, @RequestParam Long trainerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime,
            @RequestParam String activity) {
        // Call service to add a new schedule
        adminService.addSchedule(memberId, trainerId, startTime, endTime, activity);
        return "redirect:/admin/schedules"; // Redirect to the schedules page after adding
    }

    /**
     * Displays the edit form for the desired member.
     * 
     * @param id    The ID of the member to edit.
     * @param model The model object used to pass data to the view.
     * @return The view name for the edit member page.
     */
    @GetMapping("/members/edit/{id}")
    public String editMember(@PathVariable Long id, Model model) {
        // Retrieve the member by ID and add it to the model
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        model.addAttribute("member", member);
        return "admin/edit-member"; // Return view name for the edit member page
    }

    /**
     * Handles the actual submission of the update member form.
     * Updates the member info that is in the system.
     * 
     * @param id             The ID of the member to update.
     * @param username       The updated username of the member
     * @param password       The updated password of the member. (optional)
     * @param membershipType The updated membership type.
     * @return Redirects to the admin dashboard on successful updates.
     */
    @PostMapping("/members/update/{id}")
    public String updateMember(@PathVariable Long id, @RequestParam String username,
            @RequestParam(required = false) String password,
            @RequestParam String membershipType) {
        // Call service to update the member details
        adminService.updateMember(id, username, password, membershipType);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after updating
    }

    /**
     * Display the edit form for the desired trainer.
     * 
     * @param id    The ID of the trainer to edit.
     * @param model The model object used to pass data to the view.
     * @return The view name for the edit trainer page.
     */
    @GetMapping("/trainers/edit/{id}")
    public String editTrainer(@PathVariable Long id, Model model) {
        // Retrieve the trainer by ID and add it to the model
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        model.addAttribute("trainer", trainer);
        return "admin/edit-trainer"; // Return view name for the edit trainer page
    }

    /**
     * Handles the submission of the trainer update form.
     * Updates the trainer info in the system.
     * 
     * @param id             The ID of the trainer to update.
     * @param username       The updated username for the trainer.
     * @param password       The updated password for the trainer. (optional)
     * @param specialization The updated specialty of the trainer.
     * @param shift          The updated shift for the trainer.
     * @return Redirects to the admin dashboard after successfully updating.
     */
    @PostMapping("/trainers/update/{id}")
    public String updateTrainer(@PathVariable Long id, @RequestParam String username,
            @RequestParam(required = false) String password,
            @RequestParam String specialization, @RequestParam String shift) {
        // Call service to update the trainer details
        adminService.updateTrainer(id, username, password, specialization, shift);
        return "redirect:/admin/dashboard"; // Redirect to the dashboard after updating
    }
}
