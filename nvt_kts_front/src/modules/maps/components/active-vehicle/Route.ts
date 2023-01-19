import { Coord } from "./Coords";

export interface Route{
    routeJSON: string;
    startLocation: Coord;
    endLocation: Coord;
}