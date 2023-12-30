import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNoteComponent } from './add-note/add-note.component';
import { authGuard } from './auth.guard';
import { UserLoginComponent } from './user-login/user-login.component';
import { ShowRecentNotesComponent } from './show-recent-notes/show-recent-notes.component';
import { UserSignupComponent } from './user-signup/user-signup.component';

const routes: Routes = [
  { path: 'signup', component: UserSignupComponent },
  { path: '', component: UserLoginComponent },
  { path: 'notes', component: ShowRecentNotesComponent, canActivate: [authGuard] },
  { path: 'addNote', component: AddNoteComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
