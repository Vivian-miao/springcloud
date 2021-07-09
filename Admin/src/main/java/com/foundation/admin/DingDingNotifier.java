package com.foundation.admin;

import com.alibaba.fastjson.JSONObject;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 2021-07-02 17:07:48
 *
 * @author zhh
 */
public class DingDingNotifier extends AbstractStatusChangeNotifier {
    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        String str = "系统警告 : 【" + serviceName + "】" +
                "【服务地址】" + serviceUrl +
                "【状态】" + status +
                "【详情】" + JSONObject.toJSONString(details);
        return Mono.fromRunnable(() -> DingDingMessageUtil.sendTextMessage(str));
    }
}
