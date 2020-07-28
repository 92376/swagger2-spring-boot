package com.pystandard.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置
 *
 * @author wujing
 * @since 2019/7/23 12:38
 */
@ConfigurationProperties(prefix = "springfox.documentation")
public class Swagger2Properties {

    /**
     * 是否开启swagger2
     */
    private boolean enabled = true;

    /**
     * Api标题
     */
    private String title = "Api";

    /**
     * Api版本
     */
    private String version = "1.0.0";

    public boolean isEnable() {
        return enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
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
