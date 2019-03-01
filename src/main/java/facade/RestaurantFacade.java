package facade;

import DTO.RestaurantDTO;
import entity.CityInfo;
import entity.Restaurant;
import java.util.ArrayList;
import java.util.Collection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import static javax.ws.rs.client.Entity.json;

/*
* @author mohammahomarhariri
*/

/**
 *
 * @author RasmusFriis
 */

public class RestaurantFacade {


    private EntityManagerFactory emf;

    /**
     * Used when ever the RestaurantFacade is instanciated.
     *
     * @param emf EntityManagerFactory which specifies which database is used
     */
    public RestaurantFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return returns an EntityManager from emf.
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Used in rest.Resource.java and in utils.SetupTestUsers.java
     *
     * @param restaurant The restaurant that needs to be added to Database
     * @return
     */
    public RestaurantDTO addRestaurant(Restaurant restaurant) {

        EntityManager em = getEntityManager();
        Collection<Restaurant> list = new ArrayList<>();
        list.add(restaurant);

        CityInfo ci = em.find(CityInfo.class, restaurant.getCityInfo().getZip());

        if (ci != null) {
            //ci.setRestaurants(list);
            ci.addRestaurant(restaurant);
            try {

                em.getTransaction().begin();
                em.merge(ci);
                em.getTransaction().commit();

            } catch (Exception ex) {
                System.out.println(ex);
                em.close();
                return null;
            }
        } else {
            try {
                em.getTransaction().begin();
                em.persist(restaurant);
                em.getTransaction().commit();

            } catch (Exception ex) {
                System.out.println(ex);
                em.close();
                return null;
            }
        }
        
        return getRestaurantDTOByNameAndPhone(restaurant.getName(), restaurant.getPhone());
    }

    /**
     * Used in rest.Resource.java and utils.SetupTestUsers.java
     *
     * @return returns all restaurants from DataBase
     */
    public List<RestaurantDTO> getAllRestaurants() {

        EntityManager em = getEntityManager();
        List<RestaurantDTO> restaurants = null;

        try {
            restaurants = em.createQuery("SELECT NEW DTO.RestaurantDTO(p.id, p.restName, p.foodType, p.website, p.street, p.phone, p.cityInfo, p.pictureUrl) from Restaurant p", RestaurantDTO.class).getResultList();
//               public RestaurantDTO(Long id, String restName, String foodType, String website, String street, String phone, CityInfo cityInfo, String pictureUrl)
            return restaurants;

        } finally {
            em.close();
        }
    }

    /**
     *
     * @return
     */
    public String getOtherRestaurants() {

        String result = "Error";
        try {
            URL url = new URL("https://oloye.dk/api/info/getlist");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
//            con.setRequestProperty("User-Agent", "server");

            Scanner scan = new Scanner(con.getInputStream());
            String jsonStr = "";

            while (scan.hasNext()) {
                jsonStr += scan.nextLine();
            }
            scan.close();
            Charset.forName("UTF-8").encode(jsonStr);
            return jsonStr;

        } catch (Exception e) {
            result = "->Red<-" + e;
        }
        return "\"" + result + "\"";

    }

    /**
     *
     * @param restaurant
     * @return
     */
    public RestaurantDTO editRestaurant(Restaurant restaurant) {
        EntityManager em = getEntityManager();
        
        try {

            em.getTransaction().begin();
            em.merge(restaurant);
            em.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex);
            em.close();
        }

        return getRestaurantDTOById(restaurant.getId());
    }

    /**
     *
     * @param name
     * @param phone
     * @return
     */
    public RestaurantDTO getRestaurantDTOByNameAndPhone(String name, String phone) {
        
        EntityManager em = getEntityManager();
        RestaurantDTO restaurant = null;
        try {
            restaurant = em.createQuery("SELECT NEW DTO.RestaurantDTO(p.id, p.restName, p.foodType, p.website, p.street, p.phone, p.cityInfo, p.pictureUrl) from Restaurant p WHERE p.restName = :name AND p.phone = :phone", RestaurantDTO.class)
                    .setParameter("name", name)
                    .setParameter("phone", phone)
                    .getSingleResult();
            
        } catch (NoResultException ex) {
            System.out.println("No Result " + ex);
        }
        return restaurant;
    }

    /**
     *
     * @param id
     * @return
     */
    public RestaurantDTO getRestaurantDTOById(Long id) {
        EntityManager em = getEntityManager();
        RestaurantDTO restaurant = null;
        try {
            restaurant = em.createQuery("SELECT NEW DTO.RestaurantDTO(p.id, p.restName, p.foodType, p.website, p.street, p.phone, p.cityInfo, p.pictureUrl) from Restaurant p WHERE p.id = :id", RestaurantDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No Result " + ex);
        }
        return restaurant;
    }
}
