import {UfItem} from "../../common/model/uf-item";
import {UfService} from "./uf.service";

export class UfController {
  static $inject: string[] = ['UfService'];
  ufs: UfItem[];
  newUf: UfItem;

  constructor(private ufService: UfService) {
  }

  $onInit() {
    this.newUf = new UfItem(null, '', '',null,null);
    this.ufs = [];
    this.updateLista();
  }

  $onChanges(changes) {
    this.updateLista();
  }

  updateLista() {
    this.ufService.getUfs().then(response => this.ufs = response);
  }

  editUf(ufData: { uf: UfItem }) {
    if (!ufData) return;
    this.newUf = ufData.uf;
  }

  deleteUf(ufData: { uf: UfItem }) {
    if (!ufData) return;
    this.ufService.deleteUf(ufData.uf.id).then(() => this.updateLista());
  }

  addUf(ufData: { uf: UfItem }) {
    if (!ufData) return;
    if (ufData.uf.id) {
      this.ufService.updateUf(ufData.uf.id, ufData.uf.nome, ufData.uf.sigla).then(() => this.updateLista());
    } else {
      this.ufService.createUf(ufData.uf.nome, ufData.uf.sigla).then(() => this.updateLista());
    }
    this.newUf = new UfItem(null, '', '', null, null);
  }
}
