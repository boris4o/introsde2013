package introsde.crud.rest.resources;
import introsde.crud.rest.model.HealthProfile;
import introsde.crud.rest.model.Person;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/ehealthv1")
public class PersonHealthProfile {
	
	public static Map<String,Person> database = new HashMap<String,Person>();
	
	static
    {
    	Person pallino = new Person();
		Person pallo = new Person("Pinco","Pallo");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person("John","Doe",hp);
		
		database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
		database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
		database.put(john.getFirstname()+" "+john.getLastname(), john);
    }

	// When client wants JSON
	@GET
	@Path("staticTest")
	@Produces(MediaType.APPLICATION_JSON)
	public String readHealthProfiles() throws JSONException {
		JSONObject result = new JSONObject();
		JSONArray jsonPeople = new JSONArray();
		for (Person person : database.values()) {
			
			JSONObject pObj=new JSONObject();
			pObj.put("firstname", person.getFirstname());
			pObj.put("lastname", person.getLastname());
			
			HealthProfile h = person.gethProfile();
			JSONObject hObj=new JSONObject();
			hObj.put("weight", h.getWeight());
			hObj.put("height", h.getHeight());
			
			pObj.put("profile", hObj);
			
			jsonPeople.put(pObj);
		}
		result.put("people",jsonPeople);
		System.out.println(result.toString());
		
		return result.toString();
	}
	
	
	
	
	
	
//	@POST
//	  @Produces(MediaType.TEXT_HTML)
//	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	  public void newTodo(@FormParam("id") String id,
//	      @FormParam("summary") String summary,
//	      @FormParam("description") String description,
//	      @Context HttpServletResponse servletResponse) throws IOException {
//	    Todo todo = new Todo(id,summary);
//	    if (description!=null){
//	      todo.setDescription(description);
//	    }
//	    TodoDao.instance.getModel().put(id, todo);
//	    
//	    servletResponse.sendRedirect("../create_todo.html");
//	  }
	
}