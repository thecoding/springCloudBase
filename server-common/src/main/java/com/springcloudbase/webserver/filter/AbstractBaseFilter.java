package com.springcloudbase.webserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mirko on 2020/4/11.
 */
public abstract class AbstractBaseFilter implements BaseFilter{

    private static Logger logger = LoggerFactory.getLogger(AbstractBaseFilter.class);

    @Override
    public boolean preExecute() {
        logger.info(" preExecute start ");
        return true;
    }


    @Override
    public void afterExecute() {
        logger.info(" postExecute start ");
    }



    @Override
    public int compareTo(BaseFilter baseFilter) {
        return this.order() > baseFilter.order() ? -1 : 1;
    }
}
