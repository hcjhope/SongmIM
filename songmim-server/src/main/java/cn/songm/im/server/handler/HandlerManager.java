/*
 * Copyright [2016] [zhangsong <songm.cn>].
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package cn.songm.im.server.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 事件操作管理器
 * 
 * @author zhangsong
 *
 */
@Component
public class HandlerManager implements ApplicationContextAware {

    private ApplicationContext context;

    private Map<Integer, Handler> ops = new HashMap<Integer, Handler>();

    @Bean(name = "handlers")
    public Map<Integer, Handler> operations() {
        Map<String, Handler> beans = context.getBeansOfType(Handler.class);
        for (Handler er : beans.values()) {
            ops.put(er.operation(), er);
        }
        return ops;
    }

    public Handler find(Integer op) {
        return ops.get(op);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }

}
