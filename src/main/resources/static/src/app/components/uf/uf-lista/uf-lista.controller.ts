import {UfItem} from "../../../common/model/uf-item";
import {UfService} from "../uf.service";
import {Event} from "../../../common/event";

export class UfListaController {
  static $inject: string[] = ['UfService'];
  ufs: Array<UfItem>;
  onEditUf: (any);
  onDeleteUf: (any);

  constructor(private ufService: UfService) {
  }

  onButtonEditUF(ufSelect: UfItem) {
    if (!ufSelect.id) return;
    this.onEditUf(new Event({
      uf: Object.assign({}, ufSelect)
    }));
  }

  onButtonDeleteUF(ufSelect: UfItem) {
    if (!ufSelect.id) return;
    this.onDeleteUf(new Event({
      uf: ufSelect
    }));
  }

}
