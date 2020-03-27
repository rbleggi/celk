import angular from 'angular';
import {UfComponent} from "./uf.component";
import {UfService} from "./uf.service";
import "./uf.scss";

export const UfModule = angular
  .module('uf', [])
  .component('celkUf', UfComponent)
  .service("UfService", UfService)
  .name;
