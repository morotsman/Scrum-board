package org.github.morotsman.todo.web.dto;


import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class MembershipsDTO extends ResourceSupport{

    private List<MembershipDTO> memberships = new ArrayList<MembershipDTO>();

    public List<MembershipDTO> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MembershipDTO> memberships) {
        this.memberships = memberships;
    }
}
