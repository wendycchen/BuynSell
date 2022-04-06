import {Product} from "./product";

export class CartItems {

  id: number;
  productId: number;
  productName: string;
  qty: number;
  price: number;

  constructor(id: number, product: Product, qty = 1) {
    this.id = id;
    this.productId = product.prodId;
    this.productName = product.pname;
    this.qty = qty;
    this.price = product.price;
  }

}
