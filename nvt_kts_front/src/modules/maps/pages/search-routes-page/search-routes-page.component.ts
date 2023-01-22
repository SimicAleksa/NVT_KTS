import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MapLocation } from 'src/modules/app/model/mapLocation';
import { Coord } from '../../components/active-vehicle/Coords';
import { DataForRideForBack } from '../../components/active-vehicle/DataForRideForBACK';
import { Route } from '../../components/active-vehicle/Route';
import { MapService } from '../../services/map.service';

@Component({
  selector: 'app-search-routes-page',
  templateUrl: './search-routes-page.component.html',
  styleUrls: ['./search-routes-page.component.css']
})
export class SearchRoutesPageComponent implements OnInit {

  public selectedStartLocation!:MapLocation;
  public selectedEndLocation!:MapLocation;

  public routeSelectedBoolean:boolean = false;
  public selectedRouteEmitedValue:any;
  public ListOfRoutes = [];
  public routeToOrded:any;
  public priceOfRide:number = 0;
  public durationOfRide: number = 0;
  public distanceOfRide:number = 0;
  public durationOfRideString:string;
  public distanceOfRideString:string;
  public priceOfRideString:string;
  public routeToOrdedJSON:any;

  carTypes: string[] = ["SUV", "HATCHBACK", "COUPE", "MINIVAN", "SEDAN", "VAN", "LIMOUSINE"];

  constructor(private toastr: ToastrService, private mapService: MapService) { }

  ngOnInit(): void {
  }

  recieveSentSelectedStartLocation(emitedValue: MapLocation){
    this.selectedStartLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

  recieveSentSelectedEndLocation(emitedValue: MapLocation){
    this.selectedEndLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

  recieveRequestForRide(emitedValue: MapLocation){
    // this.selectedLocation = emitedValue;
    console.log("hvata na stranici");
    console.log(emitedValue);
  }

  recieveIsRouteSelected(recievedFromChild: boolean){
    this.routeSelectedBoolean = recievedFromChild;
  }

  recieveSelectedRoute(recievedFromChild: boolean){
    this.selectedRouteEmitedValue = recievedFromChild;
    this.ListOfRoutes = [];
    this.ListOfRoutes = this.ListOfRoutes.concat(this.selectedRouteEmitedValue.route);
    this.ListOfRoutes = this.ListOfRoutes.concat(this.selectedRouteEmitedValue.alternatives);
    this.routeToOrded = this.selectedRouteEmitedValue.route;
    this.variableReset();
    this.variableSetUp();
  }

  changeRidePref(radioType:any): void{
    // console.log(radioType.srcElement.id);
    let rType = radioType.srcElement.id
    if(rType==="shortest"){
      this.ridePrefFunctForRadio('totalDistance');
    }
    else if(rType ==="cheapest"){
      this.ridePrefFunctForRadio('totalDistance');
    }
    else if(rType ==="quickTime"){
      this.ridePrefFunctForRadio('totalTime');
    }
    else{
      this.routeToOrded = this.ListOfRoutes[0];
      this.variableReset();
      this.variableSetUp();
    }
  }

  variableReset():void{
    this.distanceOfRide = 0;
    this.durationOfRide = 0;
    this.priceOfRide = 0;
  }

  variableSetUp():void{
    this.durationOfRide = this.routeToOrded['summary']['totalTime'];
    var minutes = Math.floor(this.durationOfRide/60);
    var secunds = this.durationOfRide - minutes*60;
    this.durationOfRideString = minutes.toString()+"min "+secunds.toString().split('.')[0];

    this.distanceOfRide = this.routeToOrded['summary']['totalDistance'];
    var km = Math.floor(this.distanceOfRide/1000);
    var mms = this.distanceOfRide-km*1000;
    this.distanceOfRideString = km.toString()+","+mms.toString().split('.')[0];

    this.priceOfRide = this.distanceOfRide*0.12
    this.priceOfRideString = (this.distanceOfRide *0.12).toString().split('.')[0];

    this.routeToOrdedJSON = JSON.stringify(this.routeToOrded);
  }

  ridePrefFunctForRadio(additionalAttribute: string): void {
    this.variableReset();
    let lowest = Number.POSITIVE_INFINITY;
    let temp;
    for(let i=this.ListOfRoutes.length-1;i>=0;i--){
      temp = this.ListOfRoutes[i]['summary'][additionalAttribute];
      if(temp<lowest){
        this.routeToOrded = this.ListOfRoutes[i];
        lowest = temp;
      }
    }
    this.variableSetUp();
  }

  isCheckBoxChecked(checkBoxID:string): boolean{
    if((document.getElementById(checkBoxID) as HTMLInputElement).checked){
      return true;
    }
    return false;
  }

  handleCheckboxesForSentData(sendIT:DataForRideForBack):void{
    if(this.isCheckBoxChecked('pet')){
      sendIT.petAllowed = true;
    }
    if(this.isCheckBoxChecked('baby')){
      sendIT.babyAllowed = true;
    }
    if(this.isCheckBoxChecked('limousine')){
      sendIT.carTypes.push("LIMOUSINE");
    }
    if(this.isCheckBoxChecked('coupe')){
      sendIT.carTypes.push("COUPE");
    }
    if(this.isCheckBoxChecked('sedan')){
      sendIT.carTypes.push("SEDAN");
    }
    if(this.isCheckBoxChecked('van')){
      sendIT.carTypes.push("VAN");
    }
    if(this.isCheckBoxChecked('suv')){
      sendIT.carTypes.push("SUV");
    }
    if(this.isCheckBoxChecked('hatchback')){
      sendIT.carTypes.push("HATCHBACK");
    }
    if(this.isCheckBoxChecked('minivan')){
      sendIT.carTypes.push("MINIVAN");
    }
  }

  create_SENDIT():DataForRideForBack{
    var startLocation:Coord = {
      latitude: 0,
      longitude: 0
    }
    var endLocation:Coord = {
      latitude: 0,
      longitude: 0
    }
    var route:Route={
      routeJSON: "0",
      startLocation: startLocation,
      endLocation: endLocation
    }
    var sendIT:DataForRideForBack={
      carTypes:[],
      babyAllowed:false,
      petAllowed:false,
      distance: 0,
      route: route,
      duration: 0,
      price: 0
    }
    return sendIT;
  }

  routeSetUpForBack(sendIT:DataForRideForBack):void{
    sendIT.distance = this.distanceOfRide;
    sendIT.duration = this.durationOfRide;
    sendIT.price = this.priceOfRide;
  }

  startAndEndLocationForBack(sendIT:DataForRideForBack):void{
    sendIT.route.startLocation.latitude = this.routeToOrded['waypoints'][0]['latLng']['lat'];
    sendIT.route.startLocation.longitude = this.routeToOrded['waypoints'][0]['latLng']['lng'];
    sendIT.route.endLocation.latitude = this.routeToOrded['waypoints'][this.routeToOrded['waypoints'].length-1]['latLng']['lat'];
    sendIT.route.endLocation.longitude = this.routeToOrded['waypoints'][this.routeToOrded['waypoints'].length-1]['latLng']['lng'];
  }

  routeJsonSetUp(sendIT:DataForRideForBack):void{
    sendIT.route.routeJSON = this.routeToOrdedJSON
  }

  onSubmit(){
    var sendIT = this.create_SENDIT();
    this.handleCheckboxesForSentData(sendIT);
    this.routeSetUpForBack(sendIT);
    this.startAndEndLocationForBack(sendIT);
    this.routeJsonSetUp(sendIT);
    if(sendIT.carTypes.length===0){
      this.toastr.warning("At least one type of vehicle must be checked");
    }
    else{
      this.mapService.saveRide(sendIT);
    }
    console.log(sendIT);
  }
}
