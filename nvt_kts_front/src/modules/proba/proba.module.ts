import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProbaPageComponent } from './pages/proba-page/proba-page.component';
import { RouterModule } from '@angular/router';
import { routes } from './proba-routing';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ProbaPageComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
  ]
})
export class ProbaModule { }
