import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    
    children: [
      {
        path: "maps",
        loadChildren: () =>
              import("../maps/maps.module").then((m) => m.MapsModule),
      },
      {
        path: "user-data",
        loadChildren: () =>
              import("../user-data/user-data.module").then((m) => m.UserDataModule),
      },
      {
        path: "chat",
        loadChildren: () =>
              import("../chat/chat.module").then((m) => m.ChatModule),
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
