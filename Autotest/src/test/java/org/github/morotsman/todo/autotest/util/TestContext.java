package org.github.morotsman.todo.autotest.util;


import org.github.morotsman.todo.web.dto.MembershipDTO;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.github.morotsman.todo.web.dto.UserDTO;

public class TestContext {

    private final ContextAwareRestHelper<UserDTO> userHelperImpl = new ContextAwareRestHelperImpl<UserDTO>(new RestHelperImpl<UserDTO>(), UserDTO.class);
    private final ContextAwareRestHelper<TeamDTO> teamHelperImpl = new ContextAwareRestHelperImpl<TeamDTO>(new RestHelperImpl<TeamDTO>(),TeamDTO.class);
    private final ContextAwareRestHelper<MembershipDTO> membershipHelperImpl = new ContextAwareRestHelperImpl<MembershipDTO>(new RestHelperImpl<MembershipDTO>(),MembershipDTO.class);

    private static final TestContext instance = new TestContext();

    private TestContext(){}

    public static TestContext instance() {
         return instance;
    }

    public RestHelper<UserDTO> getUserHelperImpl() {
        return userHelperImpl;
    }

    public ContextAwareRestHelper<TeamDTO> getTeamHelperImpl() {
        return teamHelperImpl;
    }

    public ContextAwareRestHelper<MembershipDTO> getMembershipHelperImpl() {
        return membershipHelperImpl;
    }

    public void cleanUp(){
        membershipHelperImpl.cleanUp();
        userHelperImpl.cleanUp();
        teamHelperImpl.cleanUp();
    }

}
