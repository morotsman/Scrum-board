package org.github.morotsman.todo.service;


import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.model.membership.MembershipRepository;
import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.user.User;
import org.github.morotsman.todo.model.user.UserRepository;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    private MembershipRepository membershipRepository;
    private UserService userService;
    private TeamService teamService;

    @Transactional
    @Override
    public Membership createMembership(String userName, String teamName) {
        User user  = userService.getUser(userName);
        Team team = teamService.getTeam(teamName);
        Membership membership = new Membership(user,team);
        membership.setDateAdded(new Date());
        return membershipRepository.makePersistent(membership);
    }

    @Transactional
    @Override
    public void deleteMembership(String userName, String teamName) {
        membershipRepository.makeTransient(find(userName,teamName));
    }

    private Membership find(String userName, String teamName){
        User user  = userService.getUser(userName);
        Team team = teamService.getTeam(teamName);
        return membershipRepository.findById(new Membership.Id(user.getId(),team.getId()),false);
    }

    @Transactional
    @Override
    public Membership findMembership(String userName, String teamName) {
        return find(userName,teamName);
    }

    @Transactional
    @Override
    public List<Membership> listMemberships(String teamName) {
        return getMemberships(teamName);
    }

    private List<Membership> getMemberships(String teamName) {
        Membership example = new Membership();
        Team team = teamService.getTeam(teamName);
        example.setTeam(team);
        return membershipRepository.findByExample(example);
    }

    @Resource
    public void setMembershipRepository(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
