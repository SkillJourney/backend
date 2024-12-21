package skilljourney.web_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import skilljourney.web_backend.models.User;
import skilljourney.web_backend.service.UserService;
import skilljourney.web_backend.utils.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ApiResponse(true, "Liste des utilisateurs récupérée avec succès", users);
        } catch (Exception e) {
            return new ApiResponse(false, "Erreur lors de la récupération des utilisateurs", null);
        }
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return new ApiResponse(false, "Utilisateur non trouvé", null);
            }
            return new ApiResponse(true, "Utilisateur récupéré avec succès", user);
        } catch (Exception e) {
            return new ApiResponse(false, "Erreur lors de la récupération de l'utilisateur", null);
        }
    }

    @PostMapping
    public ApiResponse createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ApiResponse(true, "Utilisateur créé avec succès", createdUser);
        } catch (Exception e) {
            return new ApiResponse(false, "Erreur lors de la création de l'utilisateur", null);
        }
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return new ApiResponse(true, "Utilisateur mis à jour avec succès", user);
        } catch (Exception e) {
            return new ApiResponse(false, "Erreur lors de la mise à jour de l'utilisateur", null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ApiResponse(true, "Utilisateur supprimé avec succès", null);
        } catch (Exception e) {
            return new ApiResponse(false, "Erreur lors de la suppression de l'utilisateur", null);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.append(fieldError.getDefaultMessage()).append(" ")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(false, errors.toString().trim(), null));
    }
}
