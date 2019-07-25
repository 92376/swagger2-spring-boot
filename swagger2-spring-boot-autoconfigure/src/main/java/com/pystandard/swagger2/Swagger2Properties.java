package com.pystandard.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置
 *
 * @author wujing
 * @since 2019/7/23 12:38
 */
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Properties {

    /**
     * 是否开启swagger2
     */
    private boolean enable = false;

    /**
     * Api标题
     */
    private String title = "Api";

    /**
     * Api版本
     */
    private String version = "1.0.0";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
