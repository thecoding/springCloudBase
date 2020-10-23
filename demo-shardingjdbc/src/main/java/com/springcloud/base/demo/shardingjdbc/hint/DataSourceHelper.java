package com.springcloud.base.demo.shardingjdbc.hint;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataSourceHelper {


    static DataSource getDataSourceForMaster() throws IOException, SQLException {
        return YamlMasterSlaveDataSourceFactory.createDataSource(getFile("/hint/hint-master-only.yaml"));
    }

    private static File getFile(String configFile){
        return new File(Thread.currentThread().getClass().getResource(configFile).getFile());
    }
}
