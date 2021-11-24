package com.example.apiserver.DTO;

public class StatusDTO {
    private Long id;
    private boolean status;
    private boolean status2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus2() {
        return status2;
    }

    public void setStatus2(boolean status2) {
        this.status2 = status2;
    }

    public StatusDTO(Long id, boolean status, boolean status2) {
        this.id = id;
        this.status = status;
        this.status2 = status2;
    }
    public StatusDTO() {}

}
