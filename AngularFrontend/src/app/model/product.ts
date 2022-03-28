import { Time } from "@angular/common";

export class Product {
    pid?:number;
    pname?:string;
    condition?:string;
    brand?:string;
    description?:string;
    price?:number;
    images?:HTMLImageElement;
    postedBy?:string;
    postedAt?:Time;
}
