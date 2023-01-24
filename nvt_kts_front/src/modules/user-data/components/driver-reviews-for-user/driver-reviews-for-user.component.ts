import { Component, OnInit } from '@angular/core';
import { RatingChangeEvent } from 'angular-star-rating';
import { APIRequestMaker } from '../../../../utils/api-request-maker';

@Component({
  selector: 'app-driver-reviews-for-user',
  templateUrl: './driver-reviews-for-user.component.html',
  styleUrls: ['./driver-reviews-for-user.component.css']
})
export class DriverReviewsForUserComponent implements OnInit {
  public reviews: Array<any>;
 
  public showPanel: boolean;
  public newReviewMess: string;
  public newCarRating: number;
  public newDriverRating: number;

  constructor(private reqMaker: APIRequestMaker) {
    this.reviews = new Array;
   
    this.showPanel = false;
    this.newReviewMess = "";
    this.newCarRating = 0;
    this.newDriverRating = 0;
  }

  ngOnInit(): void {
  }

  fetchReviews(driverId: number): void {
    this.reqMaker.createDriverReviewsRequest(driverId).subscribe(this.getDriverReviewsObservable());
  }

  getDriverReviewsObservable() {
    return {
      next: (retData: any) => {
        this.reviews = retData.body;
      },
      error: (err: any) => {
        this.reviews = [];
      },
      complete: () => {
        this.showPanel = true;
      }
    }
  }

  onCarRatingChange(newRating: RatingChangeEvent): void {
    this.newCarRating = newRating.rating;
  }

  onDriverRatingChange(newRating: RatingChangeEvent): void {
    this.newDriverRating = newRating.rating;
  }

  submitReview(): void {
    
  } 

}
