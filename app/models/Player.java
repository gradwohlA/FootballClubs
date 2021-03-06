package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.GenericModel;

@Entity
@Table(name="player")

public class Player extends GenericModel{
	@Id
	@GeneratedValue
	@Column(name="player_id")
	public Integer playerId;
	
	@Column(name="player_name")
	public String playerName;
	
	@Column(name="license_number")
	public Integer licenseNumber;
	
	@Column
	public Integer salary;
	
	@Column
	public String started;
	
	@Column(name="end_of_contract")
	public String endOfContract;
	
	@Column
	public String post;
	
	@ManyToOne
	@JoinColumn(name="club_id",referencedColumnName="club_id")
	public Club owningClub;
}
