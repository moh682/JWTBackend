
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author RasmusFriis
 */
@Entity
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restName, foodType, website, street, phone, pictureUrl;
        
    @ManyToOne(cascade = CascadeType.ALL)
    private CityInfo cityInfo;

    /**
     *
     */
    public Restaurant() {
    }
    
    /**
     *
     * @param restName
     * @param foodType
     * @param website
     * @param street
     * @param phone
     * @param cityInfo
     * @param pictureUrl 
     */
    public Restaurant(String restName, String foodType, String website, String street, String phone, CityInfo cityInfo, String pictureUrl) {
        this.restName = restName;
        this.foodType = foodType;
        this.website = website;
        this.street = street;
        this.phone = phone;
        this.cityInfo = cityInfo;
        this.pictureUrl = pictureUrl;
    }

    /**
     *
     * @param id
     * @param name
     * @param foodtype
     * @param website
     * @param address
     * @param phone
     * @param cityInfo
     */
    public Restaurant(Long id, String name, String foodtype, String website, String address, String phone, CityInfo cityInfo) {
        this.id = id;
        this.restName = name;
        this.foodType = foodtype;
        this.website = website;
        this.street = address;
        this.phone = phone;
        this.cityInfo = cityInfo;
    }
    
    

    /**
     *
     * @return
     */
    public String getName() {
        return restName;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.restName = name;
    }

    /**
     *
     * @return
     */
    public String getFoodtype() {
        return foodType;
    }

    /**
     *
     * @param foodtype
     */
    public void setFoodtype(String foodtype) {
        this.foodType = foodtype;
    }

    /**
     *
     * @return
     */
    public String getWebsite() {
        return website;
    }

    /**
     *
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return street;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.street = address;
    }

    /**
     *
     * @return
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    /**
     *
     * @return
     */
    public CityInfo getCityInfo() {
        return cityInfo;
    }

    /**
     *
     * @param cityInfo
     */
    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getPictureurl() {
        return pictureUrl;
    }

    /**
     *
     * @param pictureurl
     */
    public void setPictureurl(String pictureurl) {
        this.pictureUrl = pictureurl;
    }

    @Override
    public String toString() {
        return  "\n " + id +
                "\n"+ restName + 
                "\n" + foodType +
                "\n" + phone + 
                "\n" + street + 
                "\n" + website + 
                "\n" + cityInfo.getCity() +
                "\n" + cityInfo.getZip() + "\n";
    }
    
}
