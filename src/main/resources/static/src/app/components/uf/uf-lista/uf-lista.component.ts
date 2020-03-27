import {UfListaController} from "./uf-lista.controller";

export const UfListaComponent: angular.IComponentOptions = {
  bindings: {
    ufs: '<',
    uf: '<',
    onEditUf: '&',
    onDeleteUf: '&'
  },
  controller: UfListaController,
  template: require("./uf-lista.html")
};
