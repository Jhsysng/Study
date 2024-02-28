package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CommandLineBean {

    private final ApplicationArguments arguments;

    public CommandLineBean(ApplicationArguments arguments){
        this.arguments = arguments;
        log.info("ApplicationArguments: {}", arguments.getOptionNames());
    }

    @PostConstruct
    public void init() {
        log.info("Source {}", List.of(arguments.getSourceArgs()));
        log.info("ApplicationArguments: {}", arguments.getOptionNames());
        Set<String> optionNames = arguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("Option: {} = {}", optionName, arguments.getOptionValues(optionName));
        }
    }
}
