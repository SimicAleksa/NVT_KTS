import { Component, OnInit,VERSION } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MapLocation } from 'src/modules/app/model/mapLocation';
import { UserDataService } from 'src/modules/user-data/services/user-data.service';
import { Coord } from '../../components/active-vehicle/Coords';
import { DataForRideForBack } from '../../components/active-vehicle/DataForRideForBACK';
import { Route } from '../../components/active-vehicle/Route';
import { MapService } from '../../services/map.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-routes-page',
  templateUrl: './search-routes-page.component.html',
  styleUrls: ['./search-routes-page.component.css']
})
export class SearchRoutesPageComponent implements OnInit {

  public selectedStartLocation!:MapLocation;
  public selectedEndLocation!:MapLocation;

  public routeSelectedBoolean:boolean = false;
  public didUserAlreadyOrder:boolean;
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
  public addedAsFavorite:boolean = false;

  public now: Date = new Date();  //minimuim
  public temp1: Date = new Date(); //maksimum
  public minDate:string;
  public maxDate:string;
  public selectedDateTime:Date = new Date();
  public reservedCheckBoxChecked:boolean;
  public quickOrdedWithFavorite:string="";
  public quickOrdedWithFavoriteBoolean:boolean=false;

  public userEmail:string = "registrovani2@gmail.com";
  carTypes: string[] = ["SUV", "HATCHBACK", "COUPE", "MINIVAN", "SEDAN", "VAN", "LIMOUSINE"];

  public emails: string[];
  private selectedMails :string[]=[];

  constructor(private toastr: ToastrService, private mapService: MapService,
              private userService: UserDataService, private activRoute: ActivatedRoute) { 
    setInterval(() => {
      this.now = new Date();
    }, 1);
  }

  ngOnInit(): void {

    this.activRoute.queryParams.subscribe(params => {
      console.log(params['order']);
      this.quickOrdedWithFavorite = params['order'];
      if(this.quickOrdedWithFavorite!=="" && this.quickOrdedWithFavorite!==undefined){
          this.quickOrdedWithFavoriteBoolean=true;
          this.routeSelectedBoolean = true;
          this.whenFavoriteIsSelected();
      }
      else{
        this.quickOrdedWithFavoriteBoolean=false;
      }
    })
      






    this.minDate= this.now.toISOString().slice(0,16)
    this.maxDate = new Date(this.temp1.setHours(this.temp1.getHours()+5)).toISOString().slice(0,16)
    this.userService.getAllRegisteredUsersMails().subscribe((response) => {
      this.emails = <string[]>response;
      this.emails = this.emails.filter(x => x !== this.userEmail);
    });
      this.userService.getUsersStateBasedOnHisRides(this.userEmail).subscribe((response) => {
        this.didUserAlreadyOrder = response;
    });
  }

  saveSelectedFruit(e:any) {
    let fruitFromPage=e.target.value;
    this.emails = this.emails.filter(x => x !== fruitFromPage);
    this.tryToAddPassenger(String(fruitFromPage));
    this.deleteInnerHTML();
    
  }

  tryToAddPassenger(email: string)
  {
    if (this.selectedMails.length == 4)
    {
      this.showMessage();
    }
    else
    {
      this.selectedMails.push(String(email));
      this.createDiv(email);  // DODALA JA
      let msg = <HTMLInputElement> document.getElementById('maxLimitLbl');
      msg.classList.add("maxLimitLbl");
    }
  }


  showMessage()
  {
    let msg = <HTMLInputElement> document.getElementById('maxLimitLbl');
    msg.classList.remove("maxLimitLbl");
  }


  deleteInnerHTML()
  {
    let input = <HTMLInputElement> document.getElementById('addLinkedInput');
    input.value = "";
  }

  createDiv(value: string) {
    let label = document.createElement('label');
    label.setAttribute('class', 'locationName');
    label.innerHTML = value;

    let button = document.createElement('button');
    button.setAttribute( 'type', 'button');
    button.setAttribute('class', "btn-close");
    button.setAttribute('area-label', "Close");
    button.setAttribute("style", "width: 7px; height: 7px; padding-bottom: 0px; margin-bottom: 5px; margin-left: 5px;")
    let that = this;
    button.addEventListener("click", function(){that.deleteDiv(value)}, false);

    let innerDiv = document.createElement('div');
    innerDiv.appendChild(label);
    innerDiv.appendChild(button);
    let outerDiv = document.createElement('div');
    outerDiv.setAttribute("class", "selectedStart row");
    outerDiv.setAttribute("id", value);
    outerDiv.setAttribute("style", "width: max-content; height:20px; border-radius: 8px; background-color: white;padding-left: 5px;margin: 5px; padding-top:2px")
    outerDiv.appendChild(innerDiv);
    let containingDiv = document.getElementById('addLinkedForm'); // DODALA JA
    containingDiv?.appendChild(outerDiv);
  }

  deleteDiv(divId: string)
  {
    var element = document.getElementById(divId);
    element?.parentNode?.removeChild(element);

    this.emails.push(divId)
    const index = this.selectedMails.indexOf(divId);
    this.selectedMails.splice(index, 1);
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

  whenFavoriteIsSelected(){
    this.routeToOrded = //poziv naci u bazi taj nroute id ILI samo proslediti iz drugog dela
    this.mapService.getUsersFavoriteRouteWithId(this.quickOrdedWithFavorite).subscribe((response) => {
      this.routeToOrded = JSON.parse(response.routeJSON); 
      console.log(JSON.parse(response.routeJSON))
      this.variableReset();
      this.variableSetUp();
    });
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
      price: 0,
      reservedTime:"",
      linkedPassengers: this.selectedMails,
      favoriteBoolean:false,
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

  isValideDateTimeSelected():boolean{
    if(this.now>new Date(this.selectedDateTime)){
      return false;
    }
    if(this.temp1<new Date(this.selectedDateTime)){
      return false;
    }
    return true;
  }

  reservedCheckBoxCheckedFinction():void{
    if(this.reservedCheckBoxChecked)
      this.reservedCheckBoxChecked = false;
    else
      this.reservedCheckBoxChecked = true;
  }

  setUpReservedTime(sendIT:DataForRideForBack):void{
      if(this.reservedCheckBoxChecked)
        sendIT.reservedTime = this.selectedDateTime.toString();
      else
        sendIT.reservedTime = "";
  }

  onSubmit(){
    var sendIT = this.create_SENDIT();
    this.handleCheckboxesForSentData(sendIT);
    this.routeSetUpForBack(sendIT);
    this.startAndEndLocationForBack(sendIT);
    this.routeJsonSetUp(sendIT);
    this.setUpReservedTime(sendIT);

    if(sendIT.carTypes.length===0){
      this.toastr.warning("At least one type of vehicle must be checked");
    }
    else if(!this.isValideDateTimeSelected() && this.reservedCheckBoxChecked){
      this.toastr.warning("Invalid date or time selected!");
      this.toastr.warning("You can only reserve 5h upfront");
    }
    else{
      sendIT.linkedPassengers.push(this.userEmail);
      sendIT.favoriteBoolean = this.addedAsFavorite;
      this.mapService.saveRide(sendIT);

      sendIT.linkedPassengers.pop();
      this.routeSelectedBoolean = false;
      this.userService.getUsersStateBasedOnHisRides(this.userEmail).subscribe((response) => {
        this.didUserAlreadyOrder = response;
      });
      this.toastr.success("Successfully ordered");
    }
    console.log(sendIT);
  }

  addToFavoriteRoute(){
    if(this.addedAsFavorite){
      this.addedAsFavorite = false;
    }
    else{
      this.addedAsFavorite = true;
    }
  }
}
