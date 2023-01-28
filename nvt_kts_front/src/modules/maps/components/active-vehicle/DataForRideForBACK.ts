import { Route } from "./Route";

export interface DataForRideForBack{
    carTypes:string[];
    babyAllowed:boolean;
    petAllowed:boolean;
    distance: number;
    route: Route;
    duration: number;
    price: number;
    reservedTime:string;
    linkedPassengers: string[];
    favoriteBoolean:boolean;
}