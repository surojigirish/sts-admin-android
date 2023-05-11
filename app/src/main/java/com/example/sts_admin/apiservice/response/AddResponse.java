package com.example.sts_admin.apiservice.response;

import com.example.sts_admin.model.UserAdmin;
import com.google.gson.annotations.SerializedName;

public class AddResponse {

        private String message;
        private int status;
        private boolean success;
        @SerializedName("user-admin")
        private UserAdmin user_admin;

        public AddResponse(String message, int status, boolean success, UserAdmin user_admin) {
                this.message = message;
                this.status = status;
                this.success = success;
                this.user_admin = user_admin;
        }

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

        public UserAdmin getUser_admin() {
                return user_admin;
        }

        public void setUser_admin(UserAdmin user_admin) {
                this.user_admin = user_admin;
        }
}
