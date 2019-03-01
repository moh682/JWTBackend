package rest;

import DTO.CityInfoDTO;
import DTO.RestaurantDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.google.protobuf.DescriptorProtos.MethodOptions.parser;
import entity.CityInfo;
import entity.Restaurant;
import facade.RestaurantFacade;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author RasmusFriis
 */
@Path("info")
public class Resource
{

    @Context
    private UriInfo context;
    Gson gson;

    RestaurantFacade rf = new RestaurantFacade(Persistence.createEntityManagerFactory("pu"));
    private JsonParser jsonParser = new JsonParser();

    /**
     *
     */
    public Resource()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

//    public String threadExecutor()
//    {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        List<Future<String>> list = new ArrayList<>();
//        String response = "";
//        for (String url : urls)
//        {
//            Callable<String> callable = new Call(url);
//
//            Future<String> future = executor.submit(callable);
//
//            list.add(future);
//        }
//        response = "[";
//        for (Future<String> fut : list)
//        {
//            try
//            {
//                String fetchedData = fut.get();
//                if (!fetchedData.contains("error"))
//                {
//                    response += fetchedData + ",";
//                }
//
//            } catch (InterruptedException | ExecutionException e)
//            {
//                e.printStackTrace();
//            }
//        }
//        executor.shutdown();
//        String substring = response.substring(0, response.length() - 1); // if all fails ] will be deleted here
//        response = substring;
//        return response += "]";
//    }
    @Context
    SecurityContext securityContext;

    /**
     *
     * @return a string with the User object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin()
    {
        String user = securityContext.getUserPrincipal().getName();
        return user; // FRONTEND
    }

    /**
     * Used when fetch method is GET, with this Path.
     *
     * @return if no errors occoured in facade.RestaurantFacade.java return
     * restaurants. else return error message
     * @throws MalformedURLException ?
     * @throws IOException ?
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("durumborestaurants")
    public Response getDurumboRestaurants() throws MalformedURLException, IOException
    {

        List<RestaurantDTO> restaurantList = rf.getAllRestaurants();
        String json = gson.toJson(restaurantList);

        if (restaurantList != null)
        {
            return Response
                    .status(200)
                    .entity(json)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else
        {
            JsonObject error = new JsonObject();
            error.addProperty("ErrorMessage", json);

            return Response
                    .status(500)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }
    }
    
    /**
     *
     * @param id
     * @param json
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("edit/{id}")
    public Response editRestaurant(@PathParam("id") String id, String json)
    {

        Restaurant r = gson.fromJson(json, Restaurant.class);
        
        if (rf.getRestaurantDTOById(Long.parseLong(id)) != null)
        {
            RestaurantDTO editedRestaurant = rf.editRestaurant(r);
            
            return Response
                    .status(200)
                    .entity(gson.toJson(editedRestaurant))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else
        { 
            JsonObject error = new JsonObject();
            error.addProperty("ErrorMessage", "Error has occured");

            return Response
                    .status(500)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    /**
     *
     * @param json
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response addRestaurants(String json)
    {

        Restaurant r = gson.fromJson(json, Restaurant.class);
        if (rf.getRestaurantDTOByNameAndPhone(r.getName(),r.getPhone()) == null)
        {

            RestaurantDTO createdRestaurant = rf.addRestaurant(r);
            
            return Response
                    .status(200)
                    .entity(gson.toJson(createdRestaurant))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else
        { 

            JsonObject error = new JsonObject();
            error.addProperty("ErrorMessage", "Restaurant already exist");

            return Response
                    .status(500)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allrestaurants")
    public Response getAllRestaurants()
    {
        List<RestaurantDTO> restaurants = rf.getAllRestaurants();

        String remote = rf.getOtherRestaurants();
        System.out.println(remote); // remove when UTF-8 works

        if (remote.contains("->Red<-"))
        {
            remote = null;
        }

        if (remote != null)
        {
            long nextID = (restaurants.get(restaurants.size() - 1).id + 1);

            System.out.println(remote);

            JsonArray jo = jsonParser.parse(remote).getAsJsonArray();
            List<RestaurantDTO> list = new ArrayList<>();
            for (JsonElement jsonElement : jo)
            {
                RestaurantDTO dto = new RestaurantDTO();
                dto.phone = jsonElement.getAsJsonObject().get("phone").getAsString();
                dto.restName = jsonElement.getAsJsonObject().get("restName").getAsString();
                dto.foodType = jsonElement.getAsJsonObject().get("foodType").getAsString();
                dto.street = jsonElement.getAsJsonObject().get("street").getAsString();
                if (jsonElement.getAsJsonObject().has("website"))
                {
                    dto.website = jsonElement.getAsJsonObject().get("website").getAsString();
                }
                JsonElement newjo = jsonElement.getAsJsonObject().get("cityInfo");
                String zip = newjo.getAsJsonObject().get("zip").getAsString();
                String city = newjo.getAsJsonObject().get("city").getAsString();
                CityInfo cityInfo = new CityInfo(zip, city);
                CityInfoDTO cityInfoDTO = new CityInfoDTO(cityInfo);
                dto.cityInfo = cityInfoDTO;
                dto.id = nextID;
                nextID++;
                list.add(dto);
            }
            restaurants.addAll(list);
            
            return Response
                    .status(200)
                    .entity(gson.toJson(restaurants))
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } else if (restaurants != null)
        {
            return Response
                    .status(200)
                    .entity(gson.toJson(restaurants))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        else
        {
            JsonObject error = new JsonObject();
            error.addProperty("ErrorMessage", restaurants + ", " + remote);

            return Response
                    .status(500)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("restaurant/{id}")
    public Response getRestaurant(@PathParam("id") String id)
    {
        RestaurantDTO restaurant = rf.getRestaurantDTOById(Long.parseLong(id));
    
        if (restaurant != null)
        {
            
            return Response
                    .status(200)
                    .entity(gson.toJson(restaurant))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else
        { 
            JsonObject error = new JsonObject();
            error.addProperty("ErrorMessage", "Restaurant Does not exist");

            return Response
                    .status(500)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
}
