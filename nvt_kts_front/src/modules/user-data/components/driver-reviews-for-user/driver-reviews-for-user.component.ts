import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { RatingChangeEvent } from 'angular-star-rating';
import { FieldValidator } from 'src/utils/field-validator';
import { APIRequestMaker } from '../../../../utils/api-request-maker';
@Component({
  selector: 'app-driver-reviews-for-user',
  templateUrl: './driver-reviews-for-user.component.html',
  styleUrls: ['./driver-reviews-for-user.component.css'],
})
export class DriverReviewsForUserComponent implements OnInit {
  @Output() errSignal = new EventEmitter<string>();
  @Output() successSignal = new EventEmitter<string>();
 
  public reviews: Array<any>;
 
  public showPanel: boolean;
  public newReviewMess: string;
  public newCarRating: number;
  public newDriverRating: number;

  private driverId: number;

  constructor(private reqMaker: APIRequestMaker, private fieldvalidator: FieldValidator) {
    this.reviews = new Array;
   
    this.showPanel = false;
    this.newReviewMess = "";
    this.newCarRating = 0;
    this.newDriverRating = 0;

    this.driverId = -1;
  }

  ngOnInit(): void {
  }

  fetchReviews(driverId: number): void {
    this.driverId = driverId;
    this.reqMaker.createDriverReviewsRequest(this.driverId).subscribe(this.getDriverReviewsObservable());
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
    let data = {
      carStars: this.newCarRating,
      driverStars: this.newDriverRating,
      comment: this.newReviewMess,
      driverId: this.driverId
    };

    if (!this.validateInputData())
      return;
  
    this.reqMaker.createNewReviewRequest(data).subscribe(this.getNewReviewObservable());
  } 

  getNewReviewObservable() {
    return {
      error: (err: any) => {
        if (err.status === 422)
          this.emitError("You cannot post a review if you did not have any ride with this driver in last 3 days!");
      },
      complete: () => {
        this.newReviewMess = "";
        this.newCarRating = 0;
        this.newDriverRating = 0;
        this.emitSuccess();
        this.fetchReviews(this.driverId);
      }
    }
  }

  emitError(errMessage: string): boolean {
    this.errSignal.emit(errMessage);
    return false;
  }

  emitSuccess() {
    this.successSignal.emit("Review has been successfully posted!");
  }

  validateInputData(): boolean {
    if (this.fieldvalidator.isEmpty(this.newReviewMess) || !this.fieldvalidator.isNonZero(this.newCarRating) || !this.fieldvalidator.isNonZero(this.newDriverRating))
      return this.emitError("Please make sure you entered some comment and rated driver and his car.");
    return true;
  }
}
