package meridian.api.command;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * A command a module answers on the proxy's command channel, for one connection.
 *
 * <p>The channel line is {@code <name> <conn> <args...> [json]}: the command's name, the number of
 * the connection it is for (from {@code sessions}), then whatever the command takes. Each
 * connection runs its own module runtime, so the command reaches the module instance serving that
 * connection - core's services there are that connection's world, player and packets.
 *
 * <p>The answer is whatever the future completes with, written as JSON (a map, a record, a list).
 * A failed future is answered as an error. Run anything slow off the calling thread; completing
 * when the work is really done - a forged chain played out - is what lets a test go on from there.
 */
@FunctionalInterface
public interface ControlCommand {

    /**
     * @param args the words after the connection number
     * @param json a trailing JSON object on the line, or {@code null}
     */
    CompletableFuture<?> run(List<String> args, String json) throws Exception;
}
