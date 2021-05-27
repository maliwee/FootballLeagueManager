import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
  export class FreeapiService {

  constructor(private httpclient:HttpClient) { }

  //call the localhost:9000 from the api as a json format
  getFootballClubs():Observable<any>{
    return this.httpclient.get("http://localhost:9000");
  }

  //call the localhost:9000/matchesPlayed from the api as a json format
    getMatchSimulation():Observable<any>{
      return this.httpclient.get("http://localhost:9000/matchesPlayed");
  }

    //call the localhost:9000/randomMatch from the api as a json format
    getRandomMatches():Observable<any>{
      return this.httpclient.get("http://localhost:9000/randomMatch");
    }

    //call the localhost:9000/sortByDate from the api as a json format
    getSortByDate():Observable<any>{
      return this.httpclient.get("http://localhost:9000/sortByDate");
    }


}
