package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.results.ResultReport;
import com.google.gson.annotations.SerializedName;

public class ReportGenerationResponse {
    @SerializedName("result") private ResultReport result;
    @SerializedName("status") private Integer status;
    @SerializedName("success") private Boolean success;

    public ResultReport getResult() {
        return result;
    }

    public void setResult(ResultReport result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
