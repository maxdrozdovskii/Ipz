package railways.server.mail_sender;

import org.springframework.stereotype.Component;
import railways.database.model.UserJourney;
import railways.server.ApplicationProperties;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class MessageQueue {

    private BlockingQueue<UserJourney> blockingQueue;
    private ThreadPoolExecutor threadPoolExecutor;

    public MessageQueue() {
        int poolSize = ApplicationProperties.getPoolSize();
        BlockingQueue<Runnable> blockingRunnableQueue = new ArrayBlockingQueue<>(poolSize);

        threadPoolExecutor = new ThreadPoolExecutor(
                poolSize,
                poolSize,
                ApplicationProperties.getTimeToKeepAlive(),
                TimeUnit.SECONDS,
                blockingRunnableQueue
        );

        blockingQueue = new ArrayBlockingQueue<>(poolSize);
    }

    public void add(UserJourney userJourney) {
        threadPoolExecutor.execute(() -> {
            try {
                blockingQueue.put(userJourney);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public UserJourney get() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
