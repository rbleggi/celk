import angular from "angular";
import {UfFormModule} from "./components/uf/uf-form/uf-form.module";
import {UfListaModule} from "./components/uf/uf-lista/uf-lista.module";
import {UfModule} from "./components/uf/uf.module";

export const ComponentsModule = angular.module("app.components", [
  UfFormModule,
  UfListaModule,
  UfModule,
]).name;
