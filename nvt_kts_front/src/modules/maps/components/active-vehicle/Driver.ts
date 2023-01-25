import { Coord } from "./Coords";

export interface Driver{
    id: number;
    currentCoords: Coord;
    licensePlateNumber: string;
}