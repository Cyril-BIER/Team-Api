import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'create-player-content',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-player.content.html',
})
export class CreatePlayerContent {
  activeModal = inject(NgbActiveModal);

  playerData = [
    {
      firstName: '',
      lastName: '',
      jerseyNumber: 0,
      role: '',
      teamName: '',
    },
  ];

  constructor(private http: HttpClient) {}

  submit() {
    const apiUrl = 'http://localhost:8080/api/player';
    console.log(this.playerData);
    this.http.post(apiUrl, this.playerData).subscribe({
      next: (response) => {
        console.log('Success!', response);
        this.activeModal.close()
      },
      error: (error) => {
        console.error('Error!', error);
      },
    });
  }
}

@Component({
  selector: 'app-create-player',
  standalone: true,
  templateUrl: 'create-player.component.html',
})
export class CreatePlayerComponent {
  private modalService = inject(NgbModal);

  open() {
    this.modalService.open(CreatePlayerContent);
  }
}
