import { DecimalPipe, AsyncPipe } from '@angular/common';
import { Component, QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';

import { Player } from './player';
import { PlayerService } from './player.service';
import { NgbdSortableHeader, SortEvent } from './sortable.directive';
import { FormsModule } from '@angular/forms';
import { NgbHighlight, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CreatePlayerComponent } from '../create-player/create-player.component';
import { NgIconComponent } from '@ng-icons/core';
import { bootstrapPersonDash } from '@ng-icons/bootstrap-icons';

@Component({
  selector: 'players-app',
  standalone: true,
  imports: [
    DecimalPipe,
    FormsModule,
    AsyncPipe,
    NgbHighlight,
    NgbdSortableHeader,
    NgbPaginationModule,
    CreatePlayerComponent,
    NgIconComponent
  ],
  templateUrl: './players.component.html',
  providers: [PlayerService, DecimalPipe],
})
export class PlayersComponent {
  iconDeletePlayer = bootstrapPersonDash;
  players$: Observable<Player[]>;
  total$: Observable<number>;

  @ViewChildren(NgbdSortableHeader) headers!: QueryList<NgbdSortableHeader>;

  constructor(public service: PlayerService) {
    this.players$ = service.players$;
    this.total$ = service.total$;
  }

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;
  }

  onDelete(id:number):void{
    this.service.delete(id).subscribe({
      next:()=>{
        console.log("deleted");
        window.location.reload();
      },
      error(err) {
        console.error(err)
      },
    })
  }
}
