package entity;

import entity.CityInfo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-04T15:13:56")
@StaticMetamodel(Restaurant.class)
public class Restaurant_ { 

    public static volatile SingularAttribute<Restaurant, String> website;
    public static volatile SingularAttribute<Restaurant, String> phone;
    public static volatile SingularAttribute<Restaurant, String> foodType;
    public static volatile SingularAttribute<Restaurant, String> street;
    public static volatile SingularAttribute<Restaurant, String> pictureUrl;
    public static volatile SingularAttribute<Restaurant, CityInfo> cityInfo;
    public static volatile SingularAttribute<Restaurant, Long> id;
    public static volatile SingularAttribute<Restaurant, String> restName;

}