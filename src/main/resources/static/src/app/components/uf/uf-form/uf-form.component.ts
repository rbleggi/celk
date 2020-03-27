import {UfFormController} from './uf-form.controller';

export const UfFormComponent: angular.IComponentOptions = {
  bindings: {
    uf: '<',
    onAddUf: '&'
  },
  controller: UfFormController,
  template: require("./uf-form.html")
};
