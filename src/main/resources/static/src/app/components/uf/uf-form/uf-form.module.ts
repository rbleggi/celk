import angular from 'angular';
import {UfFormComponent} from "./uf-form.component";
import {UfService} from "../uf.service";
import "./uf-form.scss";

export const UfFormModule = angular
  .module('uf.form', [])
  .component('ufForm', UfFormComponent)
  .service("UfService", UfService)
  .name;
