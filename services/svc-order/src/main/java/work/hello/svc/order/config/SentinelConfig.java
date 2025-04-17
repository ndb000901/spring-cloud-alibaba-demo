package work.hello.svc.order.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

@Configuration
public class SentinelConfig {

    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return new BlockExceptionHandler() {

            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, String s, BlockException e) throws IOException {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                String msg = "请求被限流了";
                if (e instanceof FlowException) {
                    msg = "请求被限流了";
                } else if (e instanceof DegradeException) {
                    msg = "服务被降级了";
                } else if (e instanceof ParamFlowException) {
                    msg = "热点参数限流";
                } else if (e instanceof SystemBlockException) {
                    msg = "系统保护规则触发";
                } else if (e instanceof AuthorityException) {
                    msg = "授权规则不通过";
                }

                String json = new ObjectMapper().writeValueAsString(Map.of(
                    "code", 429,
                    "message", msg
                ));
                response.getWriter().write(json);
            }
        };
    }
}
