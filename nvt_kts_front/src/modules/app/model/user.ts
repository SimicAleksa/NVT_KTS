export interface User{
    name: string;
    surname: string;
    email:string;
    picture: string;
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
    picture: string;
    city: string;
    phone: string;
    carType: string;
    petAllowed: boolean;
    babyAllowed: boolean; 
    password: string;
    note: string;
    blocked: boolean;
    tokens: number;
}

export interface ChangePassword {
    username: string;
    new: string;
    old: string;
}