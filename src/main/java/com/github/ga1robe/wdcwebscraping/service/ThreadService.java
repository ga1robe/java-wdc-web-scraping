package com.github.ga1robe.wdcwebscraping.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
public class ThreadService implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ThreadService.class);

    private Boolean debug = true;

    @Autowired
    CardsService service;

    @Override
    public void run() {
        longBackgorund();
    }

    protected void longBackgorund() {
        boolean isSpinningRound = true;
        int waitMinutes = 15;
        while (isSpinningRound) {
            if (debug) {
                log.info("###### INFO: " + "Waiting around " + String.valueOf(waitMinutes) + " minutes to next reload data...");
            }
            try {
                Thread.sleep(waitMinutes*60*1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.service.getMainDataSPM();
            if (debug) {
                log.info("###### MainDataSPM: " + this.service.getRecords());
            }
            if(debug && this.service.getRecords().size() == 0){
                log.warn("###### WARNING: Size of MainDataSPM: " + this.service.getRecords().size());
                do {
                    try {
                        Thread.sleep( 1*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.warn("###### WARNING: MainDataSPM: " + this.service.getMainDataSPM());
                } while(this.service.getRecords().size() == 0);
            }
        }
    }
}
