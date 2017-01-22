package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name="club")
public class Club extends GenericModel{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="club_id")
	public Integer clubId;

	@Column(name="club_name")
	public String clubName;
	
	@Column(name="foundation_year")
	public Integer foundationYear;

	@Column
	public String country;
	
	@Column
	public String county;
	
	@Column
	public String city;
	
	@OneToMany(mappedBy="owningClub")
	public List<Player> players;
	
	public Integer getPlayers(){
		return this.players.size();
	}
	
	public Integer getSumSalary(){
		Integer sum=0;
		List<Player> list = Player.findAll();
		for(Player player : list){
			if(player.owningClub.clubId.equals(this.clubId))
			sum+=player.salary;
		}
		return sum;
	}
}
