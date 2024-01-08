import {Component, OnInit} from '@angular/core';
import {HttpClientModule} from "@angular/common/http";
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {CinemaService} from "../services/cinema.service";
@Component({
  imports: [HttpClientModule, NgIf, NgForOf, NgClass],
  selector: 'app-cinema',
  standalone: true,
  styleUrl: './cinema.component.css',
  templateUrl: './cinema.component.html'
})
export class CinemaComponent  implements OnInit {
  public villes: any;
  public cinemas: any;
  public currentVille: any;
  public currentCinema: any;
  public salles: any;
  constructor(protected cinemaService:CinemaService) { }
  ngOnInit() {
    this.cinemaService.getVilles()
      .subscribe(data=>{
        this.villes=data;
      },err=>{
          console.log(err);
      })
  }
  onGetCinemas(v: any) {
    this.currentVille=v;
    this.cinemaService.getCinemas(v)
      .subscribe(data=>{
        this.cinemas=data;
      },err=>{
        console.log(err);
      })
  }
  onGetSalles(c: any) {
    this.currentCinema=c;
    this.cinemaService.getSalles(c)
      .subscribe(data=>{
        this.salles=data;
        this.salles._embedded.salles.forEach((salle: any)=>{
          this.cinemaService.getProjection(salle)
            .subscribe(data=>{
              salle.projections=data;
            },err=>{
              console.log(err);
            })
        })
      },err=>{
        console.log(err);
        }
      )
  }
}
