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



@Path("/movie")
public class MovieRecourses {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Movie>getMovie(){
		return MovieDAO.INSTANCE.getMovie();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{movieId}")
	public Movie getMovie(@PathParam("movieId") String id){
		return MovieDAO.INSTANCE.getId(Integer.parseInt(id));
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postMovie(
			@FormParam("id") String id,
			@FormParam("name") String name,
			@FormParam("director") String director,
			@FormParam("date") String date,
			@FormParam("price") String price,
			@FormParam("place") String place,
			
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Inside POST:");
		System.out.println("Name = " + name);
		System.out.println("Surname = " + director);
		System.out.println("Date = " + date);
		System.out.println("Price = " + price);
		System.out.println("Place = " + place);
		
		
		Movie movie = new Movie();
		movie.setId(Integer.parseInt(id));
		movie.setName(name);
		movie.setDirector(director);
		movie.setDate(date);
		movie.setPrice(price);
		movie.setPlace(place);
		
		
		MovieDAO.INSTANCE.saveMovie(movie);
		System.out.println("Data on Movie "+name+" saved in Database");
		servletResponse.sendRedirect("../createMovie.html");
		//return "Data on "+name+" "+surname+" saved in DB";
	}
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{movieId}")
	public static void main(@PathParam("movieId") String id,
			@FormParam("name") String name,
			@FormParam("director") String director,
			@FormParam("date") String date,
			@FormParam("price") String price,
			@FormParam("place") String place,
			
			@Context HttpServletResponse servletResponse) throws IOException, SQLException{
		System.out.println("PUT id="+id);
		
		Movie movie = new Movie();
		movie.setId(Integer.parseInt(id));
		movie.setName(name);
		movie.setDirector(director);
		movie.setDate(date);
		movie.setPrice(price);
		movie.setPlace(place);
		
		MovieDAO.INSTANCE.Update(movie);
		
	}		
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("{movieId}")
	public void deleteMovie(
			@PathParam("movieId") int id,
			@Context HttpServletResponse servletResponse)throws IOException {
		MovieDAO.INSTANCE.delete(id);
		System.out.println("Movie with Id "+id+" deleted from Database");
	}
	
}
