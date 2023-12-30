import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {

  const token = localStorage.getItem('jwtToken');

  if (token) {

    return true;
  } else {


    return false;
  }

};