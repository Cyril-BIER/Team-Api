import { Injectable, PipeTransform } from '@angular/core';
import { Player } from './player';
import { SortColumn, SortDirection } from './sortable.directive';
import {
  BehaviorSubject,
  debounceTime,
  delay,
  Observable,
  of,
  Subject,
  switchMap,
  tap,
} from 'rxjs';
import { DecimalPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';

interface SearchResult {
  players: Player[];
  total: number;
}

interface State {
  page: number;
  pageSize: number;
  searchTerm: string;
  sortColumn: SortColumn;
  sortDirection: SortDirection;
}

const compare = (v1: string | number, v2: string | number) =>
  v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

function sort(
  players: Player[],
  column: SortColumn,
  direction: string
): Player[] {
  if (direction === '' || column === '') {
    return players;
  } else {
    return [...players].sort((a, b) => {
      const res = compare(a[column], b[column]);
      return direction === 'asc' ? res : -res;
    });
  }
}

function matches(player: Player, term: string, pipe: PipeTransform) {
  return (
    player.firstName.toLowerCase().includes(term.toLowerCase()) ||
    player.lastName.toLowerCase().includes(term.toLowerCase()) ||
    player.role.toLowerCase().includes(term.toLowerCase()) ||
    player.teamName.toLowerCase().includes(term.toLowerCase())
  );
}

@Injectable({ providedIn: 'root' })
export class PlayerService {
  private _loading$ = new BehaviorSubject<boolean>(true);
  private _search$ = new Subject<void>();
  private _players$ = new BehaviorSubject<Player[]>([]);
  private _total$ = new BehaviorSubject<number>(0);
  private _state: State = {
    page: 1,
    pageSize: 10,
    searchTerm: '',
    sortColumn: '',
    sortDirection: '',
  };

  constructor(private http: HttpClient, private pipe: DecimalPipe) {
    this._search$
      .pipe(
        tap(() => this._loading$.next(true)),
        debounceTime(200),
        switchMap(() => this._search()),
        delay(200),
        tap(() => this._loading$.next(false))
      )
      .subscribe((result) => {
        this._players$.next(result.players);
        this._total$.next(result.total);
      });

    this._search$.next();
  }

  get players$() {
    return this._players$.asObservable();
  }
  get total$() {
    return this._total$.asObservable();
  }
  get loading$() {
    return this._loading$.asObservable();
  }
  get page() {
    return this._state.page;
  }
  get pageSize() {
    return this._state.pageSize;
  }
  get searchTerm() {
    return this._state.searchTerm;
  }

  set page(page: number) {
    this._set({ page });
  }
  set pageSize(pageSize: number) {
    this._set({ pageSize });
  }
  set searchTerm(searchTerm: string) {
    this._set({ searchTerm });
  }
  set sortColumn(sortColumn: SortColumn) {
    this._set({ sortColumn });
  }
  set sortDirection(sortDirection: SortDirection) {
    this._set({ sortDirection });
  }

  private _set(patch: Partial<State>) {
    Object.assign(this._state, patch);
    this._search$.next();
  }

  private _search(): Observable<SearchResult> {
    const { sortColumn, sortDirection, pageSize, page, searchTerm } =
      this._state;

    return this.http
      .get<Player[]>('http://localhost:8080/api/player')
      .pipe(
        switchMap((players) => {
          // 1. sort
          let sortedPlayers = sort(players, sortColumn, sortDirection);

          // 2. filter
          sortedPlayers = sortedPlayers.filter((player) =>
            matches(player, searchTerm, this.pipe)
          );
          const total = sortedPlayers.length;

          // 3. paginate
          sortedPlayers = sortedPlayers.slice(
            (page - 1) * pageSize,
            (page - 1) * pageSize + pageSize
          );

          return of({ players: sortedPlayers, total });
        })
      );
  }

  public delete(id : number):Observable<void>{

    let url = `http://localhost:8080/api/player?id=${id}`;
    console.log(url);
    return this.http
      .delete<void>(url);

  }
}
