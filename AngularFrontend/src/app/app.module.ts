import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { MatCardModule} from '@angular/material/card';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ReactiveFormsModule,FormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SellComponent } from './sell/sell.component';
import { SettingComponent } from './setting/setting.component';
import { AdminComponent } from './admin/admin.component';
import { CanActivateRouteGuard } from './guards/can-activate-route.guard';
import { TokenInterceptor } from './TokenInterceptor';
import { RoleGuard } from './guards/role.guard';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ROLE } from './role.enum';

const routes: Routes = [
  {
    path:'login',
    component:LoginComponent
  }, {
    path:'register',
    component:RegisterComponent
  }, {
    path:'',
    component:HomeComponent
  },{
    path:'cart',
    component:CartComponent
  }, {
    path:'profile',
    component:ProfileComponent
  }, {
    path:'sell',
    component:SellComponent
  }, {
    path:'profile/setting',
    component:SettingComponent
  }, {
    path:'product',
    component: ProductListComponent
  }, {
    path:'admin',
<<<<<<< HEAD
    component: AdminComponent
=======
    component: AdminComponent,
    canActivate:[CanActivateRouteGuard, RoleGuard],
    data: {expectedRole: ROLE.ADMIN}
>>>>>>> 74716b7cb13b5a95d77f660f24f7db227c70f82a
  },{
    path: 'checkout',
    component: CheckoutComponent
  }

]

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    SellComponent,
    RegisterComponent,
    SettingComponent,
    AdminComponent,
    CartComponent,
    CheckoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatCardModule,
    RouterModule.forRoot(routes)
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
