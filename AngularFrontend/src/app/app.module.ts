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
import { ChatComponent } from './chat/chat.component';
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


const routes: Routes = [
  {
    path:'login',
    component:LoginComponent
  }, {
    path:'register',
    component:RegisterComponent
  }, {
    path:'',
    component:HomeComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'chat',
    component:ChatComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'profile',
    component:ProfileComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'sell',
    component:SellComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'profile/setting',
    component:SettingComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'product',
    component: ProductListComponent,
    canActivate:[CanActivateRouteGuard]
  }, {
    path:'admin',
    component: AdminComponent,
    canActivate:[CanActivateRouteGuard]
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
    ChatComponent,
    SellComponent,
    RegisterComponent,
    SettingComponent,
    AdminComponent,
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
