import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: '',

    children: [
      {
        path: "maps",
        loadChildren: () => import("../maps/maps.module").then((m) => m.MapsModule),
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
      },
      {
        path: "charts",
        loadChildren: () =>
              import("../reports/reports.module").then((m) => m.ReportsModule),
      },
      {
        path: "sidebar",
        loadChildren: () =>
              import("../menu/menu.module").then((m) => m.MenuModule),
              // http://localhost:4200/sidebar/driver-rides
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
