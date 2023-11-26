import { ActivatedRoute } from '@angular/router';
import { PessoaService } from './../../services/pessoa.service';
import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/model/pessoa.model';
import { TelefoneService } from 'src/app/services/telefone.service';
import { TelefoneModel } from 'src/app/model/telefone.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit{

  pessoa?: Pessoa;
  telefone?: TelefoneModel[];
  showAdicionarContato = false;

  constructor(private pessoaService: PessoaService,
    private telefoneService: TelefoneService,
    private route: ActivatedRoute){

  }

  ngOnInit(): void {

    let idPessoa = this.route.snapshot.params["idPessoa"];
    this.pessoaService.getPessoasById(idPessoa).subscribe(response => {
      this.pessoa = response;
    });

    this.carregaContato();
  }

  private carregaContato(){

    let idPessoa = this.route.snapshot.params["idPessoa"];
    this.telefoneService.getTelefone(idPessoa).subscribe(response => {
      this.telefone = response;
    });
  }

  public mostrarAdicionarContato(){
    this.showAdicionarContato = true;
  }

  public atualizarContatos(){
    this.carregaContato();
  }
}
