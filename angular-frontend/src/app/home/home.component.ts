import { Component, OnInit } from '@angular/core';
import { PostModel } from '../shared/post-model';
import { PostService } from '../shared/post.service';
//import {faComments} from '@fort'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 // faComments = faComments; 
 // posts$: Array<PostModel> = [];

  constructor( ) {
    
  }

  ngOnInit(): void {
  }

}
