package meridian.api.session;

import java.util.UUID;
import meridian.api.packet.Packet;

/**
 * The client a headless connection stands in for: the proxy is connected to a server with no game
 * behind it, and plays the client's part through this.
 *
 * <p>The proxy opens the connection and does the handshake (connect and authentication); the rest
 * of what a client says - asking for assets, saying it is ready, answering pings and teleports - is
 * game behaviour, left to whoever handles the session's packets.
 */
public interface HeadlessClient {

    /** The name the client plays under. */
    String username();

    /** The account the client plays as. */
    UUID uuid();

    /**
     * Sends {@code packet} as the game would: through the client-to-server handler chain, where
     * Layer-1 trackers see it as the client's own, then to the server. Safe from any thread.
     */
    void sendAsClient(Packet packet);
}
