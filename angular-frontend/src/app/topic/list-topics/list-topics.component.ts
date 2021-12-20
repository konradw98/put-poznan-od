import { Component, OnInit } from '@angular/core';

import { throwError } from 'rxjs';
import { TopicModel } from '../topic-response';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-list-topics',
  templateUrl: './list-topics.component.html',
  styleUrls: ['./list-topics.component.css']
})
export class ListTopicsComponent implements OnInit {

  topics: Array<TopicModel>;
  constructor(private topicService: TopicService) { }

  ngOnInit() {
    this.topicService.getAllTopics().subscribe(data => {
      this.topics = data;
    }, error => {
      throwError(error);
    })
  }
}