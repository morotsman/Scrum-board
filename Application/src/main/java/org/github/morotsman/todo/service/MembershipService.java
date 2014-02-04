package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.user.User;

import java.util.List;

public interface MembershipService {

    public Membership createMembership(String userName, String teamName);

    public void deleteMembership(String userName, String teamName);

    public Membership findMembership(String userName, String teamName);

    public List<Membership> listMemberships(String teamName);

}
