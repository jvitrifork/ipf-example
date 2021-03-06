package ch.qligier.ipf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;

/**
 * Main class of the IPF application.
 *
 * There are two entry point, depending on if the application is run as a JAR or a WAR.
 *
 * @author Quentin Ligier
 */
@SpringBootApplication
@Slf4j
public class IpfApplication extends SpringBootServletInitializer {

    /**
     * Entry point of the IPF application when running as a JAR (e.g. in IntelliJ IDEA).
     *
     * @param args The list of CLI parameters.
     */
    public static void main(final String[] args) {
        log.info("Configuring IPF app for a traditional JAR deployment");
        final SpringApplication application = new SpringApplication(IpfApplication.class);
        addApplicationStartupHook(application);
        application.run(args);
    }

    /**
     * Entry point of the IPF application when running as a WAR (e.g. in a Tomcat).
     *
     * @param builder The builder for {@link SpringApplication}.
     * @return The configured builder.
     */
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        log.info("Configuring IPF app for a traditional WAR deployment");
        addApplicationStartupHook(builder.application());
        return builder.sources(IpfApplication.class);
    }

    /**
     * Adds a hook to the Application Ready event to run some magic.
     *
     * @param application The IPF {@link SpringApplication} instance.
     */
    public static void addApplicationStartupHook(final SpringApplication application) {
        application.addListeners((ApplicationListener<ApplicationReadyEvent>) event -> {
            // Do some configuration magic...

            log.info("IPF app has been configured and has started");
        });
    }
}
