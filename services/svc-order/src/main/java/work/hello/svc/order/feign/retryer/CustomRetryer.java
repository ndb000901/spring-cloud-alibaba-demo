package work.hello.svc.order.feign.retryer;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.stereotype.Component;

@Component
public class CustomRetryer implements Retryer {

    private int attempt = 1;
    private final int maxAttempts = 3;
    private final long waitTime = 200;

    @Override
    public void continueOrPropagate(RetryableException e) {
        System.out.println("CustomRetryer............");
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer();
    }
}
