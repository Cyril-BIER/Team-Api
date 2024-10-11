import { Component, Input } from '@angular/core';

@Component({
  selector: 'player-component',
  standalone: true,
  imports: [],
  templateUrl: './player.component.html',
  styleUrl: './player.component.css',
})
export class PlayerComponent {
  @Input() data: any;
  constructor() {}
}
