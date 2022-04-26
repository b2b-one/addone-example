package b2b.one.addonebackend.models;

public class PostRequest {
    String token;
    String oneBoUrl;
    String tenantKey;
    String pluginId;

    public String getToken() {
        return this.token;
    }

    public String getOneBoUrl() {
        return this.oneBoUrl;
    }

    public String getTenantKey() {
        return this.tenantKey;
    }

    public String getPluginId() {
        return this.pluginId;
    }
}