import angular from "angular";
import { HeaderModule } from "./header/header.module";
import { FooterModule } from "./footer/footer.module";

export const CommonModule = angular.module("app.common", [
  HeaderModule,
  FooterModule
]).name;
