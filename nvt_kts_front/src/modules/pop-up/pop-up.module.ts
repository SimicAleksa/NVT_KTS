import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopUpComponent } from './component/pop-up/pop-up.component';



@NgModule({
  declarations: [
    PopUpComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    PopUpComponent
  ]
})
export class PopUpModule { }
