/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radical.lib.properties.service;

import com.easycoop.radical.database.layer.entity.Bank;
import com.easycoop.radical.database.layer.entity.PropertyEnvironment;
import com.easycoop.radical.database.layer.query.DaoServiceQuery;
import com.easycoop.radical.database.layer.query.UserManagementServiceQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author emmanuel.idoko
 */
@Service
public class RadicalProperties extends Properties {

    private static final Logger logger = LoggerFactory.getLogger(RadicalProperties.class);
    //private @Autowired UserManagementServiceQuery userManagementService;
    UserManagementServiceQuery userManagementService;

    private InputStream instream;
    private static RadicalProperties INSTANCE;
    private final String threadpool_size_information_queue = "threadpool.size.information.queue";
    private final String threadpool_size_rejecter_queue = "threadpool.size.rejecter.queue";
    private final String threadpool_size_email_queue = "threadpool.size.email.queue";
    private final String threadpool_size_text_queue = "threadpool.size.text.queue";
    private final String threadpool_size_authoriser_queue = "threadpool.size.authoriser.queue";

    public RadicalProperties() {
    }

    public static RadicalProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RadicalProperties();
        }
        return INSTANCE;
    }

    /**
     * Loads a configuration file from the disk
     */
    public final void load() {
        try {
            logger.info("Loading configuration file");

            boolean exists = false;
            File propFile = new File("C:\\etc\\index\\threadpool_notifiers.properties");
            //if property file does not exist in designated location, create file using default file within system classpath
            if (!propFile.exists()) {
                reload();
            } else {
                exists = true;
            }

            if (exists) {
                FileInputStream loadstream = new FileInputStream(propFile);
                load(loadstream);
                loadstream.close();

                //ensure that all property keys have not been tampered with
                if (userManagementService == null) {
                    logger.info("userManagementService is null..");
                } else {
                    logger.info("userManagementService is not null..");
                }
                List<PropertyEnvironment> all = new ArrayList<>();
                logger.info("records returned from database...{}", all.size());
                //all = userManagementService.getAllProperties();
                for (PropertyEnvironment s : all) {
                    boolean found = false;
                    for (Map.Entry pairs : entrySet()) {
                        String key = (String) pairs.getKey();
                        String value = (String) pairs.getValue();
                        if (s.getPropertyName().equals(key) && s.getPropertyValue().equals(value)) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        reload();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            logger.info("failed to load configuration file - see error log");
            logger.error("error loading email config file:", ex);
        }
    }

    /**
     * Reloads a configuration file, getting all necessary variables from the
     * database.
     */
    public final void reload() {
        try {
            logger.info("Reloading configuration file");

            File propFile = new File("C:\\etc\\index\\threadpool_notifiers.properties");
            propFile.delete();
            //if property file does not exist in designated location, create file using default file within system classpath
            if (!propFile.exists()) {
                propFile.getParentFile().mkdirs();

                instream = RadicalProperties.class.getClassLoader().getResourceAsStream("threadpool_notifiers.properties");
                FileOutputStream outstream = new FileOutputStream(propFile);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = instream.read(buffer)) > 0) {
                    outstream.write(buffer, 0, length);
                }

                instream.close();
                outstream.close();
            }

            FileInputStream loadstream = new FileInputStream(propFile);
            load(loadstream);
            loadstream.close();

            FileOutputStream changestream = new FileOutputStream(propFile);

//            setProperty(threadpool_size_information_queue, userManagementService.getProperty(threadpool_size_information_queue).getPropertyValue());
//            setProperty(threadpool_size_rejecter_queue, userManagementService.getProperty(threadpool_size_rejecter_queue).getPropertyValue());
//            setProperty(threadpool_size_email_queue, userManagementService.getProperty(threadpool_size_email_queue).getPropertyValue());
//            setProperty(threadpool_size_text_queue, userManagementService.getProperty(threadpool_size_text_queue).getPropertyValue());
//            setProperty(threadpool_size_authoriser_queue, userManagementService.getProperty(threadpool_size_authoriser_queue).getPropertyValue());

            store(changestream, null);
            changestream.close();
        } catch (Exception ex) {
            logger.info("failed to reload configuration file - see error log");
            logger.error("error reloading email config file:", ex);
        }
    }

    public String getThreadpoolSizeInformationQueue() {
        return getProperty(threadpool_size_information_queue);
    }

    public String getThreadpoolSizeRejecterQueue() {
        return getProperty(threadpool_size_rejecter_queue);
    }

    public String getThreadpoolSizeEmailQueue() {
        return getProperty(threadpool_size_email_queue);
    }

    public String getThreadpoolSizeTextQueue() {
        return getProperty(threadpool_size_text_queue);
    }

    public String getThreadpoolSizeAuthoriserQueue() {
        return getProperty(threadpool_size_authoriser_queue);
    }

}
