
package DTO;

import entity.CityInfo;
import entity.Restaurant;
import java.util.Objects;

/**
 *
 * @author RasmusFriis
 */
public class RestaurantDTO {
    
    /**
     *
     */
    public Long id;

    /**
     *
     */
    public String restName,

    /**
     *
     */
    foodType,

    /**
     *
     */
    website,

    /**
     *
     */
    street,

    /**
     *
     */
    phone,

    /**
     *
     */
    pictureUrl;

    /**
     *
     */
    public CityInfoDTO cityInfo;

    /**
     *
     */
    public RestaurantDTO()
    {
    }
    
    /**
     *
     * @param id
     * @param restName
     * @param foodType
     * @param website
     * @param street
     * @param phone
     * @param cityInfo
     * @param pictureUrl 
     */
    public RestaurantDTO(Long id, String restName, String foodType, String website, String street, String phone, CityInfo cityInfo, String pictureUrl) {
        
        this.id = id;
        this.restName = restName;
        this.foodType = foodType;
        this.website = website;
        this.street = street;
        this.phone = phone;
        this.cityInfo = (new CityInfoDTO(cityInfo));
        this.pictureUrl = pictureUrl;
        
    }
    
    /**
     *
     * @param restaurant
     */
    public RestaurantDTO(Restaurant restaurant) {
        
        this.id = restaurant.getId();
        this.restName = restaurant.getName();
        this.foodType = restaurant.getFoodtype();
        this.website = restaurant.getWebsite();
        this.street = restaurant.getAddress();
        this.phone = restaurant.getPhone();
        this.cityInfo = new CityInfoDTO(restaurant.getCityInfo());
        this.pictureUrl = restaurant.getPictureurl();
    }

    /**
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getRestName()
    {
        return restName;
    }

    /**
     *
     * @param restName
     */
    public void setRestName(String restName)
    {
        this.restName = restName;
    }

    /**
     *
     * @return
     */
    public String getFoodType()
    {
        return foodType;
    }

    /**
     *
     * @param foodType
     */
    public void setFoodType(String foodType)
    {
        this.foodType = foodType;
    }

    /**
     *
     * @return
     */
    public String getWebsite()
    {
        return website;
    }

    /**
     *
     * @param website
     */
    public void setWebsite(String website)
    {
        this.website = website;
    }

    /**
     *
     * @return
     */
    public String getStreet()
    {
        return street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street)
    {
        this.street = street;
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
    public String getPictureUrl()
    {
        return pictureUrl;
    }

    /**
     *
     * @param pictureUrl
     */
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    /**
     *
     * @return
     */
    public CityInfoDTO getCityInfo()
    {
        return cityInfo;
    }

    /**
     *
     * @param cityInfo
     */
    public void setCityInfo(CityInfoDTO cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    @Override
    public String toString()
    {
        return "RestaurantDTO{" + "id=" + id + ", restName=" + restName + ", foodType=" + foodType + ", website=" + website + ", street=" + street + ", phone=" + phone + ", pictureUrl=" + pictureUrl + ", cityInfo=" + cityInfo + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final RestaurantDTO other = (RestaurantDTO) obj;
        if (!Objects.equals(this.restName, other.restName))
        {
            return false;
        }
        if (!Objects.equals(this.foodType, other.foodType))
        {
            return false;
        }
        if (!Objects.equals(this.website, other.website))
        {
            return false;
        }
        if (!Objects.equals(this.street, other.street))
        {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone))
        {
            return false;
        }
        if (!Objects.equals(this.pictureUrl, other.pictureUrl))
        {
            return false;
        }
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.cityInfo, other.cityInfo))
        {
            return false;
        }
        return true;
    }

    
}
