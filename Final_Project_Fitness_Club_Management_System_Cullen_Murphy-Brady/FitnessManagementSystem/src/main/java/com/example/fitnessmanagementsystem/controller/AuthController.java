package com.example.fitnessmanagementsystem.controller;

import com.example.fitnessmanagementsystem.model.User;
import com.example.fitnessmanagementsystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for managing user authentication.
 * Handles login and logout functions.
 */
@Controller
public class AuthController {

    // Automatically injects UserService dependency into this controller
    @Autowired
    private UserService userService;

    /**
     * Displays the login page.
     *
     * @return The name of the view for the login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        // Directs the user to the login view when they navigate to "/login"
        return "login";
    }

    /**
     * Handles login functionality for users.
     * Validates the user credentials and redirects them to the proper dashboard
     * based on
     * their role (ADMIN, TRAINER, or MEMBER).
     *
     * @param username           The username entered by the user.
     * @param password           The password entered by the user.
     * @param session            The HTTP session to store all the user details.
     * @param redirectAttributes Attributes to pass error messages when redirecting.
     * @return Redirects to the proper dashboard based on role, or login page if
     *         auth fails.
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            // Logs the login attempt for debugging purposes
            System.out.println("Login attempt for username: " + username);

            // Calls the login method from UserService to authenticate the user
            User user = userService.login(username, password);

            // Logs the retrieved user details
            System.out.println("User found: " + user.getUsername());
            System.out.println("User ID: " + user.getId());
            System.out.println("User Role: " + user.getRole());

            // Stores user details in the session for future reference
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());

            // Logs the session attributes for debugging purposes
            System.out.println("Session userId: " + session.getAttribute("userId"));
            System.out.println("Session userRole: " + session.getAttribute("userRole"));

            // Determines the redirect URL based on the user's role
            String redirectUrl = switch (user.getRole()) {
                case "ADMIN" -> "redirect:/admin/dashboard";
                case "TRAINER" -> "redirect:/trainer/dashboard";
                case "MEMBER" -> "redirect:/member/dashboard";
                default -> "redirect:/login";
            };

            // Logs the redirection URL for debugging purposes
            System.out.println("Redirecting to: " + redirectUrl);

            // Redirects the user to the determined URL
            return redirectUrl;
        } catch (RuntimeException e) {
            // Logs any login errors encountered during the login process
            System.out.println("Login error: " + e.getMessage());

            // Passes the error message to the login page for display
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    /**
     * Handles logging out of a session.
     * Invalidates the session and redirects to the login page.
     *
     * @param session The HTTP session to be invalidated.
     * @return Redirects to the login page.
     */
    @GetMapping
    public String logout(HttpSession session) {
        // Invalidates the current session, effectively logging the user out
        session.invalidate();
        // Redirects the user to the login page
        return "redirect:/login";
    }
}
