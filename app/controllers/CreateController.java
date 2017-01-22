package controllers;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import models.Club;
import models.Player;
import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import util.Validations;

public class CreateController extends Controller{
	public static List<String> posts = Arrays.asList("Goalkeeper","Defender","Midfielder","Forward");
	
	public static void createClubForm(@Required Integer clubId){
		renderArgs.put("clubId",clubId);
		render("@Application.createClub");
	}
	
	public static void createClub(@Required(message="Name is required") String clubName,
									@Required(message="Foundation year is required") Integer foundationYear,
									@Required(message="Country is required") String country,
									@Required(message="County is required") String county,
									@Required(message="City is required") String city){
		LocalDateTime ldt = LocalDateTime.now();
		if(foundationYear==null || foundationYear<1862 || foundationYear>ldt.now().getYear()){
			validation.addError("foundationYear", "The foundation year need to be between 1862 and "+ldt.now().getYear()+".");
		}
		if(clubName!=null){
			Club club=Club.find("clubName = ?", clubName).first();
			if(club!=null) validation.addError("clubName", "This name is taken");
		}
		if (validation.hasErrors()){ //
			params.flash();
			render("@Application.createClub");
		} 
		else{
			Club club=new Club();
			club.clubName=clubName;
			club.foundationYear=foundationYear;
			club.country=country;
			club.county=county;
			club.city=city;
			club.save();
			
			FootballClubController.footballClubs(null);
		}
	}
	@Before
	public static void prepare(){
		renderArgs.put("posts", posts);
	}
	public static void createPlayerForm(@Required Integer clubId){
		renderArgs.put("clubId", clubId);
		render("@Application.createPlayer");
	}
	
	public static void createPlayer(@Required Integer clubId,@Required(message="Name is required") String playerName,
			@Required(message="License number is required") Integer licenseNumber,
			@Required(message="Salary is required") Integer salary,
			@Required(message="Started is required") String started,
			@Required(message="End of contract is required") String endOfContract, String post){
		
		
		if(!Validations.isDateFormat(started)){
			validation.addError("started", "The format is wrong! Please try this: YYYY-MM-DD");
		}
		if(!Validations.isDateFormat(endOfContract)){
			validation.addError("endOfContract", "The format is wrong! Please try this: YYYY-MM-DD");
		}
		if(licenseNumber!=null){
			Player pl=Player.find("license_number=?", licenseNumber).first();
			if(pl!=null) validation.addError("licenseNumber", "This license number is taken!");
		}
		
		if(salary==null || salary<150000) validation.addError("salary", "Minimum salary is 150.000 HUF");
		
		
		if (validation.hasErrors()){
			
			params.flash();
			renderArgs.put("clubId", clubId);
			render("@Application.createPlayer");
		} else {
			
			Player player=new Player();
			player.playerName=playerName;
			player.owningClub=Club.findById(clubId);
			player.licenseNumber=licenseNumber;
			player.salary=salary;
			player.started=started;
			player.endOfContract=endOfContract;
			player.post=post;
			
			player.save();
			FootballClubController.footballClubs(clubId);
		
		}
		
	}
}
