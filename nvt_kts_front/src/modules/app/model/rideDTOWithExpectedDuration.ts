import { Route } from "src/modules/maps/components/active-vehicle/Route"; 

export interface RideDtoWithExpectedDuration{
    id: number;
    route: Route;
    rideState: number;
    driver: number;
    expectedDuration:number;
}