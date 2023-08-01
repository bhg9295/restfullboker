package com.restful.booker.model;

public class PatchPoJo {
    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public static class PatchPojo {
        public void setFirstname(String name) {
        }

        public void setLastname(String patel) {
        }
    }
}
