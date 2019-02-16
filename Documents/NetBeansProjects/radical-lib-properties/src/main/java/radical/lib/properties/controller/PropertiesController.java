/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radical.lib.properties.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author emmanuel.idoko
 */
@RestController
@RequestMapping("/api/radicalmonitor/v1/email")
@Api(value = "radical-monitor email alert controller API calls")
public class PropertiesController {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesController.class);

}
