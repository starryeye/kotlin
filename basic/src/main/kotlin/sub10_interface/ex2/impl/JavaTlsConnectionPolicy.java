package sub10_interface.ex2.impl;

import sub10_interface.ex2.JavaConnectionPolicy;

public class JavaTlsConnectionPolicy implements JavaConnectionPolicy {

    private final String host;
    private final int port;
    private long timeoutMs;

    public JavaTlsConnectionPolicy(String host, int port, long timeoutMs) {
        this.host = host;
        this.port = port;
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public long getTimeoutMs() {
        return timeoutMs;
    }

    @Override
    public void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String getEndpoint() {
        return "https://" + host + ":" + port;
    }
}
