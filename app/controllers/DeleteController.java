package controllers;

import java.util.List;

import models.Club;
import models.Player;
import play.mvc.Controller;

public class DeleteController extends Controller{
	
	public static void deleteClub(Integer clubId){
		
		if(clubId!=null){
			Club club=Club.find("clubId= ?", clubId).first();
			if(club!=null){
				List<Player> list = Player.findAll();
					for(Player player : list){
						if(player.owningClub.clubId.equals(clubId))
							player.delete();
					}
					club.delete();
				}
				
				
			}
		FootballClubController.footballClubs(null);
		}
	public static void deletePlayer(Integer playerId){
		
		if(playerId!=null){
			Player player=Player.find("playerId= ?", playerId).first();
			if(player!=null){
						player.delete();
			}
		FootballClubController.footballClubs(null);
		}
	}
}

