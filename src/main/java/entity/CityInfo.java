
package entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author RasmusFriis
 */
@Entity
public class CityInfo implements Serializable {


    @Id
    private String zip;
    private String city;
    
    @OneToMany(mappedBy = "cityInfo")
    private Collection<Restaurant> restaurants = new ArrayList<>();

    /**
     *
     */
    public CityInfo() {
    }

    /**
     *
     * @param zipCode
     * @param city
     */
    public CityInfo(String zipCode, String city) {
        this.zip = zipCode;
        this.city = city;
    }

    /**
     *
     * @return
     */
    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     *
     * @param restaurants
     */
    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     *
     * @param restaurant
     */
    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }
    
    
    /**
     *
     * @return
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "zipCode=" + zip + ", city=" + city + ", restaurants=" + restaurants + '}';
    }
    
}
