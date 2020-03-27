export class Pageable<T> {
  constructor(
    public content: Array<T>,
    public totalElements: number,
    public last: boolean,
    public totalPages: number,
    public size: number,
    public number: number,
    public numberOfElements: number,
    public first: boolean,
    public empty: boolean) { }
}
