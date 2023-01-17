export interface User{
    name: string;
    surname: string;
    email:string;
    picture: string;
    city: string;
    phone: string;
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
}