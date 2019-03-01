package entity;

import entity.Restaurant;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-04T15:13:56")
@StaticMetamodel(CityInfo.class)
public class CityInfo_ { 

    public static volatile SingularAttribute<CityInfo, String> zip;
    public static volatile SingularAttribute<CityInfo, String> city;
    public static volatile CollectionAttribute<CityInfo, Restaurant> restaurants;

}