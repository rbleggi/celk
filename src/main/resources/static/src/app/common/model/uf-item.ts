export class UfItem {
  constructor(
    public id: number,
    public nome: string,
    public sigla: string,
    public dataHoraAtualizacao: Date,
    public dataHoraCadastro: Date) {
  }
}
