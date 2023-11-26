import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pessoa } from '../model/pessoa.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private httpClient: HttpClient) { }

  public getPessoas(): Observable<Pessoa[]> { // observable: observa a resposta do servidor
    return this.httpClient.get<Pessoa[]>("https://ibmec-ap1-2023.azurewebsites.net/pessoa");
  }
  public getPessoasById(id: any): Observable<Pessoa> {
    return this.httpClient.get<Pessoa>("https://ibmec-ap1-2023.azurewebsites.net/pessoa/" + id);
  }
}
