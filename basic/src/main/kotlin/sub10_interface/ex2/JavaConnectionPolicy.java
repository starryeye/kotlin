package sub10_interface.ex2;

public interface JavaConnectionPolicy {

    String getHost();

    int getPort();

    long getTimeoutMs();
    void setTimeoutMs(long timeoutMs);

    default String getEndpoint() {
        return getHost() + ":" + getPort();
    }
}
