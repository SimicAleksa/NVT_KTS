import { Driver } from "./Driver";
import { Route } from "./Route";

export interface Ride{
    id: number;
    route: Route;
    rideState: number;
    driver: number;
}