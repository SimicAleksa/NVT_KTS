import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LoginFormComponent } from './login-form.component';
import { APIRequestMaker } from 'src/utils/api-request-maker';
import { FacebookLoginProvider, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';

describe('LoginFormComponent', () => {
  let component: LoginFormComponent;
  let fixture: ComponentFixture<LoginFormComponent>;
  let reqService: APIRequestMaker;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginFormComponent ],
      imports: [HttpClientTestingModule], 
      providers: [
        {
          provide: 'SocialAuthServiceConfig',
          useValue: {
            autoLogin: false,
            providers: [],
            onError: (err) => {}
          } as SocialAuthServiceConfig,
        },
        APIRequestMaker
      ]
    });
    httpController = TestBed.inject(HttpTestingController);
    reqService = TestBed.inject(APIRequestMaker);
    fixture = TestBed.createComponent(LoginFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  afterEach(() => {
    httpController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit error if at least one of inputs are empty', () => {
    component.email = "email@gmail.com";
    component.password = "";

    spyOn(component, 'onLoginBtnClick').and.callThrough();
    spyOn(component, 'emitError').and.callThrough();
    
    component.onLoginBtnClick();

    expect(component.onLoginBtnClick).toHaveBeenCalled();
    expect(component.emitError).toHaveBeenCalled();
  });

  it('should emit error if one of inputs are of invalid format', () => {
    component.email = "invalid mail";
    component.password = "password";

    spyOn(component, 'onLoginBtnClick').and.callThrough();
    spyOn(component, 'emitError').and.callThrough();
    
    component.onLoginBtnClick();

    expect(component.onLoginBtnClick).toHaveBeenCalled();
    expect(component.emitError).toHaveBeenCalled();
  });
  
  it('should emit error if form inputs are valid and user is NOT found', () => {
    component.email = "validEmailButUserDoesntExist@gmail.com";
    component.password = "password";

    spyOn(component, 'onLoginBtnClick').and.callThrough();
    spyOn(component.reqMaker, 'createLoginRequest').and.callThrough();
    spyOn(component, 'emitError').and.callThrough();
    
    component.onLoginBtnClick();

    expect(component.onLoginBtnClick).toHaveBeenCalled();
    expect(component.reqMaker.createLoginRequest).toHaveBeenCalled();
    
    httpController.expectOne({
      method: 'POST',
      url: "http://localhost:8000/api/unauth/login",
    }).flush('LOCKED', {status: 401, statusText: 'LOCKED'});

    expect(component.emitError).toHaveBeenCalled();

  });

  it('should emit error if form inputs are valid and user is BLOCKED or NOT ACTIVE', () => {
    component.email = "validEmailButUserDoesntExist@gmail.com";
    component.password = "password";

    spyOn(component, 'onLoginBtnClick').and.callThrough();
    spyOn(component.reqMaker, 'createLoginRequest').and.callThrough();
    spyOn(component, 'emitError').and.callThrough();
    
    component.onLoginBtnClick();

    expect(component.onLoginBtnClick).toHaveBeenCalled();
    expect(component.reqMaker.createLoginRequest).toHaveBeenCalled();
    
    httpController.expectOne({
      method: 'POST',
      url: "http://localhost:8000/api/unauth/login",
    }).flush('LOCKED', {status: 423, statusText: 'LOCKED'});

    expect(component.emitError).toHaveBeenCalled();

  });

  it('should call redirect method if form inputs are valid and user is found', () => {
    let mockedRetData = { 
      accessToken: "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiVVNFUiIsImlzcyI6InNwcmluZy1zZWN1cml0eS1leGFtcGxlIiwic3ViIjoic3RyYWhpbmphcG9wb3ZpYy5ldmlscG9wc0BnbWFpbC5jb20iLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2NzQ2NzYwMTUsImV4cCI6MTY3NDY5NDAxNX0.jQhG4cIPo7qC-CA7QJ2jFDUanhu1LO-aKcFPwDm3r3i7M3Ls-zSQkrrwpIPyZqAXLE6PbaBw4Nf1x_xFTL7EWw", 
      role: "someRole"
    };
    component.email = "validEmailButUserDoesntExist@gmail.com";
    component.password = "password";

    spyOn(component, 'onLoginBtnClick').and.callThrough();
    spyOn(component.reqMaker, 'createLoginRequest').and.callThrough();
    spyOn(component, "redirectAfterLogin").and.callFake(() => {});
    
    component.onLoginBtnClick();

    expect(component.onLoginBtnClick).toHaveBeenCalled();
    expect(component.reqMaker.createLoginRequest).toHaveBeenCalled();
    
    httpController.expectOne({
      method: 'POST',
      url: "http://localhost:8000/api/unauth/login",
    }).flush(mockedRetData);

    expect(component.redirectAfterLogin).toHaveBeenCalled();
  });

});
