package org.github.morotsman.todo.autotest.util;


import org.github.morotsman.todo.web.dto.*;

public class TestContext {

    private final HateoasAwareRestHelper<UserDTO> userHelperImpl = new HateoasAwareRestHelperImpl<UserDTO>(new RestHelperImpl<UserDTO>(), UserDTO.class);
    private final HateoasAwareRestHelper<TeamDTO> teamHelperImpl = new HateoasAwareRestHelperImpl<TeamDTO>(new RestHelperImpl<TeamDTO>(),TeamDTO.class);
    private final HateoasAwareRestHelper<MembershipDTO> membershipHelperImpl = new HateoasAwareRestHelperImpl<MembershipDTO>(new RestHelperImpl<MembershipDTO>(),MembershipDTO.class);
    private final HateoasAwareRestHelper<SprintDTO> sprintHelperImpl = new HateoasAwareRestHelperImpl<SprintDTO>(new RestHelperImpl<SprintDTO>(),SprintDTO.class);
    private final HateoasAwareRestHelper<StoryDTO> storyHelperImpl = new HateoasAwareRestHelperImpl<StoryDTO>(new RestHelperImpl<StoryDTO>(),StoryDTO.class);

    private static final TestContext instance = new TestContext();

    private TestContext(){}

    public static TestContext instance() {
         return instance;
    }

    public RestHelper<UserDTO> getUserHelperImpl() {
        return userHelperImpl;
    }

    public HateoasAwareRestHelper<TeamDTO> getTeamHelperImpl() {
        return teamHelperImpl;
    }

    public HateoasAwareRestHelper<MembershipDTO> getMembershipHelperImpl() {
        return membershipHelperImpl;
    }

    public HateoasAwareRestHelper<SprintDTO> getSprintHelperImpl() {
        return sprintHelperImpl;
    }

    public HateoasAwareRestHelper<StoryDTO> getStoryHelperImpl() {
        return storyHelperImpl;
    }

    public void cleanUp(){
        membershipHelperImpl.cleanUp();
        userHelperImpl.cleanUp();
        storyHelperImpl.cleanUp();
        sprintHelperImpl.cleanUp();
        teamHelperImpl.cleanUp();
    }

}
