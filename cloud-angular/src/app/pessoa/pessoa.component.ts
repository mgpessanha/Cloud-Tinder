import { Router } from '@angular/router';
import { Pessoa } from '../model/pessoa.model';
import { PessoaService } from './../services/pessoa.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit{
  pessoas: Pessoa[] = [];

  constructor(private pessoaService: PessoaService, private router: Router) {

  }

  ngOnInit(): void { // quando o componente está inicializando
    this.pessoaService.getPessoas().subscribe(response => { // chama o backend
      this.pessoas = response;
    })
  }

  redirectToDetail(id: any) {
    this.router.navigate(["detail", id]);
}


  /* CICLO DE VIDA DO COMPONENTE DO ANGULAR

  ngBeforeViewInit(): void { // antes da view/ html aparecer //

  }
  ngAfterViewInit(): void { // depois que o html/ view já apareceu //

  }
  ngChanges(): void { // toda vez que precisa atualizar o html //

  }
  ngOnDestroy(): void { // quando sai do html e destroi os componentes //

  }
  */
}

