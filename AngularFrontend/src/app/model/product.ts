import { Time } from "@angular/common";
import { Byte } from "@angular/compiler/src/util";

export class Product {
    prodId!:number;
    pname!: string;
    condition?:string;
    brand?:string;
    desc?:string;
    price!:number;
    postedBy?:string;
    date?:Date;
    imageUrl?: null;
    image!: [Byte];
}

