import { Component} from '@angular/core';
import { FootballClubs } from './frontendClasses/FootballClubs';
import { FreeapiService } from './apiService/apiServices.service';
import {RandomMatches} from './frontendClasses/randomMatches';
import {SortByDate} from './frontendClasses/SortByDate';
import { MatchSimulation } from './frontendClasses/MatchSimulation';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  popUpRandomMatch = false;//random match popup set to false
  sortByDatePopUpBox = false;//sort date pop up set to false
  searchDatePopUpBox = false;//search date pop up set to false
  title = 'Premier League Manager';//title of the angular project

  footballClubs:FootballClubs[];//football club array 
  matchSimulation:MatchSimulation[];//matches played array
  randomMatches:RandomMatches[];//random match playing array
  sortByDateAscendingOrder:SortByDate[];//sort date in ascending order array

  //day,month and year which contain the user input values in the search field
  day:number;
  month:number;
  year:number;

//search field elements
dateFound=false;
alertBox=false;
homeTeam:string;
opponentTeam:string;
dateOfMatchPlayed:{day:number,month:number,year:number};
goalsScoredHomeTeam:number;
goalsScoredOpponentTeam:number;
tempSearchArray=new Array();

//random match elements
homeTeamRandomMatchFound=false;
opponentTeamRandomMatchFound=false;
homeTeamRandomMatch:FootballClubs;
opponentTeamRandomMatch:FootballClubs;
  
//headings of the tables 
public headings_footballClub=["Club Name", "Country", "Location","No Of Matches Played", "Matches Won", "Matches Lost", "Matches Drawn", "Goals Scored", "Goals Received", "Points Scored","University Name","School Name","No Of Players"];
public headings_filterByDate=["Home Team", "Opponent Team", "Date Of Match Played","Goals Scored Home Team", "Goals Scored Opponent Team"];
public headingRandomMatch=["Home Team", "Opponent Team", "Date Of Match Played","Goals Scored Home Team", "Goals Scored Opponent Team"];

//clock and clock handle
clock=""
clockHandle;
  constructor(private apiService:FreeapiService) { 
   
  }

  ngOnInit(){
    
    //get the football clubs from the http://localhost:9000
    this.apiService.getFootballClubs()
    .subscribe(
      data =>{
        this.footballClubs = data;

         }
    );  

    //get the football clubs from the http://localhost:9000/sortByDate
    this.apiService.getSortByDate()
    .subscribe(
      data =>{      
          this.sortByDateAscendingOrder = data;
            
      }      
    );

    //get the football clubs from the http://localhost:9000/matchesPlayed
    this.apiService.getMatchSimulation()
    .subscribe(
      data =>{      
          this.matchSimulation = data;          
      
      }      
    );    
   
    //code for the clock is taken from =>
  //https://stackblitz.com/edit/angular-clock-1-q2tuyq?file=src%2Fapp%2Fapp.component.html
   this.clockHandle = setInterval(()=>{
        this.clock = new Date().toLocaleString();},1000);
  }


//search function
searchButton(){  

//empty the array in begining of each loop
this.tempSearchArray=[];
 
  //find if the date entered by the user is in the array
   for(let matchSimulationSearch of this.matchSimulation){
        if((this.day==matchSimulationSearch.dateOfMatchPlayed.day) && (this.month==matchSimulationSearch.dateOfMatchPlayed.month) && (this.year==matchSimulationSearch.dateOfMatchPlayed.year)){
          this.homeTeam=matchSimulationSearch.homeTeam;
          this.opponentTeam=matchSimulationSearch.opponentTeam;
          this.dateOfMatchPlayed={day: matchSimulationSearch.dateOfMatchPlayed.day,month:matchSimulationSearch.dateOfMatchPlayed.month,year:matchSimulationSearch.dateOfMatchPlayed.year}
          this.goalsScoredHomeTeam=matchSimulationSearch.goalsScoredHomeTeam;
          this.goalsScoredOpponentTeam=matchSimulationSearch.goalsScoredOpponentTeam;  
          //if the date is in the array displaying the modal
          this.searchDatePopUpBox=true;  
          //if the date is in the array making the datefound boolean value to true
          this.dateFound=true; 
          //if the date is in the array pushing the relavent information to the temporary array     
          this.tempSearchArray.push({homeTeam:matchSimulationSearch.homeTeam,opponentTeam:matchSimulationSearch.opponentTeam,dateMatchPlayed:matchSimulationSearch.dateOfMatchPlayed,goalsScoredHomeTeam:matchSimulationSearch.goalsScoredHomeTeam,goalsScoredOpponentTeam:matchSimulationSearch.goalsScoredOpponentTeam});
        }
    }
    
  //if the length of the temporary array is 0 making the day,month and year text filds to null and displaying an alert box
  if(this.tempSearchArray.length==0){
  for(let matchSimulationSearch of this.matchSimulation){
    //displaying an error message if one component of the date is found
    if((this.day==matchSimulationSearch.dateOfMatchPlayed.day) || (this.month==matchSimulationSearch.dateOfMatchPlayed.month) || (this.year==matchSimulationSearch.dateOfMatchPlayed.year)){
      this.day=null;
      this.month=null;
      this.year=null;
      alert("ERROR ! DATE CANNOT BE FOUND !!!");
      break;
    //displaying an error message if all the components of the date are not found
   }if(!((this.day==matchSimulationSearch.dateOfMatchPlayed.day) && (this.month==matchSimulationSearch.dateOfMatchPlayed.month) && (this.year==matchSimulationSearch.dateOfMatchPlayed.year))){
    this.day=null;
    this.month=null;
    this.year=null;
    alert("ERROR ! DATE CANNOT BE FOUND !!!");
     break;
   }
   break;
  }
}   
}

//close the filter by date modal after the button click
closePopUpFilterByDate(){
  this.searchDatePopUpBox=false;

}

  // sort number of wins in descending order
   compareWins(object_1, object_2, key){
    const obj_1 = object_1[key];
    const obj_2 = object_2[key];
  
    if (obj_1 > obj_2) {//if the object_1 is greater than object_2 shift the relavent row upper
      return -1
    }
    
    return 0//else do nothing
  }


  //button click action for sort number of wins
  sortNoOfWins(){    
    this.footballClubs.sort((a,b)=>{//sort the matches won column by calling the above compare wins method
      return this.compareWins(a, b, 'matchesWon')    

    }
    )
  }



//sort goals scored in descending order
compareGoalsScored(object_1, object_2, key){
  const obj_1 = object_1[key];
  const obj_2 = object_2[key];

  if (obj_1 > obj_2) {//if the object_1 is greater than object_2 shift the relavent row upper
    return -1
  }
  
  return 0//else do nothing
}

  //button click action for sort number of goals scored
sortGoalsScored(){

  this.footballClubs.sort((a,b)=>{//sort the goals scored column by calling the above compare goals scored method
    return this.compareGoalsScored(a, b, 'goalsScored')   

  }
  )
}

//sort points scored in descending order
comparedPointsScored(object1, object2, key){
  const obj1 = object1[key];
  const obj2 = object2[key];

  if (obj1 > obj2) {//if the object_1 is greater than object_2 shift the relavent row upper
    return -1
  }
  
  return 0//else do nothing
}


sortByPoints(){

  this.footballClubs.sort((a,b)=>{//sort the points scored column by calling the above compare points scored method
    return this.comparedPointsScored(a, b, 'pointsScored')    

  }
  )
}

//random match button click action method
randomMatch(){   

  //call the api after every button click(new random match will be generated)
  this.apiService.getRandomMatches()
  .subscribe(
    data =>{      
        this.randomMatches = data;      
    }
    
  );
//show the random match modal 
this.popUpRandomMatch=true;

}

//close the random match modal
closePopUpRandomMatch(){

this.popUpRandomMatch=false;

//if the club name is in the match simulation home team make the boolean home team random match to true and set the football club to the home team random match boolean value
for(let footballClubsRandomMatch of this.footballClubs){
  for(let matchSimulationRandomMatch of this.randomMatches){
   if(footballClubsRandomMatch.clubName.includes(matchSimulationRandomMatch.homeTeam)){
   this.homeTeamRandomMatchFound=true;
    this.homeTeamRandomMatch=footballClubsRandomMatch;

 }
 } 
}

//if the club name is in the match simulation opponent team make the boolean opponent team random match to true and set the football club to the opponent team random match boolean value
for(let footballClubsRandomMatch of this.footballClubs){
  for(let matchSimulationRandomMatch of this.randomMatches){
 if(footballClubsRandomMatch.clubName.includes(matchSimulationRandomMatch.opponentTeam)){
   this.opponentTeamRandomMatchFound=true;
    this.opponentTeamRandomMatch=footballClubsRandomMatch;

 }
 } 
 }

//if both home team random match and opponent team random match found true
if (this.homeTeamRandomMatchFound==true && this.opponentTeamRandomMatchFound==true){
    for(let matchSimulationRandomMatch of this.randomMatches){
      //increase the number of matches played by one
      this.homeTeamRandomMatch.noOfMatchesPlayed=this.homeTeamRandomMatch.noOfMatchesPlayed+1;
      //updating the goals scored of the home team
      this.homeTeamRandomMatch.goalsScored=this.homeTeamRandomMatch.goalsScored+matchSimulationRandomMatch.goalsScoredHomeTeam;
      //updating the goals received by the home team
      this.homeTeamRandomMatch.goalsReceived=this.homeTeamRandomMatch.goalsReceived+matchSimulationRandomMatch.goalsScoredOpponentTeam;
      
      //increase the number of matches played by one
      this.opponentTeamRandomMatch.noOfMatchesPlayed=this.opponentTeamRandomMatch.noOfMatchesPlayed+1;
      //updating the goals scored of the opponent team
      this.opponentTeamRandomMatch.goalsScored=this.opponentTeamRandomMatch.goalsScored+matchSimulationRandomMatch.goalsScoredOpponentTeam;
      //updating the goals received by the opponent team
      this.opponentTeamRandomMatch.goalsReceived=this.opponentTeamRandomMatch.goalsReceived+matchSimulationRandomMatch.goalsScoredHomeTeam;
  
      //if the goals scored by home team is greater than the goals scored by the opponent team
    if(matchSimulationRandomMatch.goalsScoredHomeTeam > matchSimulationRandomMatch.goalsScoredOpponentTeam){
      //increasing the points of the home team by 3
      this.homeTeamRandomMatch.pointsScored=this.homeTeamRandomMatch.pointsScored+3;
      //increasing the number of matches won by the home team by one
      this.homeTeamRandomMatch.matchesWon=this.homeTeamRandomMatch.matchesWon+1;
      //increasing the number of matches lost by the opponent team by one
      this.opponentTeamRandomMatch.matchesLost=this.opponentTeamRandomMatch.matchesLost+1;

    }

    //if the goals scored by opponent team is greater than the goals scored by the home team
    if(matchSimulationRandomMatch.goalsScoredHomeTeam < matchSimulationRandomMatch.goalsScoredOpponentTeam){
      //increasing the points of the opponent team by 3
      this.opponentTeamRandomMatch.pointsScored=this.opponentTeamRandomMatch.pointsScored+3;
      //increasing the number of matches won by the opponent team by one
      this.opponentTeamRandomMatch.matchesWon=this.opponentTeamRandomMatch.matchesWon+1;
     //increasing the number of matches lost by the home team by one
      this.homeTeamRandomMatch.matchesLost=this.homeTeamRandomMatch.matchesLost+1;
    }

    //if the goals scored by the home team and the opponent team are equal
    if(matchSimulationRandomMatch.goalsScoredHomeTeam == matchSimulationRandomMatch.goalsScoredOpponentTeam){
      //increasing the number of points scored by the home club by one
      this.homeTeamRandomMatch.pointsScored=this.homeTeamRandomMatch.pointsScored+1;
      //increasing the number of points scored by the opponent club by one
      this.opponentTeamRandomMatch.pointsScored=this.opponentTeamRandomMatch.pointsScored+1;
      //increasing the number of matches drawn by the home club by one
      this.homeTeamRandomMatch.matchesDrawn=this.homeTeamRandomMatch.matchesDrawn+1;
      //increasing the number of matches drawn by the opponent club by one
      this.opponentTeamRandomMatch.matchesDrawn=this.opponentTeamRandomMatch.matchesDrawn+1;

    }

}
}

//push the relavent data to the match simulation array
for(let matchSimulationRandomMatch of this.randomMatches){
       this.matchSimulation.push({homeTeam:matchSimulationRandomMatch.homeTeam,opponentTeam:matchSimulationRandomMatch.opponentTeam,dateOfMatchPlayed:matchSimulationRandomMatch.dateOfMatchPlayed,goalsScoredHomeTeam:matchSimulationRandomMatch.goalsScoredHomeTeam,
        goalsScoredOpponentTeam:matchSimulationRandomMatch.goalsScoredOpponentTeam});
        
      }
  
//push the relavent data to the sortdate ascending order array    
for(let matchSimulationRandomMatch of this.randomMatches){
  this.sortByDateAscendingOrder.push({homeTeam:matchSimulationRandomMatch.homeTeam,opponentTeam:matchSimulationRandomMatch.opponentTeam,dateOfMatchPlayed:matchSimulationRandomMatch.dateOfMatchPlayed,goalsScoredHomeTeam:matchSimulationRandomMatch.goalsScoredHomeTeam,
   goalsScoredOpponentTeam:matchSimulationRandomMatch.goalsScoredOpponentTeam});
   
 }
}

//sort date in ascending order button click action
sortByDate(){ 

    //first sort the date by day
    this.sortByDateAscendingOrder.sort((a, b) => (a.dateOfMatchPlayed.day < b.dateOfMatchPlayed.day ? -1 : 1));
    //second sort the date by month
    this.sortByDateAscendingOrder.sort((a, b) => (a.dateOfMatchPlayed.month < b.dateOfMatchPlayed.month ? -1 : 1));

  //open the modal sort date pop up box
  this.sortByDatePopUpBox=true;
}

//close the sort date pop up box after a button click
closePopUpsortByDate(){
  this.sortByDatePopUpBox=false;
}
}