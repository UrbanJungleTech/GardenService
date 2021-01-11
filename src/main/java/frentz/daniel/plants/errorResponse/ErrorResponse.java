package frentz.daniel.plants.errorResponse;

public class ErrorResponse {
    private long statusCode;
    private String entityType;
    private long entityId;

    public ErrorResponse(long statusCode, String entityType, long entityId){
        this.statusCode = statusCode;
        this.entityType = entityType;
        this.entityId = entityId;
    }
    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
