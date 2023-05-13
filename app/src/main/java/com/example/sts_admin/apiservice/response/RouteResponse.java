package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.Route;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteResponse {

        @SerializedName("result")
        private List<Route> result;
        @SerializedName("status")
        private Integer status;
        @SerializedName("success")
        private Boolean success;
        @SerializedName("message")
        private String message;

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public boolean isSuccess() {
                return success;
        }

        public void setSuccess(boolean success) {
                this.success = success;
        }

        public List<Route> getResult() {
                return result;
        }

        public void setResult(List<Route> result) {
                this.result = result;
        }
}
