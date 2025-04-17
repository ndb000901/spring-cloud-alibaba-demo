package work.hello.svc.order.feign.interceptor;

import feign.InvocationContext;
import feign.ResponseInterceptor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TagInterceptor implements ResponseInterceptor {

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {

        Collection<String> strings = invocationContext.response().headers().get("x-tag");

        if (strings != null) {
            System.out.println("tag: " + strings);
        }

        return chain.next(invocationContext);
    }
}
