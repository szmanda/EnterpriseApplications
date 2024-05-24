import { Component } from '@angular/core';
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {
  today = new Date(Date.now()).toString();
}
