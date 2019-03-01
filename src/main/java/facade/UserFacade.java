package facade;

import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import exceptions.AuthenticationException;

/**
 *
 * @author RasmusFriis
 */
public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserFacade instance = new UserFacade();
    
    private UserFacade(){}
    
    /**
     * 
     * when ever this method is called, the same instance is returned.
     * 
     * @return the instance of this class
     */
    public static UserFacade getInstance(){
        return instance;
    }
    
    /**
     * 
     * 
     * @param username Username of Client
     * @param password Password of Client
     * @return returns The User if user does exist and the password is equal the input password.
     * 
     * @throws AuthenticationException if User does not exist or Password is incorrect.
     */
    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

}
