import { Driver } from "./Driver";

export interface Ride{
    id: number;
    routeJSON: string;
    rideState: number;
    driver: Driver;
}