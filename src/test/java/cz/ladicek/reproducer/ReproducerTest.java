package cz.ladicek.reproducer;

import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ReproducerTest {
    static MockTracer tracer = new MockTracer();

    static {
        GlobalTracer.register(tracer);
    }

    @Test
    public void hello() {
        when()
                .get("/client")
        .then()
                .statusCode(200)
                .body(is("Client got: Fallback"));

        List<MockSpan> spans = tracer.finishedSpans();
        assertEquals(3, spans.size());
        for (MockSpan span : spans) {
            assertEquals(spans.get(0).context().traceId(), span.context().traceId());
        }
    }
}
