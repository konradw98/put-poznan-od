import { Component, OnInit } from '@angular/core';
import { TopicModel } from 'src/app/topic/topic-response';
import { TopicService } from 'src/app/topic/topic.service';

@Component({
  selector: 'app-topic-side-bar',
  templateUrl: './topic-side-bar.component.html',
  styleUrls: ['./topic-side-bar.component.css']
})
export class TopicSideBarComponent implements OnInit {
   topics: Array<TopicModel>;
   displayViewAll: boolean; 

  constructor(private topicService: TopicService) {
    this.topicService.getAllTopics().subscribe(data => {
      if(data.length >= 4){
      this.topics = data.slice(0,3);
      this.displayViewAll = true; 
     } else {this.topics = data;}
    })
   }

  ngOnInit(): void {
  }

}
