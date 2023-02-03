import { Injectable } from '@angular/core';
import { ChangeProfileRequest } from 'src/modules/app/model/user';

@Injectable()
export class UserDataServiceMock {
  constructor() { }

  getUsers(): Array<{}> {
      return [
          {
              name: 'user1',
              surname: 'usersurname1'
          }
      ];
  }

  public dumbMethod()
  {
    return "Nevena";
  }

  checkIfAlreadyExists(email: string) {
    return true;
  }

  addUser(user: ChangeProfileRequest) {
    
  }

  sendRegistrationEmail(email: string) {
   
  }
}
