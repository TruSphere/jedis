package redis.clients.jedis;

import java.io.Serializable;

public class HostAndPort implements Serializable {
private static final long serialVersionUID = -519876229978427751L;

public static final String LOCALHOST_STR = "localhost";

  private String host;
  private int port;
  private boolean ssl;

  public HostAndPort(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public HostAndPort(String host, int port, boolean ssl) {
    this.host = host;
    this.port = port;
    this.ssl = ssl;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public boolean isSsl() {
    return ssl;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof HostAndPort) {
      HostAndPort hp = (HostAndPort) obj;

      String thisHost = convertHost(host);
      String hpHost = convertHost(hp.host);
      return port == hp.port && thisHost.equals(hpHost) && ssl == hp.ssl;
    }

    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((host == null) ? 0 : host.hashCode());
    result = prime * result + port;
    result = prime * result + (ssl ? 1231 : 1237);
    return result;
  }

  @Override
  public String toString() {
    if (!ssl) {
      return host + ":" + port;
    } else {
      return "rediss://" + host + ":" + port;
    }
  }

  private String convertHost(String host) {
    if (host.equals("127.0.0.1")) return LOCALHOST_STR;
    else if (host.equals("::1")) return LOCALHOST_STR;

    return host;
  }
}
