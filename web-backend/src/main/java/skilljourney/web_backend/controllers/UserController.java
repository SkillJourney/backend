package skilljourney.web_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import skilljourney.web_backend.models.User;
import skilljourney.web_backend.service.UserService;
import skilljourney.web_backend.utils.ApiResponse;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ApiResponse(true, "Liste des utilisateurs récupérée avec succès", users);
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }
        return new ApiResponse(true, "Utilisateur récupéré avec succès", user);
    }

    @PostMapping
    public ApiResponse createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ApiResponse(true, "Utilisateur créé avec succès", createdUser);
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return new ApiResponse(true, "Utilisateur mis à jour avec succès", user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse(true, "Utilisateur supprimé avec succès", null);
    }
}
