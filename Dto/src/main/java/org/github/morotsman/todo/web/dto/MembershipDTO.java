package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class MembershipDTO extends ResourceSupport {

    private Date dateAdded;

    public MembershipDTO(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public MembershipDTO() {
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
