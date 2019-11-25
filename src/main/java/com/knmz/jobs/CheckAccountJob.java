package com.knmz.jobs;

import com.knmz.service.UserService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务
 *
 * @author zl
 * @date 2019/9/27 11:02
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
        /*String account = "12345678";
        String nick = "world";
        boolean success1 = userService.updateNick(account, nick);
        List<String> accountList = new ArrayList<>();
        accountList.add("123456789");
        String nick1 = "hello";
        boolean success2 = userService.batchUpdateNick(accountList, nick1);*/
        if (success) {
            logger.info("checkAccount success. {}", phoneOrEmail);
        } else {
            logger.error("checkAccount fail. {}", phoneOrEmail);
        }

    }
}
