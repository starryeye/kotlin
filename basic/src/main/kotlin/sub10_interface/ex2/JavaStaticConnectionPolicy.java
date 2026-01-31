package sub10_interface.ex2;

public class JavaStaticConnectionPolicy implements JavaConnectionPolicy {

    private final String host;
    private final int port;
    private long timeoutMs;

    public JavaStaticConnectionPolicy(String host, int port, long timeoutMs) {
        this.host = host;
        this.port = port;
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public long getTimeoutMs() {
        return this.timeoutMs;
    }

    @Override
    public void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }
}
