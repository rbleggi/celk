import {IHttpResponse} from "angular";
import {Pageable} from "../../common/model/pageable";
import {UfItem} from "../../common/model/uf-item";
import Swal from 'sweetalert2/dist/sweetalert2.js'
import 'sweetalert2/src/sweetalert2.scss'

export class UfService {
  static $inject: string[] = ['$http'];

  constructor(private $http: angular.IHttpService) {
  }

  createUf(nome: string, sigla: string) {
    return this.$http
      .post("/api/uf/v1/?nome=" + nome + "&sigla=" + sigla, null)
      .then(response => response.data)
      .catch(response => {
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Erro!',
          text: response.data.message,
          showConfirmButton: false,
          timer: 3000
        })
      });
  }

  updateUf(id: number, nome: string, sigla: string) {
    return this.$http
      .put("/api/uf/v1/" + id + "?nome=" + nome + "&sigla=" + sigla, null)
      .then((response) => response.data)
      .catch(response => {
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Erro!',
          text: response.data.message,
          showConfirmButton: false,
          timer: 3000
        })
      });
  }

  getUf(id: number) {
    return this.$http
      .get("/api/uf/v1/" + id)
      .then(response => response.data)
      .catch(response => {
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Erro!',
          text: response.data.message,
          showConfirmButton: false,
          timer: 3000
        })
      });
  }

  getUfs() {
    return this.$http
      .get("/api/uf/v1/query")
      .then((response: IHttpResponse<Pageable<UfItem>>) => response.data.content)
      .catch(response => {
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Erro!',
          text: response.data.message,
          showConfirmButton: false,
          timer: 3000
        });
        return UfItem[0];
      });
  }

  deleteUf(id: number) {
    return this.$http
      .delete("/api/uf/v1/" + id)
      .then(response => response.data)
      .catch(response => {
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Erro!',
          text: response.data.message,
          showConfirmButton: false,
          timer: 3000
        })
      });
  }
}
