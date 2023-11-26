export interface Pessoa {
  id?:Number; // a interrogação é para sinalizar que o campo pode vir vazio, mesmo que alguns sejam obrigatórios
  nome?:String;
  cpf?:String;
  dtNascimento?:Date;
  bio?:String;
  urlImage?:String;
}
