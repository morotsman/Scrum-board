package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class MembershipDTO extends ResourceSupport {

    private String teamName;
    private String userName;

    public MembershipDTO(String teamName, String userName) {
        this.teamName = teamName;
        this.userName = userName;
    }

    public MembershipDTO() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
