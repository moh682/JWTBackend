package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author RasmusFriis
 */
@Entity
@Table(name = "users")
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @JoinTable(name = "user_roles", joinColumns =
    {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    })
    @ManyToMany
    private List<Role> roleList = new ArrayList();

    /**
     *
     * @return
     */
    public List<String> getRolesAsStrings()
    {
        if (roleList.isEmpty())
        {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roleList)
        {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    /**
     *
     */
    public User()
    {
    }

    //TODO Change when password is hashed

    /**
     *
     * @param pw
     * @return
     */
    public boolean verifyPassword(String pw)
    {
        //return (pw.equals(userPass));        

        if (BCrypt.checkpw(pw, this.userPass))
        {
            System.out.println("It matches");
            return true;
        } else
        {
            System.out.println("It does not match");
            return false;
        }
    }

    /**
     *
     * @param userName
     * @param userPass
     */
    public User(String userName, String userPass)
    {
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());

        // Check that an unencrypted password matches one that has
        // previously been hashed
    }

    /**
     *
     * @return
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getUserPass()
    {
        return this.userPass;
    }

    /**
     *
     * @param userPass
     */
    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    /**
     *
     * @return
     */
    public List<Role> getRoleList()
    {
        return roleList;
    }

    /**
     *
     * @param roleList
     */
    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    /**
     *
     * @param userRole
     */
    public void addRole(Role userRole)
    {
        roleList.add(userRole);
    }

}
