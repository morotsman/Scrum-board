package org.github.morotsman.todo.model.membership;

import org.github.morotsman.todo.model.team.Team;
import org.github.morotsman.todo.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Membership {

    @Embeddable
    public static class Id implements Serializable {

        @Column(name  = "USER_ID")
        private Long userId;

        @Column(name = "TEAM_ID")
        private Long teamId;

        public Id(){}

        public Id(Long userId, Long teamId){
            this.userId = userId;
            this.teamId = teamId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (userId != null ? !userId.equals(id.userId) : id.userId != null) return false;
            if (teamId != null ? !teamId.equals(id.teamId) : id.teamId != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = userId != null ? userId.hashCode() : 0;
            result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
            return result;
        }

        public Long getUserId() {
            return userId;
        }


        public Long getTeamId() {
            return teamId;
        }


    }

    @EmbeddedId
    private Id id = new Id();


    @Column(name = "ADDED_ON")
    private Date dateAdded;

    @ManyToOne
    @JoinColumn(
            name = "USER_ID",
            insertable = false,
            updatable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "TEAM_ID",
            insertable = false,
            updatable = false
    )
    private Team team;

    public Membership(){}

    public Membership(User user, Team team) {
        this.user = user;
        this.team = team;

        this.id.userId = user.getId();
        this.id.teamId = team.getId();

        team.getMemberships().add(this);
        user.getMemberships().add(this);
    }


    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membership that = (Membership) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", dateAdded=" + dateAdded +
                ", user=" + user +
                ", team=" + team +
                '}';
    }
}
