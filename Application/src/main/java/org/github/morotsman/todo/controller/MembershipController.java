package org.github.morotsman.todo.controller;

import org.github.morotsman.todo.model.membership.Membership;
import org.github.morotsman.todo.service.MembershipService;
import org.github.morotsman.todo.service.exceptions.ResourceExistException;
import org.github.morotsman.todo.service.exceptions.ResourceNotFoundException;
import org.github.morotsman.todo.web.dto.MembershipDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/v1")
public class MembershipController {

    private MembershipService membershipService;




    @RequestMapping(value="/team/{teamName}/membership/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public MembershipDTO getMembership(@PathVariable String teamName, @PathVariable String userName){
        return toMembershipDTO(membershipService.findMembership(userName,teamName),teamName,userName);
    }

    private MembershipDTO toMembershipDTO(Membership membership, String teamName, String userName){
        MembershipDTO result = new MembershipDTO();
        result.setDateAdded(membership.getDateAdded());
        result.add(linkTo(methodOn(MembershipController.class).getMembership(teamName,userName)).withSelfRel());
        result.add(linkTo(methodOn(TeamController.class).getTeam(teamName)).withRel("team"));
        result.add(linkTo(methodOn(UserController.class).getUser(userName)).withRel("user"));
        return result;
    }

    @RequestMapping(value="/team/{teamName}/membership/{userName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MembershipDTO createMembership(@PathVariable String teamName, @PathVariable String userName){
        Membership membership = membershipService.createMembership(userName,teamName);
        return toMembershipDTO(membership,teamName,userName);
    }

    @RequestMapping(value="/team/{teamName}/membership/{userName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMembership(@PathVariable String teamName, @PathVariable String userName){
        membershipService.deleteMembership(userName,teamName);
    }

    @ExceptionHandler(ResourceExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    void handleResourceAlreadyExistsException(Exception exception) {
        //exception.printStackTrace(System.out);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    void handleServiceException(Exception exception) {
        exception.printStackTrace(System.out);
    }

    @ExceptionHandler({ResourceNotFoundException.class,ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    void handleResourceNotFoundException(Exception exception) {
        //exception.printStackTrace(System.out);
    }

    @Resource
    public void setMembershipService(MembershipService membershipService) {
        this.membershipService = membershipService;
    }
}
