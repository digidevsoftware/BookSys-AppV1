package digidevsoftware.shiftmanagementsystem.config;

import digidevsoftware.shiftmanagementsystem.model.UserAccount;
import digidevsoftware.shiftmanagementsystem.model.UserRole;
import digidevsoftware.shiftmanagementsystem.service.UserAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedDefaultAdmin(
            UserAccountService userAccountService,
            PasswordEncoder passwordEncoder,
            @Value("${app.seed.admin.username}") String adminUsername,
            @Value("${app.seed.admin.password}") String adminPassword
    ) {
        return args -> {
            if (userAccountService.findByUsername(adminUsername).isEmpty()) {
                UserAccount admin = new UserAccount();
                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRole(UserRole.ADMIN);
                userAccountService.save(admin);
            }
        };
    }
}
