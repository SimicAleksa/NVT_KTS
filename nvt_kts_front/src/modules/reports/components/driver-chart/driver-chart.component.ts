import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Chart, registerables } from 'chart.js';
import { ReportParam } from 'src/modules/app/model/reportParams';
import { RideService } from '../../services/ride.service';
Chart.register(...registerables);

@Component({
  selector: 'app-driver-chart',
  templateUrl: './driver-chart.component.html',
  styleUrls: ['./driver-chart.component.css']
})
export class DriverChartComponent implements OnInit {

    dateForm: FormGroup;
    userEmail: string;
    data:Map<string, Map<string, number>>;
  
    constructor(
      private rideService: RideService,
      private fb: FormBuilder) { 
        this.createForm();
        this.userEmail = "pera@gmail.com";
    }

    createForm() {
      this.dateForm = this.fb.group({
        start: ["", [Validators.required]],
        end: ["", [Validators.required]],
      })
    }
  
    ngOnInit(): void {
      
    }
    onSubmit()
    {
      let l: ReportParam = {start: this.dateForm.get("start")?.value, end:this.dateForm.get("end")?.value, email:this.userEmail};
      this.rideService.getDriverDataForReport(l).subscribe((response) => {
        const map = new Map(Object.entries(response));
        var mapAsc = new Map([...map.entries()].sort());   
        this.data = mapAsc; 

        var myChart = new Chart("myChart", {
          type: 'bar',
          data: {
              labels: this.getLabels(),
              datasets: [{
                  label: '# money earned',
                  data: [12, 19, 3, 5],
                  backgroundColor: [
                      'rgba(54, 162, 235, 0.2)'
                  ],
                  borderColor: [
                      'rgba(54, 162, 235, 1)'
                  ],
                  borderWidth: 1
              }]
          },
          options: {
              scales: {
                  y: {
                      beginAtZero: true
                  }
              }
          }
      });

      });

      

      
    }

  getLabels(): string[] {
    let retval : string[] = [];
    this.data.forEach((value: Map<string, number>, key: string) => {
      retval.push(key);
  });
    return retval;
  }

  }
  