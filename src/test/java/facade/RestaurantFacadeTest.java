package facade;

import DTO.RestaurantDTO;
import entity.CityInfo;
import entity.Restaurant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

 
public class RestaurantFacadeTest {
    
    RestaurantFacade rf = new RestaurantFacade(Persistence.createEntityManagerFactory("pusecond"));
    
    EntityManagerFactory emf;
    
    
    
    public RestaurantFacadeTest() {
        Persistence.generateSchema("pusecond", null);
    }
    
        private static Connection testConnection;
    private static final String USER = "seed_user";
    private static final String USERPW = "ostemad";
    private static final String DBNAME = "seedtest";
    private static final String HOST = "46.101.104.53";
    
    @Before
    public void setUp() {
                try {
            // avoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            //            // reset test database
            try (Statement stmt = testConnection.createStatement()) {
                
                stmt.execute("SET FOREIGN_KEY_CHECKS=0;");
                
                
               
                stmt.execute("drop table if exists RESTAURANT");
                stmt.execute("create table RESTAURANT like seed.RESTAURANT");
                
                stmt.execute("SET FOREIGN_KEY_CHECKS=1;");
                
                
             
            }
        } catch (SQLException | ClassNotFoundException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
      
        
        
        
    }

    /**
     * Test of getEntityManager method, of class RestaurantFacade.
     */
//    @Test
//    public void testGetEntityManager() {
//    }
//
//    /**
//     * Test of addRestaurant method, of class RestaurantFacade.
//     */
    @Test
    public void testAddRestaurant() {
      
        CityInfo ci = new CityInfo("2100", "kina");
        Restaurant rs = new Restaurant("Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        Restaurant rs2 = new Restaurant("Mondo2","Engelsk2","www.mad.d2k","alleen2","00", ci, "ingenurls");
        
        rf.addRestaurant(rs);
        rf.addRestaurant(rs2);
        assertFalse(rf.getAllRestaurants().isEmpty());
        
        
    }
    
    @Test
    public void testAddRestaurant2(){
        CityInfo ci = new CityInfo("2100", "kina");
        Restaurant rs = new Restaurant("Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        
        rf.addRestaurant(rs);
        assertEquals("Mondo", rf.getAllRestaurants().get(0).getRestName());
        
    }
    
    @Test
    public void testAddRestaurantFullObject(){
  
        Collection<RestaurantDTO> list = new ArrayList<>();

        
        long s = 1;
        CityInfo ci = new CityInfo("2100", "kina");
        
        
        RestaurantDTO rDTO = new RestaurantDTO(s,"Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        
        Restaurant rs = new Restaurant("Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        rf.addRestaurant(rs);
        list.add(rDTO);

        
        assertEquals(list,rf.getAllRestaurants());
    }

    /**
     * Test of getAllRestaurants method, of class RestaurantFacade.
     */
    @Test
    public void testGetAllRestaurants() {
        long s = 1;
        
        CityInfo ci = new CityInfo("2100", "kina");
        Restaurant rs = new Restaurant("Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        Restaurant rs2 = new Restaurant("Mondo2","Engelsk2","www.mad.d2k","alleen2","00", ci, "ingenurls");
        
        RestaurantDTO rDTO = new RestaurantDTO(s,"Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        
        rf.addRestaurant(rs);
        rf.addRestaurant(rs2);
        
        assertTrue(rf.getAllRestaurants().contains(rDTO));
         
    }
    @Test
    public void testget10restaurants(){
    CityInfo ci = new CityInfo("2100", "kina");
    Restaurant rs1 = new Restaurant("Mondo1","Engelsk1","www.mad1.dk","alleen 1","1", ci,"url");
    Restaurant rs2 = new Restaurant("Mondo2","Engelsk2","www.mad2.dk","alleen 2","2", ci, "url2");
    Restaurant rs3 = new Restaurant("Mondo3","Engelsk3","www.mad3.dk","alleen 3","3", ci, "url3");
    Restaurant rs4 = new Restaurant("Mondo4","Engelsk4","www.mad4.dk","alleen 4","4", ci, "url4");
    Restaurant rs5 = new Restaurant("Mondo5","Engelsk5","www.mad5.dk","alleen 5","5", ci, "url5");
    Restaurant rs6 = new Restaurant("Mondo6","Engelsk6","www.mad6.dk","alleen 6","6", ci, "url6");
    Restaurant rs7 = new Restaurant("Mondo7","Engelsk7","www.mad7.dk","alleen 7","7", ci, "url7");
    Restaurant rs8 = new Restaurant("Mondo8","Engelsk8","www.mad8.dk","alleen 8","8", ci, "url8");
    Restaurant rs9 = new Restaurant("Mondo9","Engelsk9","www.mad9.dk","alleen 9","9", ci, "url9");
    Restaurant rs10 = new Restaurant("Mondo10","Engelsk10","www.mad10.dk","alleen 10","10", ci, "url10");
    rf.addRestaurant(rs1);
    rf.addRestaurant(rs2);
    rf.addRestaurant(rs3);
    rf.addRestaurant(rs4);
    rf.addRestaurant(rs5);
    rf.addRestaurant(rs6);
    rf.addRestaurant(rs7);
    rf.addRestaurant(rs8);
    rf.addRestaurant(rs9);
    rf.addRestaurant(rs10);
    
    assertEquals(10, rf.getAllRestaurants().size());
}
    
    @Test
    public void testEquals10Restaurants(){    
    Collection<RestaurantDTO> list = new ArrayList<>();
    CityInfo ci = new CityInfo("2100", "kina");
        long a = 1;
        long b = 2;
        long c = 3;
        long d = 4;
        long e = 5;
        long f = 6;
        long g = 7;
        long h = 8;
        long i = 9;
        long j = 10;
        
     RestaurantDTO rDTO = new RestaurantDTO(a,"Mondo1","Engelsk1","www.mad1.dk","alleen 1","1", ci, "url"); 
     RestaurantDTO rDTO2 = new RestaurantDTO(b,"Mondo2","Engelsk2","www.mad2.dk","alleen 2","2", ci, "url2"); 
     RestaurantDTO rDTO3 = new RestaurantDTO(c,"Mondo3","Engelsk3","www.mad3.dk","alleen 3","3", ci, "url3"); 
     RestaurantDTO rDTO4 = new RestaurantDTO(d,"Mondo4","Engelsk4","www.mad4.dk","alleen 4","4", ci, "url4"); 
     RestaurantDTO rDTO5 = new RestaurantDTO(e,"Mondo5","Engelsk5","www.mad5.dk","alleen 5","5", ci, "url5"); 
     RestaurantDTO rDTO6 = new RestaurantDTO(f,"Mondo6","Engelsk6","www.mad6.dk","alleen 6","6", ci, "url6"); 
     RestaurantDTO rDTO7 = new RestaurantDTO(g,"Mondo7","Engelsk7","www.mad7.dk","alleen 7","7", ci, "url7"); 
     RestaurantDTO rDTO8 = new RestaurantDTO(h,"Mondo8","Engelsk8","www.mad8.dk","alleen 8","8", ci, "url8"); 
     RestaurantDTO rDTO9 = new RestaurantDTO(i,"Mondo9","Engelsk9","www.mad9.dk","alleen 9","9", ci, "url9"); 
     RestaurantDTO rDTO10 = new RestaurantDTO(j,"Mondo10","Engelsk10","www.mad10.dk","alleen 10","10", ci, "url10"); 

    Restaurant rs1 = new Restaurant("Mondo1","Engelsk1","www.mad1.dk","alleen 1","1", ci,"url");
    Restaurant rs2 = new Restaurant("Mondo2","Engelsk2","www.mad2.dk","alleen 2","2", ci, "url2");
    Restaurant rs3 = new Restaurant("Mondo3","Engelsk3","www.mad3.dk","alleen 3","3", ci, "url3");
    Restaurant rs4 = new Restaurant("Mondo4","Engelsk4","www.mad4.dk","alleen 4","4", ci, "url4");
    Restaurant rs5 = new Restaurant("Mondo5","Engelsk5","www.mad5.dk","alleen 5","5", ci, "url5");
    Restaurant rs6 = new Restaurant("Mondo6","Engelsk6","www.mad6.dk","alleen 6","6", ci, "url6");
    Restaurant rs7 = new Restaurant("Mondo7","Engelsk7","www.mad7.dk","alleen 7","7", ci, "url7");
    Restaurant rs8 = new Restaurant("Mondo8","Engelsk8","www.mad8.dk","alleen 8","8", ci, "url8");
    Restaurant rs9 = new Restaurant("Mondo9","Engelsk9","www.mad9.dk","alleen 9","9", ci, "url9");
    Restaurant rs10 = new Restaurant("Mondo10","Engelsk10","www.mad10.dk","alleen 10","10", ci, "url10");
    rf.addRestaurant(rs1);
    rf.addRestaurant(rs2);
    rf.addRestaurant(rs3);
    rf.addRestaurant(rs4);
    rf.addRestaurant(rs5);
    rf.addRestaurant(rs6);
    rf.addRestaurant(rs7);
    rf.addRestaurant(rs8);
    rf.addRestaurant(rs9);
    rf.addRestaurant(rs10);
    list.add(rDTO);
    list.add(rDTO2);
    list.add(rDTO3);
    list.add(rDTO4);
    list.add(rDTO5);
    list.add(rDTO6);
    list.add(rDTO7);
    list.add(rDTO8);
    list.add(rDTO9);
    list.add(rDTO10);
    assertEquals(list, rf.getAllRestaurants());
   

    }

    /**
     * Test of getOtherRestaurants method, of class RestaurantFacade.
     */
    @Test
    public void testGetOtherRestaurants() {
        System.out.println("LETS SEE ALL THE OTHERS RESTAURANTS"
                + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
        
        assertFalse(rf.getOtherRestaurants().isEmpty());
    }

    /**
     * Test of editRestaurant method, of class RestaurantFacade.
     */
    @Test
    public void testEditRestaurant() {
    }

    /**
     * Test of getRestaurantDTOByNameAndPhone method, of class RestaurantFacade.
     */
    @Test
    public void testGetRestaurantDTOByNameAndPhone() {
    }

    /**
     * Test of getRestaurantDTOById method, of class RestaurantFacade.
     */
    @Test
    public void testGetRestaurantDTOById() {  
        long s = 1;
        CityInfo ci = new CityInfo("2100", "kina");
        Restaurant rs = new Restaurant("Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
          RestaurantDTO rDTO = new RestaurantDTO(s,"Mondo","Engelsk","www.mad.dk","alleen","0", ci, "ingenurl");
        
        rf.addRestaurant(rs);
        assertEquals(rDTO , rf.getRestaurantDTOById(s));
    }
}