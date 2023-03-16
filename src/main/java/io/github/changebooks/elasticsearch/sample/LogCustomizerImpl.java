package io.github.changebooks.elasticsearch.sample;

import io.github.changebooks.elasticsearch.EsLogCustomizer;
import org.springframework.stereotype.Component;

/**
 * 拦截ES请求和ES响应，打日志
 *
 * @author changebooks@qq.com
 */
@Component
public class LogCustomizerImpl extends EsLogCustomizer {
}
