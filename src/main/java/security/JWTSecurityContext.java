
package security;
import java.security.Principal;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author RasmusFriis
 */
public class JWTSecurityContext implements SecurityContext {
   UserPrincipal user;
   ContainerRequestContext request;

    /**
     *
     * @param user
     * @param request
     */
    public JWTSecurityContext(UserPrincipal user,ContainerRequestContext request) {
       this.user = user;
       this.request = request;
   }

    /**
     *
     * @param role
     * @return
     */
    @Override
   public boolean isUserInRole(String role) {
       return user.isUserInRole(role);
   }

    /**
     *
     * @return
     */
    @Override
   public boolean isSecure() {
       return request.getUriInfo().getBaseUri().getScheme().equals("https");
   }

    /**
     *
     * @return
     */
    @Override
   public Principal getUserPrincipal() {
       return user;
   }

    /**
     *
     * @return
     */
    @Override
   public String getAuthenticationScheme() {
       return "JWT"; //Only for INFO
   }
}

