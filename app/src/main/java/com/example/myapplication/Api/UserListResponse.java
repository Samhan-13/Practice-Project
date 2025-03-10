package com.example.myapplication.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListResponse {

    @SerializedName("users")
    private List<User>userList;

    public List<User> getUser() {
        return userList;
    }

    public void setUser(List<User> user) {
        this.userList = user;
    }

    public static class User{
        @SerializedName("id")
        private long id;

        @SerializedName("firstName")
        private String name;
        @SerializedName("phone")
        private String phoneNumber;

        @SerializedName("company")
        private Company company;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public static class Company{
            @SerializedName("title")
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

}
