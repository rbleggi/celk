import {Event} from "../../../common/event";
import {UfItem} from "../../../common/model/uf-item";
import {UfService} from "../uf.service";

export class UfFormController {
  static $inject = ['UfService'];
  uf: UfItem;
  onAddUf: (any);

  constructor() {
  }

  onSubmit() {
    if (!this.uf.nome) return;
    this.onAddUf(new Event({
      uf: this.uf
    }));
  }
}
