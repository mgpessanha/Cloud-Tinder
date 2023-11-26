import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TelefoneModel } from '../model/telefone.model';

@Injectable({
  providedIn: 'root'
})
export class TelefoneService {

  constructor(private http: HttpClient) {}

  public getTelefone(idPessoa: any) : Observable <TelefoneModel[]>{
    return this.http.get<TelefoneModel[]>(`https://ibmec-ap1-2023.azurewebsites.net/pessoa/${idPessoa}/telefone`);
  }

  public createTelefone(idPessoa: any, telefone: TelefoneModel) : Observable <TelefoneModel>{
    return this.http.post<TelefoneModel>(`https://ibmec-ap1-2023.azurewebsites.net/pessoa/${idPessoa}/telefone`, telefone);
  }
}
