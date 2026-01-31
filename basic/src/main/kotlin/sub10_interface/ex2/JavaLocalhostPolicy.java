package sub10_interface.ex2;

public class JavaLocalhostPolicy implements JavaConnectionPolicy{

    private long timeoutMs;

    public JavaLocalhostPolicy(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String getHost() {
        return "localhost";
    }

    @Override
    public int getPort() {
        return 8080;
    }

    @Override
    public long getTimeoutMs() {
        return timeoutMs;
    }

    @Override
    public void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }
}
