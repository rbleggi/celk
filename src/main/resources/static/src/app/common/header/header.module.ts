import angular from "angular";
import { HeaderComponent } from "./header.component";
import "./header.scss";

export const HeaderModule = angular
  .module("header", [])
  .component("celkHeader", HeaderComponent).name;
