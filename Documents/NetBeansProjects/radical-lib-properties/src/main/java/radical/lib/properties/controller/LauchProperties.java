/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radical.lib.properties.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import radical.lib.properties.service.RadicalProperties;

/**
 *
 * @author emmanuel.idoko
 */
@Service
public class LauchProperties implements Runnable {
private static final Logger logger = LoggerFactory.getLogger(LauchProperties.class);

    @Override
    public void run() {
        logger.info("thread class launcher running..");
        RadicalProperties.getInstance().load();
        logger.info("thread pool size value {} ",RadicalProperties.getInstance().getThreadpoolSizeTextQueue());
    }

}
