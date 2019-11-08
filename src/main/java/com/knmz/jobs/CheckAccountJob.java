package com.knmz.jobs;

import com.knmz.service.UserService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by reese on 2019-10-12.
 */
@DisallowConcurrentExecution
public class CheckAccountJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        String phoneOrEmail = "+8615018513737";
        boolean success = userService.checkAccount(phoneOrEmail);
        if (success) {
            logger.info("checkAccount success");
        } else {
            logger.info("checkAccount success");
        }

    }
}
