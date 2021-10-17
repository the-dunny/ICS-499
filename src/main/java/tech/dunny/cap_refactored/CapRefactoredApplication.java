package tech.dunny.cap_refactored;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = {"tech.dunny.repositories"})
@EntityScan("tech.dunny.model")
public class CapRefactoredApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapRefactoredApplication.class, args);
    }
}

/*@Component
class CapstoneCommandLineRunner implements CommandLineRunner{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setPlayerID((playerRepository.count()+1));
        player.setUserName("Jordan Dodd1");
        player.setPassword("pwordCommandLineRunner2");
        player.setBestScore(1234);

        playerRepository.save(player);

    }
}*/
