import { TelefoneModel } from './../model/telefone.model';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { TelefoneService } from '../services/telefone.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-telefone',
  templateUrl: './create-telefone.component.html',
  styleUrls: ['./create-telefone.component.css'],
  providers: []
  
})
export class CreateTelefoneComponent {

  contato = new FormControl('', [Validators.required]);
  codPais = new FormControl('', [Validators.required]);
  codArea = new FormControl('', [Validators.required]);
  @Output() newTelefoneEvent = new EventEmitter();
  @Input() idPessoa:any = '';

  constructor(private telefoneService: TelefoneService, private snackBar: MatSnackBar){

  }

  public adicionarNovoContato(){
    if(this.contato.hasError("required")){
      return;
    }

    if(this.codPais.hasError("required")){
      return;
    }

    if(this.codArea.hasError("required")){
      return;
    }

    let telefone: TelefoneModel = {
      codigoPais: this.codPais.value as string,
      codigoArea: this.codArea.value as string,
      numero: this.contato.value as string
    };

    this.telefoneService.createTelefone(this.idPessoa, telefone).subscribe(response => {
      this.snackBar.open("Número adicionado com sucesso", "Ok");
      this.newTelefoneEvent.emit();
    });
  }
}
