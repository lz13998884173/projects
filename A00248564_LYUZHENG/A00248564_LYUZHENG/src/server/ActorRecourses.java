package server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



@Path("/Actor")
public class ActorRecourses {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Actor>getActor(){
		return ActorDAO.INSTANCE.getActor();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{ActorId}")
	public Actor getActor(@PathParam("ActorId") String id){
		return ActorDAO.INSTANCE.getId(Integer.parseInt(id));
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postActor(
			@FormParam("id") String id,
			@FormParam("fname") String fname,
			@FormParam("sname") String sname,
			@FormParam("birthday") String birthday,
			@FormParam("phone") String phone,
			@FormParam("email") String email,
			@FormParam("address") String address,
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Inside POST:");
		System.out.println("Name = " + fname);
		System.out.println("Surname = " + sname);
		System.out.println("Birthday = " + birthday);
		System.out.println("Phone = " + phone);
		System.out.println("Email = " + email);
		System.out.println("Address = " + address);
		
		Actor actor = new Actor();
		actor.setId(Integer.parseInt(id));
		actor.setFname(fname);
		actor.setSname(sname);
		actor.setBirthday(birthday);
		actor.setPhone(phone);
		actor.setEmail(email);
		actor.setAddress(address);
		
		ActorDAO.INSTANCE.saveActor(actor);
		System.out.println("Data on Actor "+fname+" "+sname+" saved in Database");
		servletResponse.sendRedirect("../createActor.html");
		//return "Data on "+name+" "+surname+" saved in DB";
	}
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{ActorId}")
	public static void main(@PathParam("ActorId") String id,
			@FormParam("fname") String fname,
			@FormParam("sname") String sname,
			@FormParam("birthday") String birthday,
			@FormParam("phone") String phone,
			@FormParam("email") String email,
			@FormParam("address") String address,
			@Context HttpServletResponse servletResponse) throws IOException, SQLException{
		System.out.println("PUT id="+id);
		Actor actor = new Actor();
		actor.setId(Integer.parseInt(id));
		actor.setFname(fname);
		actor.setSname(sname);
		actor.setBirthday(birthday);
		actor.setPhone(phone);
		actor.setEmail(email);
		actor.setAddress(address);
		ActorDAO.INSTANCE.Update(actor);
		
	}		
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{ActorId}")
	public void deleteActor(
			@PathParam("ActorId") int id,
			@Context HttpServletResponse servletResponse)throws IOException {
		ActorDAO.INSTANCE.delete(id);
		System.out.println("Actor with Id "+id+" deleted from Database");
	}
	
}
