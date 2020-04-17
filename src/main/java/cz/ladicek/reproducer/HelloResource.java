package cz.ladicek.reproducer;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> get() {
        CompletableFuture<String> result = new CompletableFuture<>();
        result.completeExceptionally(new BadRequestException());
        return result;
    }
}
