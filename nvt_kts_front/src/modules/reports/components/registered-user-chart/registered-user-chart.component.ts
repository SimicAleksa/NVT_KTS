import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RideService } from '../../services/ride.service';
import { ReportParam } from 'src/modules/app/model/reportParams';

@Component({
  selector: 'app-registered-user-chart',
  templateUrl: './registered-user-chart.component.html',
  styleUrls: ['./registered-user-chart.component.css']
})
export class RegisteredUserChartComponent implements OnInit {

    dateForm: FormGroup;
    userEmail: string;
    data:Map<string, Map<string, number>>;

    myChartPrice: Chart;
    myChartDistance: Chart;
    myChartNumber: Chart;

  constructor(
    private rideService: RideService,
    private fb: FormBuilder
  ) {
        this.createForm();
        this.userEmail = "registrovani1@gmail.com";
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
      alert("Poslala sam formu");
      if (this.myChartDistance)
      {
        this.myChartDistance.destroy();
        this.myChartPrice.destroy();
        this.myChartNumber.destroy();
      }

      let l: ReportParam = {start: this.dateForm.get("start")?.value, end:this.dateForm.get("end")?.value, email:this.userEmail};
      this.rideService.getUserDataForReport(l).subscribe((response) => {
        const map = new Map(Object.entries(response));
        var mapAsc = new Map([...map.entries()].sort());   
        this.data = mapAsc; 
        // sad ubaci u labele
        const sum1 = this.getPrices().reduce((partialSum, a) => partialSum + a, 0);
        const sum2 = this.getDistances().reduce((partialSum, a) => partialSum + a, 0);
        const sum3 = this.getNumbers().reduce((partialSum, a) => partialSum + a, 0);
        let n = this.getLabels().length; 
        document.getElementById('priceLabel')!.innerHTML = 'Total money:' + sum1.toFixed(2) + "&nbsp &nbsp &nbsp &nbsp &nbsp Average: " + (sum1/n).toFixed(2);
        document.getElementById('distanceLabel')!.innerHTML = 'Total distance:' + sum2.toFixed(2) + "&nbsp &nbsp &nbsp &nbsp &nbsp Average: " + (sum2/n).toFixed(2);
        document.getElementById('numberLabel')!.innerHTML = 'Total number of rides:' + sum3.toFixed(2) + "&nbsp &nbsp &nbsp &nbsp &nbsp Average: " + (sum3/n).toFixed(2);

        this.myChartPrice = new Chart("myChartPrice", {
          type: 'bar',
          data: {
              labels: this.getLabels(),
              datasets: [{
                  label: '# money earned',
                  data: this.getPrices(),
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

      this.myChartDistance = new Chart("myChartDistance", {
        type: 'bar',
        data: {
            labels: this.getLabels(),
            datasets: [{
                label: '# distance travelled',
                data: this.getDistances(),
                backgroundColor: [
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(153, 102, 255, 1)'
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

    this.myChartNumber = new Chart("myChartNumber", {
      type: 'bar',
      data: {
          labels: this.getLabels(),
          datasets: [{
              label: '# number of rides',
              data: this.getNumbers(),
              backgroundColor: [
                  'rgba(255, 206, 86, 0.2)'
              ],
              borderColor: [
                  'rgba(255, 206, 86, 1)'
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
  

  getPrices(): number[] {
    let retval : number[] = [];
    this.data.forEach((value: Map<string, number>, key: string) => {
      let map:Map<string, number> = new Map(Object.entries(value));
      retval.push(map.get("price")!);
    });
      return retval;
  }

  getDistances(): number[] {
    let retval : number[] = [];
    this.data.forEach((value: Map<string, number>, key: string) => {
      let map:Map<string, number> = new Map(Object.entries(value));
      retval.push(map.get("distance")!);
    });
      return retval;
  }

  getNumbers(): number[] {
    let retval : number[] = [];
    this.data.forEach((value: Map<string, number>, key: string) => {
      let map:Map<string, number> = new Map(Object.entries(value));
      retval.push(map.get("num")!);
    });
      return retval;
  }

  getLabels(): string[] {
    let retval : string[] = [];
    this.data.forEach((value: Map<string, number>, key: string) => {
      retval.push(key);
    });
      return retval;
  }  

}
