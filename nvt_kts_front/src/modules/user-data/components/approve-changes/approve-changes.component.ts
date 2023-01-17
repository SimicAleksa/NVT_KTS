import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approve-changes',
  templateUrl: './approve-changes.component.html',
  styleUrls: ['./approve-changes.component.css']
})
export class ApproveChangesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  approve()
  {
    alert("Odobrila sam promjene");
  }

  decline()
  {
    alert("Odbacila sam promjene");
  }

}
