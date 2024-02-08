package playground.virtualthreads;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ThreadsExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var url_google = "http://www.google.com";
        var url_bing = "http://www.bing.com";

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var futureGoogle = executor.submit(() -> fetchURL(url_google));
            var futureBing = executor.submit(() -> fetchURL(url_bing));
            String responseGoogle = futureGoogle.get();
            String responseBing = futureBing.get();

            System.out.println(STR."Got \{responseGoogle.getBytes(StandardCharsets.UTF_8).length} bytes from google");
            System.out.println(STR."Got \{responseBing.getBytes(StandardCharsets.UTF_8).length} bytes from bing");
        } catch (ExecutionException | InterruptedException e) {
            // TODO
            throw e;
        }
    }

    static String fetchURL(String adress) throws IOException {
        URL url = new URL(adress);
        try (var in = url.openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}

