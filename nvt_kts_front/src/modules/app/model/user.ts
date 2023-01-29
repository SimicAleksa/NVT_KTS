export interface User{
    name: string;
    surname: string;
    email:string;
    picture: any;
    city: string;
    phone: string;
    note: string;
    blocked: boolean;
    tokens: number;
}

export interface ChangeProfileRequest {
    name: string;
    surname: string;
    email:string;
    picture: any;
    city: string;
    phone: string;
    carType: string;
    petsAllowed: boolean;
    babyAllowed: boolean; 
    password: string;
    note: string;
    blocked: boolean;
    tokens: number;
}

export interface ChangePassword {
    password: string;
    username: string;
}