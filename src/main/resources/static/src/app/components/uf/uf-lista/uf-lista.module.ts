import angular from "angular";
import {UfListaComponent} from "./uf-lista.component";
import {UfService} from "../uf.service";
import "./uf-lista.scss";

export const UfListaModule = angular
  .module("uf.lista", [])
  .component("ufLista", UfListaComponent)
  .service("UfService", UfService)
  .name;
