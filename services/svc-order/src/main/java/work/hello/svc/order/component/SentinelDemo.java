package work.hello.svc.order.component;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SentinelDemo {

    @SentinelResource(value = "sentinelResourceDemo", blockHandler = "sentinelResourceBlockHandler")
    public void sentinelResourceDemo() {
        log.info("sentinelResourceDemo run.....");
        sphUDemo();
    }

    public void sentinelResourceBlockHandler(BlockException ex) {
        log.error("sentinelResourceBlockHandler run...", ex);
    }

    public void sphUDemo() {
        try (Entry entry = SphU.entry("sphUDemo")) {
            // 受保护的业务逻辑
            log.info("order_create run.....");

        } catch (BlockException ex) {
            // 被限流/熔断
            log.warn("order_create 被限流了");
        }

    }
}
