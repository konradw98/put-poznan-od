import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopicModel } from '../topic-response';
import { TopicService } from '../topic.service';

@Component({
  selector: 'app-create-topic',
  templateUrl: './create-topic.component.html',
  styleUrls: ['./create-topic.component.css']
})
export class CreateTopicComponent implements OnInit {

  createTopicForm: FormGroup;
  topicModel: TopicModel;
  title = new FormControl('');
  description = new FormControl('');

  constructor(private router: Router, private topicService: TopicService) {
    this.createTopicForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    this.topicModel = {
      name: '',
      description: ''
    }
  }

  ngOnInit() {
  }

  discard() {
    this.router.navigateByUrl('/');
  }

  createTopic() {
    this.topicModel.name = this.createTopicForm.get('title').value;
    this.topicModel.description = this.createTopicForm.get('description').value;
    this.topicService.createTopic(this.topicModel).subscribe(data => {
      this.router.navigateByUrl('/list-topics');
    }, error => {
      console.log('Error occurred');
    })
  }
}


