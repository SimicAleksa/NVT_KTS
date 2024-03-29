import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-proba-page',
  templateUrl: './proba-page.component.html',
  styleUrls: ['./proba-page.component.css']
})
export class ProbaPageComponent implements OnInit {

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;

  constructor(private httpClient: HttpClient){}
  ngOnInit(): void {
  }

  public onFileChanged(event:any) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    console.log(this.selectedFile);
    
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('api/user/imgUploadPROBA', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
  }

  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
  //Make a call to Sprinf Boot to get the Image Bytes.
  this.httpClient.get('api/user/imgUploadPROBAGET')
    .subscribe(
      res => {
        console.log(res);
        this.retrieveResonse = res;
        this.base64Data = this.retrieveResonse['picture'];
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
      }
    );
  }
}
