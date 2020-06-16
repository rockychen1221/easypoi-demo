package com.littlefox.easypoi.listener;

import cn.afterturn.easypoi.cache.manager.POICacheManager;
import com.littlefox.easypoi.config.MyFileLoaderImpl;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author rockychen
 * @version 1.0
 * @date 2020-06-16 23:23
 */
@Component
public class ExcelListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        POICacheManager.setFileLoader(new MyFileLoaderImpl());
    }
}
