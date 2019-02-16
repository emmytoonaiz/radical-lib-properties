/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radical.lib.properties.config;

import com.easycoop.radical.database.layer.query.UserManagementServiceQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import radical.lib.properties.controller.LauchProperties;

/**
 *
 * @author emmanuel.idoko
 */
@Configuration
public class BeansConfig {

    @Bean
    public SimpleAsyncTaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


    @Bean
    public CommandLineRunner schedulingRunner(SimpleAsyncTaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(new LauchProperties());
            }
        };
    }

}
