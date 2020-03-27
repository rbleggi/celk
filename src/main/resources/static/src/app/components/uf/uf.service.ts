import {IHttpResponse} from "angular";
import {Pageable} from "../../common/model/pageable";
import {UfItem} from "../../common/model/uf-item";

export class UfService {
  static $inject: string[] = ['$http'];

  constructor(private $http: angular.IHttpService) {
  }

  createUf(nome: string, sigla: string) {
    return this.$http
      .post("/api/uf/v1/?nome=" + nome + "&sigla=" + sigla, null)
      .then(response => response.data);
  }

  updateUf(id: number, nome: string, sigla: string) {
    return this.$http
      .put("/api/uf/v1/" + id + "?nome=" + nome + "&sigla=" + sigla, null)
      .then((response) => response.data);
  }

  getUf(id: number) {
    return this.$http
      .get("/api/uf/v1/" + id)
      .then(response => response.data);
  }

  getUfs() {
    return this.$http
      .get("/api/uf/v1/query")
      .then((response: IHttpResponse<Pageable<UfItem>>) => response.data.content);
  }

  deleteUf(id: number) {
    return this.$http
      .delete("/api/uf/v1/" + id)
      .then(response => response.data);
  }
}
