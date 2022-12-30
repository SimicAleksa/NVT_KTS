import { Injectable } from '@angular/core';


@Injectable({ providedIn: 'root' })
export class FieldValidator {
    private emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    
    constructor() { }

    isEmpty(value: string): boolean {
        return value === null || value === undefined || value === "";
    }

    isLenBetween(value: string, start: number, end: number): boolean {
        return value.length >= start && value.length <= end;
        
    }

    isLenGraterThan(value: string, num: number): boolean {
        return value.length >= num;
    }

    isLenLesserThan(value: string, num: number): boolean {
        return value.length <= num;

    }

    isOfExactLength(value: string, num: number): boolean {
        return value.length === num;

    }

    validateEmail(email: string): boolean {
        return this.emailRegex.test(email);
    }

}