import angular from "angular";
import {UfFormModule} from "./uf/uf-form/uf-form.module";
import {UfListaModule} from "./uf/uf-lista/uf-lista.module";
import {UfModule} from "./uf/uf.module";

export const ComponentsModule = angular.module("app.components", [
  UfFormModule,
  UfListaModule,
  UfModule
]).name;
