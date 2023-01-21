import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockUserPageComponent } from './block-user-page.component';

describe('BlockUserPageComponent', () => {
  let component: BlockUserPageComponent;
  let fixture: ComponentFixture<BlockUserPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlockUserPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlockUserPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
