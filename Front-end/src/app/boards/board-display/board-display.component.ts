import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Board} from '../board.model';
import {BoardsService} from '../boards.service';
import {BoardsRestService} from '../boards-rest.service';
import {Interest} from '../../interest/interest.model';
import {InterestsService} from '../../interest/interests.service';
import {Subscription} from 'rxjs/Subscription';
import {InterestsRestService} from '../../interest/interests.rest.service';

@Component({
  selector: 'app-board-display',
  templateUrl: './board-display.component.html',
  styleUrls: ['./board-display.component.css']
})
export class BoardDisplayComponent implements OnInit, OnDestroy {

  board: Board;
  id: number;
  interests: Interest[];
  interestsNames: string[];
  subscritionInterests: Subscription;
  // refreshInterval: number;

  constructor(private boardsService: BoardsService,
              private boardsRestService: BoardsRestService,
              private route: ActivatedRoute,
              private router: Router,
              private interestsService: InterestsService,
              private interestsRestService: InterestsRestService) { }

  ngOnInit() {
    this.interests = new Array<Interest>();
    this.interestsNames = new Array<string>();

    this.subscritionInterests = this.interestsService.interestsChanged
      .subscribe((interests: Interest[]) => {
        this.interests = interests;
      });

    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];
          this.board = this.boardsService.getBoard(this.id);

          this.interests.splice(0, this.interests.length);
          this.interestsNames.splice(0, this.interestsNames.length);
          for (const interestName of this.board.interests){
            this.interestsNames.push(interestName);
          }
          this.interestsService.setInterestsNamesList(this.interestsNames);

          setTimeout( () => {
            this.updateContents();
          }, 1000);
          setTimeout( () => {
            this.updateContents();
          }, 6000);
          setInterval( () => {
            this.updateContents();
          }, 60000);
        }
      );
    // this.refreshInterval = setInterval( () => {
    //   this.updateContents();
    //  }, 30000);

  }

  ngOnDestroy(){
    // clearInterval(this.refreshInterval);
  }

  onEdit(){
    this.router.navigate(['edit'], {relativeTo: this.route});
  }

  onDelete() {
    this.boardsRestService.deleteBoard(this.boardsService.getBoard(this.id));
    this.boardsService.deleteBoard(this.id);
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onRefresh() {
    this.updateContents();
  }

  updateContents(){
    this.interestsRestService.updateContents();
  }
}
