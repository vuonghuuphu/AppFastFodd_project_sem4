package huuphu.aprotrain.client_app.Model.Response;

import java.util.ArrayList;

public class RegisterResponse {
        private String createdAt;
        private String updatedAt;
        private String deletedAt = null;
        private String createdBy = null;
        private String updatedBy = null;
        private String deletedBy = null;
        private String id;
        private String userName;
        private String passwordHash;
        ArrayList < Roles > roles = new ArrayList< Roles >();
        private String status;


        // Getter Methods

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public String getDeletedBy() {
            return deletedBy;
        }

        public String getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public String getStatus() {
            return status;
        }

        // Setter Methods

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public void setDeletedBy(String deletedBy) {
            this.deletedBy = deletedBy;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }