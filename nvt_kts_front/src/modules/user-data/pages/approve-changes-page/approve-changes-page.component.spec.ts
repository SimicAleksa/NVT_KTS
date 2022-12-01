import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveChangesPageComponent } from './approve-changes-page.component';

describe('ApproveChangesPageComponent', () => {
  let component: ApproveChangesPageComponent;
  let fixture: ComponentFixture<ApproveChangesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveChangesPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApproveChangesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
