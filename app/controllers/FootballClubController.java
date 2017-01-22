package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Club;
import models.Player;
import play.mvc.Controller;

public class FootballClubController extends Controller{

	public static void footballClubs(Integer clubId){
		List<Club> lists = Club.findAll();
		renderArgs.put("clubs", lists);
		render("@Application.FootballClubs");
	}
	
	public static void clubDetails(Integer clubId){
		Club club = null;
		
		if (clubId != null){
			club = Club.findById(clubId);
		}
		
		if (club == null){
			FootballClubController.footballClubs(null);
		} else {
			renderArgs.put("club", club);
			
			List<String> errors=new ArrayList<String>();
			errors.add("forward");
			errors.add("midfielder");
			errors.add("defender");
			errors.add("goalkeeper");
			
			List<Player> list = Player.findAll();
			List<Player> list2 = new ArrayList<Player>();
			for(Player p : list){
				if(p.owningClub.clubId.equals(clubId)){
					list2.add(p);
					
					if(p.post.equals("Forward")) errors.remove("forward");
					if(p.post.equals("Defender")) errors.remove("defender");
					if(p.post.equals("Midfielder")) errors.remove("midfielder");
					if(p.post.equals("Goalkeeper")) errors.remove("goalkeeper");
				}
				
			}
			renderArgs.put("list", list2);
			renderArgs.put("errorList", errors);
			render("@Application.clubDetails");
			
		}
	}
}
