import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTelefoneComponent } from './create-telefone.component';

describe('CreateTelefoneComponent', () => {
  let component: CreateTelefoneComponent;
  let fixture: ComponentFixture<CreateTelefoneComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateTelefoneComponent]
    });
    fixture = TestBed.createComponent(CreateTelefoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
